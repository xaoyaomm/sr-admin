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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.store.api.common.AjaxObject;
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
@RequestMapping("/merchants")
public class MerchantsController extends BaseAction {

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
	 * @return
	 */
	@RequestMapping("/search")
	public ModelAndView search(PageBean pageBean,@RequestParam(value = "pid", required = false, defaultValue = "7") int pid,
			@RequestParam(value = "cid", required = false, defaultValue = "340") int cid,
			@RequestParam(value = "start", required = false, defaultValue = "") String start,
			@RequestParam(value = "end", required = false, defaultValue = "") String end) {
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
		List<UserView> mercList=userService.findByMerc(pageBean, startTime, endTime,cid);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("page", pageBean);
		result.put("mercList", mercList);
		result.put("areapList", areapList);
		result.put("areacList", areacList);
		result.put("areapSelect", pid);
		result.put("areacSelect", cid);
		result.put("start", start);
		result.put("end", end);
		return new ModelAndView("merchants/list", result);
	}
	
	@RequestMapping(value = "/detail/{id}")
	public ModelAndView detail(@PathVariable long id) {
		Map<String, Object> result = new HashMap<String, Object>();
		User user = userService.findOne(id);
		result.put("merchants", user);
		return new ModelAndView("merchants/detail", result);
	}
	
	@ResponseBody
	@RequestMapping(value = "/opera/{id}")
    public String opera(@PathVariable long id,@RequestParam(value = "type", required = false, defaultValue = "0") int type) {
	    AjaxObject ajaxObject = null;
	    if(type==1 || type==2){
            User user = userService.findOne(id);
            if(null!=user){
                if(type==1){
                    user.setStatus(0);
                    userService.save(user);
                }
                if(type==2){
                    user.setStatus(1);
                    userService.save(user);
                }
                ajaxObject = AjaxObject.newOk("修改成功");
                ajaxObject.setCallbackType("");
//                ajaxObject.setNavTabId("tab_merchants");
            }else{
                ajaxObject = AjaxObject.newError("修改失败");
                ajaxObject.setCallbackType("");
            }
        }else{
            ajaxObject = AjaxObject.newError("修改失败");
            ajaxObject.setCallbackType("");
        }
        return ajaxObject.toString();
    }
	
	@RequestMapping(value = "/recs/{id}")
    public ModelAndView recs(@PathVariable long id) {
        Map<String, Object> result = new HashMap<String, Object>();
        User user = userService.findOne(id);
        List<User> users=userService.findByPromoCode(user.getMercNum()+"");
        result.put("rec_users", users);
        return new ModelAndView("merchants/recUsers", result);
    }
	
	@RequestMapping(value = "/orders/{id}")
    public ModelAndView orders(@PathVariable long id,@RequestParam(value = "type", required = false, defaultValue = "1") int type) {
        Map<String, Object> result = new HashMap<String, Object>();
        List<Order> orders=new ArrayList<Order>();
        if(type==1)
            orders = orderService.findByMercReceiveOrders(id);
        if(type==2)
            orders = orderService.findByMercTryOrders(id);
        if(type==3)
            orders = orderService.findByMerchantsId(id);
        if(type==4)
            orders = orderService.findByMercIdAndStatusIn(id, new Long[]{1L,2L,4L,6L});
        if(type==5)
            orders = orderService.findByMercIdAndStatusIn(id, new Long[]{9L,10L});
        
        result.put("merc_orders", orders);
        result.put("merc_id", id);
        return new ModelAndView("merchants/orders", result);
    }

}
