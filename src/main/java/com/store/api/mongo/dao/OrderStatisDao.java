/**
 * OrderStatisDao.java
 *
 * Copyright 2015 redmz, Inc. All Rights Reserved.
 *
 * created by vincent 2015年1月5日
 */
package com.store.api.mongo.dao;

import java.util.List;

import com.store.api.mongo.entity.vo.OrderStatisVo;

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
    public List<OrderStatisVo> statisCustomerOrderByUsers(List<Long> userIds);
    
    /**
     * 统计用户收到的订单总数及订单总金额
     * @param userIds
     * @return
     */
    public List<OrderStatisVo> statisMercTotalOrderByUsers(List<Long> userIds);
    
    /**
     * 统计商户尝试接单数
     * @param userIds
     * @return
     */
    public List<OrderStatisVo> statisMercTryOrderByUsers(List<Long> userIds);
    
    /**
     * 统计商户成功接单数
     * @param userIds
     * @return
     */
    public List<OrderStatisVo> statisMercSuccOrderByUsers(List<Long> userIds);
    
    /**
     * 统计商户接单失败数
     * @param userIds
     * @return
     */
    public List<OrderStatisVo> statisMercFailOrderByUsers(List<Long> userIds);

}
