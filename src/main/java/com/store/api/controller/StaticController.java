/**
 * StaticController.java
 *
 * Copyright 2015 redmz, Inc. All Rights Reserved.
 *
 * created by vincent 2015年1月10日
 */
package com.store.api.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.store.api.mongo.entity.Area;
import com.store.api.mongo.entity.User;
import com.store.api.mongo.entity.enumeration.UserType;
import com.store.api.mongo.entity.vo.StatisVo;
import com.store.api.mongo.entity.vo.UserView;
import com.store.api.mongo.service.AreaService;
import com.store.api.mongo.service.OrderStatisService;
import com.store.api.mongo.service.UserService;
import com.store.api.utils.Utils;

/**
 * 
 * Revision History
 * 
 * 2015年1月10日,vincent,created it
 */
@Controller()
@Scope("prototype")
@RequestMapping("/statis")
public class StaticController extends BaseAction {
    
    @Autowired
    private AreaService areaService;

    @Autowired
    private OrderStatisService orderStatisService;
    
    @Autowired
    private UserService userService;
    
    @RequestMapping("/product")
    public ModelAndView product(@RequestParam(value = "pid", required = false, defaultValue = "7") int pid,
            @RequestParam(value = "cid", required = false, defaultValue = "340") int cid,
            @RequestParam(value = "start", required = false, defaultValue = "") String start,
            @RequestParam(value = "end", required = false, defaultValue = "") String end,
            @RequestParam(value = "limit", required = false, defaultValue = "50") int limit) {
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
        
        List<StatisVo> statisList=orderStatisService.statisHotProducts(startTime, endTime, cid, limit);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("hot_productsList", statisList);
        result.put("p_areapList", areapList);
        result.put("p_areacList", areacList);
        result.put("p_areapSelect", pid);
        result.put("p_areacSelect", cid);
        result.put("p_start", start);
        result.put("p_end", end);
        result.put("limit", limit);
        return new ModelAndView("statis/product", result);
    }
    
    @RequestMapping("/order")
    public ModelAndView order(@RequestParam(value = "pid", required = false, defaultValue = "7") int pid,
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
        
        List<String> dataStrList=Utils.getDateStrWithRange(start, end);
        
        List<StatisVo> statisList=orderStatisService.statisTotalOrderInfo(startTime, endTime, cid);
        long totalOrder=0;
        long totalSucc=0;
        long totalFail=0;
        long totalNone=0;
        long totalPrice=0;
        long totalAvgPrice=0;
        StringBuffer dateStr=new StringBuffer();
        List<String> totalOrderList=new LinkedList<String>();
        List<String> totalSuccList=new LinkedList<String>();
        List<String> totalFailList=new LinkedList<String>();
        List<String> totalNoneList=new LinkedList<String>();
        List<String> totalPriceList=new LinkedList<String>();
        List<String> totalAvgPriceList=new LinkedList<String>();
        List<StatisVo> statisLinkedList=new LinkedList<StatisVo>();
        for (int i=0;i < dataStrList.size();i++) {
        	String date=dataStrList.get(i);
        	boolean flag=false;
        	for (StatisVo vo : statisList) {
        		if(vo.getDateStr().equals(date)){
	            	totalOrder+=vo.getTotalOrder();
	            	totalSucc+=vo.getTotalSucc();
	            	totalFail+=vo.getTotalFail();
	            	totalNone+=vo.getTotalNone();
	            	totalPrice+=vo.getTotalPrice();
	            	statisLinkedList.add(vo);
	            	totalOrderList.add(vo.getTotalOrder()+"");
	            	totalSuccList.add(vo.getTotalSucc()+"");
	            	totalFailList.add(vo.getTotalFail()+"");
	            	totalNoneList.add(vo.getTotalNone()+"");
	            	totalPriceList.add(nfmt.format(vo.getTotalPrice()/100));
	            	totalAvgPriceList.add(nfmt.format(vo.getTotalAvgPrice()/100));
	            	flag=true;
	            	break;
            	}
    		}
        	if(!flag){
        		statisLinkedList.add(new StatisVo());
        		totalOrderList.add("0");
        		totalSuccList.add("0");
        		totalFailList.add("0");
        		totalNoneList.add("0");
        		totalPriceList.add("0");
        		totalAvgPriceList.add("0");
        	}
        	dateStr.append("\"").append(date).append("\"");
        	if(i!=dataStrList.size()-1)
        		dateStr.append(",");
        }
        
        if(totalOrder>0 && totalPrice>0)
        	totalAvgPrice=totalPrice/totalOrder;
        
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("statis_orderList", statisLinkedList);
        result.put("so_areapList", areapList);
        result.put("so_areacList", areacList);
        result.put("so_areapSelect", pid);
        result.put("so_areacSelect", cid);
        result.put("so_start", start);
        result.put("so_end", end);
        result.put("totalOrder", totalOrder);
        result.put("totalSucc", totalSucc);
        result.put("totalFail", totalFail);
        result.put("totalNone", totalNone);
        result.put("totalPrice", totalPrice);
        result.put("totalAvgPrice", totalAvgPrice);
        result.put("dateStr", dateStr.toString());
        result.put("totalOrderListStr", totalOrderList.toString());
        result.put("totalSuccListStr", totalSuccList.toString());
        result.put("totalFailListStr", totalFailList.toString());
        result.put("totalNoneListStr", totalNoneList.toString());
        result.put("totalPriceListStr", totalPriceList.toString());
        result.put("totalAvgPriceListStr", totalAvgPriceList.toString());
        return new ModelAndView("statis/order", result);
    }
    
