package com.store.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.store.api.common.AjaxObject;
import com.store.api.mongo.entity.HotProduct;
import com.store.api.mongo.service.HotProductService;

@Controller()
@Scope("prototype")
@RequestMapping("/hot")
public class HotProductController extends BaseAction {
	
	@Autowired
	private HotProductService hotProductService;
	
	@RequestMapping("/list")
	public ModelAndView list() {
		List<HotProduct> list = hotProductService.findAll();
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("hotList", list);
		return new ModelAndView("hot/list", result);
	}
	
	@RequestMapping(value = "/cre")
	public String create() {
		return "hot/add";
	}
	
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public String add(HotProduct hp) {
		AjaxObject ajaxObject = null;
		if (null != hp) {
			boolean flag = hotProductService.exists(hp.getId());
			if (flag) {
				ajaxObject = AjaxObject.newError("新增失败，此商品已经存在");
			} else {
				hotProductService.save(hp);
				ajaxObject = AjaxObject.newOk("添加成功");
				ajaxObject.setNavTabId("tab_hot");
			}
		}
		return ajaxObject.toString();
	}
	
	@ResponseBody
	@RequestMapping(value = "/del", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public String del(@RequestParam(value = "id", required = false, defaultValue = "0") long id) {
		AjaxObject ajaxObject = null;
		HotProduct hp = hotProductService.findOne(id);
		if (null != hp) {
			hotProductService.delete(id);
			ajaxObject = AjaxObject.newOk("删除成功");
			ajaxObject.setCallbackType("");
			ajaxObject.setNavTabId("tab_hot");
		} else {
			ajaxObject = AjaxObject.newError("删除失败");
			ajaxObject.setCallbackType("");
		}
		return ajaxObject.toString();
	}
	
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public String update(HotProduct hp) {
		AjaxObject ajaxObject = null;
		if (null != hp) {
			HotProduct realHp = hotProductService.findOne(hp.getId());
			if (null != realHp) {
				hotProductService.save(hp);
				ajaxObject = AjaxObject.newOk("修改成功");
				ajaxObject.setNavTabId("tab_hot");
			} else
				ajaxObject = AjaxObject.newError("修改失败");
		} else
			ajaxObject = AjaxObject.newError("修改失败");
		return ajaxObject.toString();
	}

}
