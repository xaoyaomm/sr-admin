package com.store.api.controller;

import java.util.ArrayList;
import java.util.Calendar;
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
import com.store.api.mongo.entity.Address;
import com.store.api.mongo.entity.Area;
import com.store.api.mongo.entity.Catalog;
import com.store.api.mongo.entity.Order;
import com.store.api.mongo.entity.Product;
import com.store.api.mongo.entity.User;
import com.store.api.mongo.entity.enumeration.UserType;
import com.store.api.mongo.entity.vo.UserView;
import com.store.api.mongo.service.AddressService;
import com.store.api.mongo.service.AreaService;
import com.store.api.mongo.service.OrderService;
import com.store.api.mongo.service.UserService;
import com.store.api.utils.ArrayUtil;
import com.store.api.utils.Utils;

@Controller()
@Scope("prototype")
@RequestMapping("/customer")
public class CustomerController extends BaseAction {

	@Autowired
	private AreaService areaService;

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AddressService addressService;

	/**
	 * 
	 * @param pageBean
	 * @param pid
	 * @param cid
	 * @param start
	 * @param end
	 * @param type 1注册用户  0临时用户
	 * @return
	 */
	@RequestMapping("/search")
	public ModelAndView search(PageBean pageBean,@RequestParam(value = "pid", required = false, defaultValue = "7") int pid,
			@RequestParam(value = "cid", required = false, defaultValue = "340") int cid,
			@RequestParam(value = "start", required = false, defaultValue = "") String start,
			@RequestParam(value = "end", required = false, defaultValue = "") String end,
			@RequestParam(value = "type", required = false, defaultValue = "1") int type) {
		List<Area> areapList = areaService.findByAllTop();
		List<Area> areacList = areaService.findByParentId(pid);
		long startTime=0;
		long endTime=0;
		if(Utils.isEmpty(start)){
		    Calendar cal = Calendar.getInstance();
	        cal.set(Calendar.HOUR_OF_DAY, 0);
	        cal.set(Calendar.MINUTE, 0);
	        cal.set(Calendar.SECOND, 0);
	        cal.add(Calendar.DAY_OF_MONTH, -7);
			startTime=cal.getTime().getTime();
			start=Utils.formatDate(cal.getTime(), "yyyy-MM-dd");
		}else{
			startTime=Utils.parseDateStr(start,false);
		}
		if(Utils.isEmpty(end)){
			endTime=Utils.getNextDayMills();
			end=Utils.formatDate(new Date(), "yyyy-MM-dd");
		}else{
			endTime=Utils.parseDateStr(end,true);
		}
		List<UserView> userList=userService.findByCustomer(pageBean, startTime, endTime,cid, type==1?UserType.customer:UserType.visitor);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("page", pageBean);
		result.put("userList", userList);
		result.put("areapList", areapList);
		result.put("areacList", areacList);
		result.put("areapSelect", pid);
		result.put("areacSelect", cid);
		result.put("start", start);
		result.put("end", end);
		result.put("type", type);
		return new ModelAndView("customer/list", result);
	}
	
	@RequestMapping(value = "/detail/{id}")
	public ModelAndView detail(@PathVariable long id) {
		Map<String, Object> result = new HashMap<String, Object>();
		User user = userService.findOne(id);
		List<Address> adds=addressService.findByUserId(id);
		result.put("customer", user);
		result.put("adds", adds);
		return new ModelAndView("customer/detail", result);
	}
	
	@RequestMapping(value = "/orders/{id}")
    public ModelAndView orders(@PathVariable long id,@RequestParam(value = "type", required = false, defaultValue = "1") int type) {
        Map<String, Object> result = new HashMap<String, Object>();
        List<Order> orders=new ArrayList<Order>();
        if(type==1)
            orders = orderService.findByCustomerId(id);
        if(type==2)
            orders = orderService.findByCustomerIdAndStatusIn(id, new Long[]{1L,2L,4L,6L});
        if(type==3)
            orders = orderService.findByCustomerIdAndStatusIn(id, new Long[]{0L});
        if(type==4)
            orders = orderService.findByCustomerIdAndStatusIn(id, new Long[]{9L,10L});
        
        result.put("cus_orders", orders);
        return new ModelAndView("customer/orders", result);
    }

}