    @RequestMapping("/orderchart")
    public ModelAndView order(@RequestParam(value = "data", required = false, defaultValue = "") String data,
            @RequestParam(value = "dates", required = false, defaultValue = "") String dates) {
    	Map<String, Object> result = new HashMap<String, Object>();
    	result.put("dateStr", dates);
    	result.put("data", data);
    	return new ModelAndView("statis/orderChart", result);
    }
    
    @RequestMapping("/users")
    public ModelAndView users(@RequestParam(value = "pid", required = false, defaultValue = "7") int pid,
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
        
        StatisVo vo=new StatisVo();
        vo.setTotalNewVisitor(userService.findUserCountByTypeAndCreaeDate(startTime, endTime, cid, UserType.visitor));
        vo.setTotalNewCustomer(userService.findUserCountByTypeAndCreaeDate(startTime, endTime, cid, UserType.customer));
        vo.setTotalNewMerc(userService.findUserCountByTypeAndCreaeDate(startTime, endTime, cid, UserType.merchants));
        vo.setTotalLoginUsers(userService.findUserCountByTypeAndLoginDate(startTime, endTime, cid, new UserType[]{UserType.visitor,UserType.customer}));
        vo.setTotalLoginMerc(userService.findUserCountByTypeAndLoginDate(startTime, endTime, cid, new UserType[]{UserType.merchants}));
        vo.setTotalOrderMercs(orderStatisService.statisTotalOrderMerc(startTime, endTime, cid));
        vo.setTotalOrderUsers(orderStatisService.statisTotalOrderUsers(startTime, endTime, cid));
        
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("statis_vo", vo);
        result.put("us_areapList", areapList);
        result.put("us_areacList", areacList);
        result.put("us_areapSelect", pid);
        result.put("us_areacSelect", cid);
        result.put("us_start", start);
        result.put("us_end", end);
        return new ModelAndView("statis/users", result);
    }
    
    @RequestMapping("/userschart")
    public ModelAndView usersChart(@RequestParam(value = "cid", required = false, defaultValue = "340") int cid,
            @RequestParam(value = "start", required = false, defaultValue = "") String start,
            @RequestParam(value = "end", required = false, defaultValue = "") String end,
            @RequestParam(value = "type", required = false, defaultValue = "0") int type) {
        
        long startTime=0;
        long endTime=0;
        if(Utils.isEmpty(start)){
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            startTime=cal.getTime().getTime();
        }else{
            startTime=Utils.parseDateStr(start,false);
        }
        if(Utils.isEmpty(end)){
            endTime=Utils.getNextDayMills();
        }else{
            endTime=Utils.parseDateStr(end,true);
        }
        
        List<String> dataStrList=Utils.getDateStrWithRange(start, end);
        StringBuffer dateStr=new StringBuffer();
        
        List<StatisVo> vos=new ArrayList<StatisVo>();
        if(type==1)
            vos=userService.statisTotalNewVisitor(startTime, endTime, cid);
        if(type==2)
            vos=userService.statisTotalNewCustomer(startTime, endTime, cid);
        if(type==3)
            vos=userService.statisTotalLoginUsers(startTime, endTime, cid);
        if(type==4)
            vos=userService.statisTotalNewMerc(startTime, endTime, cid);
        if(type==5)
            vos=userService.statisTotalLoginMerc(startTime, endTime, cid);
        if(type==6)
            vos=orderStatisService.statisTotalOrderUsersGroupDay(startTime, endTime, cid);
        if(type==7)
            vos=orderStatisService.statisTotalOrderMercsGroupDay(startTime, endTime, cid);
        
        List<String> data=new LinkedList<String>();
        
        for (int i=0;i < dataStrList.size();i++) {
            String date=dataStrList.get(i);
            boolean flag=false;
            for (StatisVo vo : vos) {
                if(vo.getDateStr().equals(date)){
                    if(type==1)
                        data.add(vo.getTotalNewVisitor()+"");
                    if(type==2)
                        data.add(vo.getTotalNewCustomer()+"");
                    if(type==3)
                        data.add(vo.getTotalLoginUsers()+"");
                    if(type==4)
                        data.add(vo.getTotalNewMerc()+"");
                    if(type==5)
                        data.add(vo.getTotalLoginMerc()+"");
                    if(type==6)
                        data.add(vo.getTotalOrderUsers()+"");
                    if(type==7)
                        data.add(vo.getTotalOrderMercs()+"");
                    flag=true;
                    break;
                }
            }
            if(!flag){
                data.add("0");
            }
            dateStr.append("\"").append(date).append("\"");
            if(i!=dataStrList.size()-1)
                dateStr.append(",");
        }
        
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("dateStr", dateStr.toString());
        result.put("data", data.toString());
        return new ModelAndView("statis/usersChart", result);
    }
    
    @RequestMapping("/mercrec")
    public ModelAndView mercRec(@RequestParam(value = "start", required = false, defaultValue = "") String start,
            @RequestParam(value = "end", required = false, defaultValue = "") String end,
            @RequestParam(value = "limit", required = false, defaultValue = "50") int limit){
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
        
        List<UserView>	users = userService.statisTopMercRec(startTime, endTime, limit);
        
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("merc_users", users);
        result.put("m_start", start);
        result.put("m_end", end);
        result.put("m_limit", limit);
        return new ModelAndView("statis/mercRec", result);

    }

}
