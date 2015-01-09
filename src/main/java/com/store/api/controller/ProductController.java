package com.store.api.controller;

import java.io.File;
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
import com.store.api.mongo.entity.Area;
import com.store.api.mongo.entity.Catalog;
import com.store.api.mongo.entity.Product;
import com.store.api.mongo.service.AreaService;
import com.store.api.mongo.service.CatalogService;
import com.store.api.mongo.service.ProductService;
import com.store.api.utils.Utils;

@Controller()
@Scope("prototype")
@RequestMapping("/product")
public class ProductController extends BaseAction {

	@Autowired
	private ProductService productService;

	@Autowired
	private CatalogService catalogService;

	@Autowired
	private AreaService areaService;

	@RequestMapping("/list")
	public ModelAndView list() {
		List<Catalog> catalogList = catalogService.findAllCatalog();
		List<Product> list = productService.findByAreaIdAndCatalogId(51, catalogList.get(0).getId());
		List<Area> areapList = areaService.findByAllTop();
		List<Area> areacList = areaService.findByParentId(7);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("productList", list);
		result.put("areapList", areapList);
		result.put("areacList", areacList);
		result.put("catalogList", catalogList);
		result.put("areapSelect", 7);
		result.put("areacSelect", 340);
		result.put("catalogSelect", catalogList.get(0).getId());
		return new ModelAndView("product/list", result);
	}

	@RequestMapping("/search")
	public ModelAndView search(@RequestParam(value = "pid", required = false, defaultValue = "7") long pid,
			@RequestParam(value = "cid", required = false, defaultValue = "340") long cid, @RequestParam(value = "catalog_id", required = false, defaultValue = "1") long catalogId,
			@RequestParam(value = "lookup", required = false, defaultValue = "") String lookup) {
		List<Catalog> catalogList = catalogService.findAllCatalog();
		List<Product> list = productService.findByAreaIdAndCatalogId(cid, catalogId);
		List<Area> areapList = areaService.findByAllTop();
		List<Area> areacList = areaService.findByParentId(pid);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("productList", list);
		result.put("areapList", areapList);
		result.put("areacList", areacList);
		result.put("catalogList", catalogList);
		result.put("areapSelect", pid);
		result.put("areacSelect", cid);
		result.put("catalogSelect", catalogId);
		if(Utils.isEmpty(lookup))
			return new ModelAndView("product/list", result);
		else
			return new ModelAndView("product/lookuplist", result);
	}

	@RequestMapping(value = "/cre")
	public ModelAndView create(@RequestParam(value = "pid", required = false, defaultValue = "7") long pid,
			@RequestParam(value = "cid", required = false, defaultValue = "340") long cid, @RequestParam(value = "catalog_id", required = false, defaultValue = "1") long catalogId) {
		List<Area> areapList = areaService.findByAllTop();
		Area areaC=areaService.findOne(cid);
		Area areaP=areaService.findOne(pid);
		Catalog catalog = catalogService.findOne(catalogId);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("areapList", areapList);
		result.put("areapSelect", areaP);
		result.put("areacSelect", areaC);
		result.put("catalogSelect", catalog);
		return new ModelAndView("product/add", result);
	}

	@RequestMapping(value = "/detail/{id}")
	public ModelAndView detail(@PathVariable long id) {
		Map<String, Object> result = new HashMap<String, Object>();
		Product product = productService.findOne(id);
		Area areaC=areaService.findOne(product.getAreaId());
		Area areaP=areaService.findOne(areaC.getParentId());
		Catalog catalog = catalogService.findOne(product.getCatalogId());
		result.put("product", product);
		result.put("areapSelect", areaP);
		result.put("areacSelect", areaC);
		result.put("catalogSelect", catalog);
		return new ModelAndView("product/detail", result);
	}

