/**
 * UserController.java
 *
 * Copyright 2014 redmz, Inc. All Rights Reserved.
 *
 * created by vincent 2014年12月20日
 */
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
import com.store.api.common.MD5;
import com.store.api.common.PageBean;
import com.store.api.mongo.entity.SysUser;
import com.store.api.mongo.service.SysUserService;
import com.store.api.utils.Utils;

/**
 * 
 * Revision History
 * 
 * 2014年12月20日,vincent,created it
 */
@Controller
@Scope("prototype")
@RequestMapping("user")
public class UserController extends BaseAction {

    @Autowired
    SysUserService sysUserService;

    @RequestMapping("/list")
    public ModelAndView list(PageBean pageBean) {
        List<SysUser> list = sysUserService.findAll(pageBean);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("userList", list);
        result.put("page", pageBean);
        return new ModelAndView("user/list", result);
    }

    @RequestMapping("/list/search")
    public ModelAndView list(PageBean pageBean, String userName) {
        List<SysUser> list = sysUserService.findByUserNameLike(pageBean, userName);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("userList", list);
        result.put("page", pageBean);
        result.put("userName", userName);
        return new ModelAndView("user/list", result);
    }

    @RequestMapping(value = "/cre")
    public String create() {
        return "user/add";
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST,produces = "text/plain;charset=UTF-8")
    public String add(SysUser user) {
        AjaxObject ajaxObject = null;
        if (null != user) {
            SysUser realUser = sysUserService.findByUserName(user.getUserName());
            if (null != realUser) {
                ajaxObject = AjaxObject.newError("新增失败，此用户已经存在");
            } else {
                user.setCreateTime(System.currentTimeMillis());
                user.setPwd(MD5.encrypt(user.getPwd()));
                sysUserService.save(user);
                ajaxObject = AjaxObject.newOk("添加成功");
            }
        }
        return ajaxObject.toString();
    }
    
    @RequestMapping(value = "/detail/{id}")
    public ModelAndView detail(@PathVariable long id){
        Map<String, Object> result = new HashMap<String, Object>();
        SysUser user=sysUserService.findOne(id);
        if(null==user)
            user=new SysUser();
        result.put("user_d", user);
        return new ModelAndView("user/detail", result);
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST,produces = "text/plain;charset=UTF-8")
    public String update(SysUser user){
        AjaxObject ajaxObject = null;
        if(null!=user){
            SysUser realUser=sysUserService.findOne(user.getId());
            if(null!=realUser){
                if(!Utils.isEmpty(user.getPwd()))
                    realUser.setPwd(MD5.encrypt(user.getPwd()));
                if(!Utils.isEmpty(user.getPhone()))
                    realUser.setPhone(user.getPhone());
                if(!Utils.isEmpty(user.getNickName()))
                    realUser.setNickName(user.getNickName());
                if(user.getRoleId()>0)
                    realUser.setRoleId(user.getRoleId());
                realUser.setStatus(user.getStatus());
                sysUserService.save(realUser);
                ajaxObject = AjaxObject.newOk("修改成功");
            }else
                ajaxObject = AjaxObject.newError("修改失败");
        }else
            ajaxObject = AjaxObject.newError("修改失败");
        return ajaxObject.toString();
    }
    
    @ResponseBody
    @RequestMapping(value = "/del", method = RequestMethod.POST,produces = "text/plain;charset=UTF-8")
    public String del(@RequestParam(value = "id", required = false, defaultValue = "0")
    long id){
        AjaxObject ajaxObject = null;
        SysUser user=sysUserService.findOne(id);
        if(null!=user){
                sysUserService.delete(id);
                ajaxObject = AjaxObject.newOk("删除成功");
                ajaxObject.setCallbackType("");
        }else{
            ajaxObject = AjaxObject.newError("删除失败");
            ajaxObject.setCallbackType("");
        }
        return ajaxObject.toString();
    }
}
