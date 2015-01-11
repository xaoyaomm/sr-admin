/**
 * StaticController.java
 *
 * Copyright 2015 redmz, Inc. All Rights Reserved.
 *
 * created by vincent 2015年1月10日
 */
package com.store.api.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.store.api.mongo.entity.Area;
import com.store.api.mongo.entity.vo.StatisVo;
import com.store.api.mongo.service.AreaService;
import com.store.api.mongo.service.OrderStatisService;
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

}
