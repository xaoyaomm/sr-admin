package com.store.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.store.api.mongo.entity.Area;
import com.store.api.mongo.entity.SysUser;
import com.store.api.mongo.service.AreaService;
import com.store.api.utils.JsonUtils;

@Controller
@Scope("prototype")
@RequestMapping("/area")
public class AreaController extends BaseAction{
	
	@Autowired
	private AreaService areaService;

	@ResponseBody
	@RequestMapping(value="/getchild", produces = "text/plain;charset=UTF-8")
	public String getChild(@RequestParam(value = "pid", required = false, defaultValue = "0") long pid) {
		List<Area> list=areaService.findByParentId(pid);
		StringBuffer sb=new StringBuffer();
		sb.append("[[\"0\",\"请选择城市\"]");
		for (Area area : list) {
			sb.append(",[\""+area.getId()+"\",\""+area.getTitle()+"\"]");
		}
		
		return sb.append("]").toString();
	}
}