	@ResponseBody
	@RequestMapping(value = "/del", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public String del(@RequestParam(value = "id", required = false, defaultValue = "0") long id) {
		AjaxObject ajaxObject = null;
		Product product = productService.findOne(id);
		if (null != product) {
			product.setStatus(3);
			productService.save(product);
			ajaxObject = AjaxObject.newOk("删除成功");
			ajaxObject.setCallbackType("");
			ajaxObject.setNavTabId("tab_product");
		} else {
			ajaxObject = AjaxObject.newError("删除失败");
			ajaxObject.setCallbackType("");
		}
		return ajaxObject.toString();
	}

	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public String add(@RequestParam(value = "img", required = false) MultipartFile photo, @RequestParam(value = "name", required = false, defaultValue = "") String name,
			@RequestParam(value = "price", required = false, defaultValue = "0") Double price,@RequestParam(value = "pid_d", required = false, defaultValue = "0") int pId,
			@RequestParam(value = "catalog_id_d", required = false, defaultValue = "0") long catalogId,
			@RequestParam(value = "cid_d", required = false, defaultValue = "0") int areaId, @RequestParam(value = "order", required = false, defaultValue = "0") long order) {
		AjaxObject ajaxObject = null;

		if (Utils.isEmpty(name))
			return AjaxObject.newError("商品名不能为空").toString();

		if (price <= 0)
			return AjaxObject.newError("单价设置错误").toString();
		else
			price = price * 100;
		Catalog catalog = catalogService.findOne(catalogId);
		if (null == catalog)
			return AjaxObject.newError("分类设置错误").toString();
		if (areaId == 0)
			return AjaxObject.newError("区域设置错误").toString();
		if (order == 0)
			return AjaxObject.newError("排序设置错误").toString();

		Product product = new Product();
		product.setAreaId(areaId);
		product.setCatalogId(catalogId);
		product.setName(name);
		product.setOrder(order);
		product.setPrice(price.longValue());
		product.setStatus(1);
		if (null != photo && !Utils.isEmpty(photo.getOriginalFilename())) {
			String filePhysical = Utils.buildFilePath(Constant.PHOTO_PATH);
			String fileUrl = Utils.buildFilePath(Constant.IMG_URL_PRE);
			String randomNum = (int) (Math.random() * 899 + 100) + "";
			String realName = photo.getOriginalFilename();
			String suffixName = ".";
			if (!Utils.isEmpty(realName) && realName.contains(".")) {
				suffixName += realName.split("\\.")[1];
			}
			String fileName = "p_" + System.currentTimeMillis() + randomNum + suffixName;
			File saveDir = new File(filePhysical);
			File saveFile = new File(filePhysical + fileName);
			if (!saveDir.exists())
				saveDir.mkdirs();
			try {
				photo.transferTo(saveFile);
			} catch (Exception e) {
				LOG.error("save product img fail." + name, e);
				return AjaxObject.newError("图片上传失败").toString();
			}
			if (!saveFile.exists() || saveFile.length() < photo.getSize()) {
				saveFile.delete();
				ajaxObject = AjaxObject.newError("图片上传失败");
				return ajaxObject.toString();
			}
			product.setImgUrl(fileUrl+fileName);
		}

		productService.save(product);
		ajaxObject = AjaxObject.newOk("添加成功");
		ajaxObject.setNavTabId("tab_product");
		return ajaxObject.toString();
	}

	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public String update(@RequestParam(value = "img", required = false) MultipartFile photo, @RequestParam(value = "name", required = false, defaultValue = "") String name,
			@RequestParam(value = "price", required = false, defaultValue = "0") long price,
			@RequestParam(value = "catalog_id_d", required = false, defaultValue = "0") long catalogId,@RequestParam(value = "pid_d", required = false, defaultValue = "0") int pId,
			@RequestParam(value = "cid_d", required = false, defaultValue = "0") int areaId, @RequestParam(value = "order", required = false, defaultValue = "0") long order,
			@RequestParam(value = "id", required = false, defaultValue = "0") long id) {
		AjaxObject ajaxObject = null;

		if (id == 0)
			return AjaxObject.newError("更新失败,参数错误").toString();

		if (Utils.isEmpty(name))
			return AjaxObject.newError("商品名不能为空").toString();

		if (price <= 0)
			return AjaxObject.newError("单价设置错误").toString();
		Catalog catalog = catalogService.findOne(catalogId);
		if (null == catalog)
			return AjaxObject.newError("分类设置错误").toString();
		if (areaId == 0)
			return AjaxObject.newError("区域设置错误").toString();
		if (order == 0)
			return AjaxObject.newError("排序设置错误").toString();

		Product product = productService.findOne(id);
		if (null != product) {
			product.setAreaId(areaId);
			product.setCatalogId(catalogId);
			product.setName(name);
			product.setOrder(order);
			product.setPrice(price);
			product.setStatus(2);
			if (null != photo && !Utils.isEmpty(photo.getOriginalFilename())) {
				String filePhysical = Utils.buildFilePath(Constant.PHOTO_PATH);
				String fileUrl = Utils.buildFilePath(Constant.IMG_URL_PRE);
				String randomNum = (int) (Math.random() * 899 + 100) + "";
				String realName = photo.getOriginalFilename();
				String suffixName = ".";
				if (!Utils.isEmpty(realName) && realName.contains(".")) {
					suffixName += realName.split("\\.")[1];
				}
				String fileName = "p_" + System.currentTimeMillis() + randomNum + suffixName;
				File saveDir = new File(filePhysical);
				File saveFile = new File(filePhysical + fileName);
				if (!saveDir.exists())
					saveDir.mkdirs();
				try {
					photo.transferTo(saveFile);
				} catch (Exception e) {
					LOG.error("save product img fail." + name, e);
					return AjaxObject.newError("图片上传失败").toString();
				}
				if (!saveFile.exists() || saveFile.length() < photo.getSize()) {
					saveFile.delete();
					ajaxObject = AjaxObject.newError("图片上传失败");
					return ajaxObject.toString();
				}
				product.setImgUrl(fileUrl+fileName);
			}

			productService.save(product);
			ajaxObject = AjaxObject.newOk("更新成功");
			ajaxObject.setNavTabId("tab_product");
//			ajaxObject.setRel("tab_product");
			return ajaxObject.toString();
		} else
			return AjaxObject.newError("更新失败").toString();
	}

}
