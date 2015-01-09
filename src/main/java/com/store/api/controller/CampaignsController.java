package com.store.api.controller;

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
import org.springframework.web.servlet.ModelAndView;

import com.store.api.common.AjaxObject;
import com.store.api.mongo.entity.Campaigns;
import com.store.api.mongo.entity.Catalog;
import com.store.api.mongo.service.CampaignsService;
import com.store.api.utils.Utils;

@Controller()
@Scope("prototype")
@RequestMapping("/campaign")
public class CampaignsController extends BaseAction {
	
	@Autowired
	private CampaignsService campaignsService;
	
	@RequestMapping("/list")
	public ModelAndView list() {
		List<Campaigns> list = campaignsService.findAll();
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("camList", list);
		return new ModelAndView("campaign/list", result);
	}
	
	@RequestMapping(value = "/cre")
	public String create() {
		return "campaign/add";
	}
	
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public String add(Campaigns cam,@RequestParam(value = "startTime", required = false, defaultValue = "") String startTime,
			@RequestParam(value = "endTime", required = false, defaultValue = "") String endTime) {
		AjaxObject ajaxObject = null;
		long start=0;
		long end=0;
		if(Utils.isEmpty(startTime) || Utils.isEmpty(endTime)){
			ajaxObject = AjaxObject.newError("添加失败，开始时间,结束时间错误");
		}else{
			start=Utils.parseDateStr(startTime,"yyyy-MM-dd HH:mm:ss");
			end=Utils.parseDateStr(endTime,"yyyy-MM-dd HH:mm:ss");
			if (null != cam) {
					cam.setEnd(end);
					cam.setStart(start);
					campaignsService.save(cam);
					ajaxObject = AjaxObject.newOk("添加成功");
					ajaxObject.setNavTabId("tab_campaign");
			}
		}
		return ajaxObject.toString();
	}
	
	@RequestMapping(value = "/detail/{id}")
	public ModelAndView detail(@PathVariable long id) {
		Map<String, Object> result = new HashMap<String, Object>();
		Campaigns cam = campaignsService.findOne(id);
		if (null == cam)
			cam = new Campaigns();
		result.put("campaign", cam);
		return new ModelAndView("campaign/detail", result);
	}
	
	@ResponseBody
	@RequestMapping(value = "/del", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public String del(@RequestParam(value = "id", required = false, defaultValue = "0") long id) {
		AjaxObject ajaxObject = null;
		Campaigns cam = campaignsService.findOne(id);
		if (null != cam) {
			campaignsService.delete(id);
			ajaxObject = AjaxObject.newOk("删除成功");
			ajaxObject.setCallbackType("");
			ajaxObject.setNavTabId("tab_campaign");
		} else {
			ajaxObject = AjaxObject.newError("删除失败");
			ajaxObject.setCallbackType("");
		}
		return ajaxObject.toString();
	}
	
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public String update(Campaigns cam,@RequestParam(value = "startTime", required = false, defaultValue = "") String startTime,
			@RequestParam(value = "endTime", required = false, defaultValue = "") String endTime) {
		AjaxObject ajaxObject = null;
		long start=0;
		long end=0;
		if (null != cam) {
			Campaigns realCam = campaignsService.findOne(cam.getId());
			start=Utils.parseDateStr(startTime,"yyyy-MM-dd HH:mm:ss");
			end=Utils.parseDateStr(endTime,"yyyy-MM-dd HH:mm:ss");
			if (null != realCam) {
				cam.setEnd(end);
				cam.setStart(start);
				campaignsService.save(cam);
				ajaxObject = AjaxObject.newOk("修改成功");
				ajaxObject.setNavTabId("tab_campaign");
			} else
				ajaxObject = AjaxObject.newError("修改失败");
		} else
			ajaxObject = AjaxObject.newError("修改失败");
		return ajaxObject.toString();
	}

}
