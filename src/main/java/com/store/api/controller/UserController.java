package com.store.api.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.store.api.common.PageBean;
import com.store.api.mongo.entity.Area;
import com.store.api.mongo.entity.Catalog;
import com.store.api.mongo.entity.Order;
import com.store.api.mongo.entity.Product;
import com.store.api.mongo.entity.vo.UserView;
import com.store.api.mongo.service.AreaService;
import com.store.api.mongo.service.OrderService;
import com.store.api.mongo.service.UserService;
import com.store.api.utils.ArrayUtil;
import com.store.api.utils.Utils;

@Controller()
@Scope("prototype")
@RequestMapping("/customer")
public class UserController extends BaseAction {

	@Autowired
	private AreaService areaService;

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserService userService;

	/**
	 * 
	 * @param pageBean
	 * @param pid
	 * @param cid
	 * @param start
	 * @param end
	 * @param status 1成功接单  0无人接单  2失败订单
	 * @return
	 */
	@RequestMapping("/search")
	public ModelAndView search(PageBean pageBean,@RequestParam(value = "pid", required = false, defaultValue = "7") int pid,
			@RequestParam(value = "cid", required = false, defaultValue = "340") int cid,
			@RequestParam(value = "start", required = false, defaultValue = "") String start,
			@RequestParam(value = "end", required = false, defaultValue = "") String end,
			@RequestParam(value = "status", required = false, defaultValue = "1") int status) {
		List<Area> areapList = areaService.findByAllTop();
		List<Area> areacList = areaService.findByParentId(pid);
		long startTime=0;
		long endTime=0;
		if(Utils.isEmpty(start)){
			startTime=Utils.getCurrDayMills();
			start=Utils.formatDate(new Date(), "yyyy-MM-dd");
		}else{
			startTime=Utils.parseDateStr(start,false);
		}
		if(Utils.isEmpty(end)){
			endTime=Utils.getNextDayMills();
			end=start=Utils.formatDate(new Date(), "yyyy-MM-dd");
		}else{
			endTime=Utils.parseDateStr(end,true);
		}
		List<UserView> userList=userService.findByCustomer(pageBean, startTime, endTime, cid);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("page", pageBean);
		result.put("userList", userList);
		result.put("areapList", areapList);
		result.put("areacList", areacList);
		result.put("areapSelect", pid);
		result.put("areacSelect", cid);
		result.put("start", start);
		result.put("end", end);
		result.put("status", status);
		return new ModelAndView("customer/list", result);
	}
	
	@RequestMapping(value = "/detail/{id}")
	public ModelAndView detail(@PathVariable long id) {
		Map<String, Object> result = new HashMap<String, Object>();
		Order order = orderService.findOne(id);
		result.put("order", order);
		result.put("pros", order.getProducts());
		return new ModelAndView("customer/detail", result);
	}

}