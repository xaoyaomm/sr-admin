package com.store.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.store.api.mongo.entity.SysUser;

@Controller
@Scope("prototype")
public class IndexController extends BaseAction{

	@RequestMapping("/index")
	public ModelAndView index() {
		Map<String, Object> result =new HashMap<String, Object>();
		Object obj=request.getSession().getAttribute("user");
		if(null!=obj){
			SysUser user=(SysUser)obj;
			result.put("user", user);
		}
		return new ModelAndView("/index",result);
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/logout")
	public ModelAndView logout() {
		request.getSession().removeAttribute("user");
		return new ModelAndView("/login");
	}
}