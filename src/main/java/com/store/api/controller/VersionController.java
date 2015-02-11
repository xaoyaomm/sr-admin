package com.store.api.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.store.api.common.AjaxObject;
import com.store.api.common.Constant;
import com.store.api.mongo.entity.Campaigns;
import com.store.api.mongo.entity.ReleaseVersion;
import com.store.api.mongo.entity.enumeration.UserType;
import com.store.api.mongo.service.ReleaseVersionService;
import com.store.api.utils.Utils;

@Controller()
@Scope("prototype")
@RequestMapping("/version")
public class VersionController extends BaseAction{
	
	@Autowired
	private ReleaseVersionService releaseVersionService;

	@RequestMapping("/list")
	public ModelAndView list(@RequestParam(value = "type", required = false, defaultValue = "0") int type) {
		List<ReleaseVersion> list=new ArrayList<ReleaseVersion>();
		if(type==0)
			list=releaseVersionService.findAll();
		else{
			UserType utype=type==1?UserType.customer:UserType.merchants;
			list = releaseVersionService.findByClientType(utype);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("versionList", list);
		result.put("version_type", type);
		return new ModelAndView("version/list", result);
	}
	
	@RequestMapping(value = "/cre")
	public String create() {
		return "version/add";
	}
	
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public String add(ReleaseVersion version,@RequestParam(value = "file", required = false) MultipartFile file) {
		AjaxObject ajaxObject = null;
		if(null!=version){
			version.setCreateDate(System.currentTimeMillis());
			if (null != file && !Utils.isEmpty(file.getOriginalFilename())) {
				String realName=file.getOriginalFilename();
				String filePhysical = Utils.buildFilePath(Constant.APP_PATH);
				String fileUrl = Utils.buildFilePath(Constant.APP_URL_PRE);
				
				File saveDir = new File(filePhysical);
				File saveFile = new File(filePhysical + realName);
				if (!saveDir.exists())
					saveDir.mkdirs();
				try {
					file.transferTo(saveFile);
				} catch (Exception e) {
					LOG.error("save apk fail." + version.getVersionCodeName(), e);
					return AjaxObject.newError("文件上传失败").toString();
				}
				if (!saveFile.exists() || saveFile.length() < file.getSize()) {
					saveFile.delete();
					ajaxObject = AjaxObject.newError("文件上传失败");
					return ajaxObject.toString();
				}
				version.setDownloadUrl(fileUrl+realName);
				releaseVersionService.save(version);
			}
		}
		
		
		ajaxObject = AjaxObject.newOk("添加成功");
		ajaxObject.setNavTabId("tab_version");
		return ajaxObject.toString();
	}
	
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public String update(ReleaseVersion version,@RequestParam(value = "file", required = false) MultipartFile file) {
		AjaxObject ajaxObject = null;
		if(null!=version){
			ReleaseVersion realVersion=releaseVersionService.findOne(version.getId());
			if(null==realVersion){
				ajaxObject = AjaxObject.newError("修改失败");
				return ajaxObject.toString();
			}
			if (null != file && !Utils.isEmpty(file.getOriginalFilename())) {
				String realName=file.getOriginalFilename();
				String filePhysical = Utils.buildFilePath(Constant.APP_PATH);
				String fileUrl = Utils.buildFilePath(Constant.APP_URL_PRE);
				
				File saveDir = new File(filePhysical);
				File saveFile = new File(filePhysical + realName);
				if (!saveDir.exists())
					saveDir.mkdirs();
				try {
					File oldFile=new File(realVersion.getDownloadUrl().replace(Constant.APP_URL_PRE, Constant.APP_PATH));
					if(oldFile.exists())
						oldFile.delete();
					file.transferTo(saveFile);
				} catch (Exception e) {
					LOG.error("save apk fail." + version.getVersionCodeName(), e);
					return AjaxObject.newError("文件上传失败").toString();
				}
				if (!saveFile.exists() || saveFile.length() < file.getSize()) {
					saveFile.delete();
					ajaxObject = AjaxObject.newError("文件上传失败");
					return ajaxObject.toString();
				}
				version.setDownloadUrl(fileUrl+realName);
				releaseVersionService.save(version);
			}
			releaseVersionService.save(version);
		}
		
		
		ajaxObject = AjaxObject.newOk("修改成功");
		ajaxObject.setNavTabId("tab_version");
		return ajaxObject.toString();
	}
	
	@RequestMapping(value = "/detail/{id}")
	public ModelAndView detail(@PathVariable long id) {
		Map<String, Object> result = new HashMap<String, Object>();
		ReleaseVersion version = releaseVersionService.findOne(id);
		if (null == version)
			version = new ReleaseVersion();
		result.put("releaseVersion", version);
		return new ModelAndView("version/detail", result);
	}
	
	@ResponseBody
	@RequestMapping(value = "/del", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public String del(@RequestParam(value = "id", required = false, defaultValue = "0") long id) {
		AjaxObject ajaxObject = null;
		ReleaseVersion version = releaseVersionService.findOne(id);
		if (null != version) {
			File oldFile=new File(version.getDownloadUrl().replace(Constant.APP_URL_PRE, Constant.APP_PATH));
			releaseVersionService.delete(id);
			if(oldFile.exists())
				oldFile.delete();
			ajaxObject = AjaxObject.newOk("删除成功");
			ajaxObject.setCallbackType("");
			ajaxObject.setNavTabId("tab_version");
		} else {
			ajaxObject = AjaxObject.newError("删除失败");
			ajaxObject.setCallbackType("");
		}
		return ajaxObject.toString();
	}
}
