/**
 * OrderStatisDao.java
 *
 * Copyright 2015 redmz, Inc. All Rights Reserved.
 *
 * created by vincent 2015年1月5日
 */
package com.store.api.mongo.dao;

import java.util.List;

import com.store.api.mongo.entity.vo.StatisVo;

/**
 * 
 * Revision History
 * 
 * 2015年1月5日,vincent,created it
 */
public interface OrderStatisDao {
    
	/**
	 * 统计买家总订单数,成功,失败,未接订单数
	 * @param userIds
	 * @return
	 */
    public List<StatisVo> statisCustomerOrderByUsers(List<Long> userIds);
    
    /**
     * 统计用户收到的订单总数及订单总金额
     * @param userIds
     * @return
     */
    public List<StatisVo> statisMercTotalOrderByUsers(List<Long> userIds);
    
    /**
     * 统计商户尝试接单数
     * @param userIds
     * @return
     */
    public List<StatisVo> statisMercTryOrderByUsers(List<Long> userIds);
    
    /**
     * 统计商户成功接单数
     * @param userIds
     * @return
     */
    public List<StatisVo> statisMercTrySuccOrderByUsers(List<Long> userIds);
    
    /**
     * 统计商户成功订单数
     * @param userIds
     * @return
     */
    public List<StatisVo> statisMercSuccOrderByUsers(List<Long> userIds);
    
    /**
     * 统计商户订单失败数
     * @param userIds
     * @return
     */
    public List<StatisVo> statisMercFailOrderByUsers(List<Long> userIds);
    
    /**
     * 统计销售最多的商品
     * @param start 开始时间
     * @param end   结束时间
     * @param cid   城市ID
     * @param limit 
     * @return
     */
    public List<StatisVo> statisHotProducts(long start,long end,int cid,int limit);

}
