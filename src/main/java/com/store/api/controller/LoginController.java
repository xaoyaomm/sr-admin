package com.store.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.store.api.common.MD5;
import com.store.api.mongo.entity.SysUser;
import com.store.api.mongo.service.SysUserService;
import com.store.api.utils.Utils;

@Controller
@Scope("prototype")
@RequestMapping("login")
public class LoginController extends BaseAction{
	
	@Autowired
	SysUserService sysUserService;
	
	@RequestMapping("/login")
	public ModelAndView list(@RequestParam(value = "username", required = false, defaultValue = "")
    String userName,@RequestParam(value = "password", required = false, defaultValue = "")
    String passWord) {
		Map<String, Object> result =new HashMap<String, Object>();
		if(Utils.isEmpty(userName)|| Utils.isEmpty(passWord)){
			result.put("error", "用户名或密码错误");
		}
		SysUser user=sysUserService.findByUserName(userName);
		if(null!=user){
			if(!Utils.isEmpty(user.getPwd()) && user.getPwd().equalsIgnoreCase(MD5.encrypt(passWord))){
				request.getSession().setAttribute("user", user);
				return new ModelAndView("redirect:/index");
			}else
				result.put("error", "密码错误");
				
		}else{
			result.put("error", "用户不存在");
		}
		return new ModelAndView("/login",result);
	}
}