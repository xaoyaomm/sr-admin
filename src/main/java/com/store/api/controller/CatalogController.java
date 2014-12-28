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
import com.store.api.mongo.entity.Catalog;
import com.store.api.mongo.entity.Product;
import com.store.api.mongo.entity.SysUser;
import com.store.api.mongo.service.CatalogService;
import com.store.api.mongo.service.ProductService;
import com.store.api.utils.Utils;

@Controller()
@Scope("prototype")
@RequestMapping("/catalog")
public class CatalogController extends BaseAction {

	@Autowired
	private CatalogService catalogService;

	@Autowired
	private ProductService productService;

	@RequestMapping("/list")
	public ModelAndView list() {
		List<Catalog> list = catalogService.findAllCatalog();
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("catalogList", list);
		return new ModelAndView("catalog/list", result);
	}

	@RequestMapping(value = "/cre")
	public String create() {
		return "catalog/add";
	}

	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public String add(Catalog catalog) {
		AjaxObject ajaxObject = null;
		if (null != catalog) {
			Catalog realCatalog = catalogService.findByName(catalog.getName());
			if (null != realCatalog) {
				ajaxObject = AjaxObject.newError("新增失败，此分类已经存在");
			} else {
				catalogService.save(catalog);
				ajaxObject = AjaxObject.newOk("添加成功");
				ajaxObject.setNavTabId("tab_catalog");
			}
		}
		return ajaxObject.toString();
	}

	@RequestMapping(value = "/detail/{id}")
	public ModelAndView detail(@PathVariable long id) {
		Map<String, Object> result = new HashMap<String, Object>();
		Catalog catalog = catalogService.findOne(id);
		if (null == catalog)
			catalog = new Catalog();
		result.put("catalog", catalog);
		return new ModelAndView("catalog/detail", result);
	}

	@ResponseBody
	@RequestMapping(value = "/del", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public String del(@RequestParam(value = "id", required = false, defaultValue = "0") long id) {
		AjaxObject ajaxObject = null;
		Catalog catalog = catalogService.findOne(id);
		if (null != catalog) {
			catalogService.remove(id);
			List<Product> list = productService.findByAreaId(catalog.getId());
			for (Product product : list) {
				product.setStatus(3);// 标记为已删除
			}
			if (list.size() > 0)
				productService.save(list);
			ajaxObject = AjaxObject.newOk("删除成功");
			ajaxObject.setCallbackType("");
			ajaxObject.setNavTabId("tab_catalog");
		} else {
			ajaxObject = AjaxObject.newError("删除失败");
			ajaxObject.setCallbackType("");
		}
		return ajaxObject.toString();
	}

	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public String update(Catalog catalog) {
		AjaxObject ajaxObject = null;
		if (null != catalog) {
			Catalog realCatalog = catalogService.findOne(catalog.getId());
			if (null != realCatalog) {
				catalogService.save(catalog);
				ajaxObject = AjaxObject.newOk("修改成功");
				ajaxObject.setNavTabId("tab_catalog");
			} else
				ajaxObject = AjaxObject.newError("修改失败");
		} else
			ajaxObject = AjaxObject.newError("修改失败");
		return ajaxObject.toString();
	}
}
