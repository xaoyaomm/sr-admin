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
    
    public List<OrderStatisVo> statisOrderByUsers(List<Long> userIds);

}
