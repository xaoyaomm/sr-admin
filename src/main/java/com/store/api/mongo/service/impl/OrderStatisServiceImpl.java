/**
 * OrderStatisServiceImpl.java
 *
 * Copyright 2015 redmz, Inc. All Rights Reserved.
 *
 * created by vincent 2015年1月5日
 */
package com.store.api.mongo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.api.mongo.dao.OrderStatisDao;
import com.store.api.mongo.entity.vo.OrderStatisVo;
import com.store.api.mongo.service.OrderStatisService;

/**
 * 
 * Revision History
 * 
 * 2015年1月5日,vincent,created it
 */
@Service
public class OrderStatisServiceImpl implements OrderStatisService{

    @Autowired
    private OrderStatisDao dao;

    @Override
    public List<OrderStatisVo> statisCustomerOrderByUsers(List<Long> userIds) {
        return dao.statisCustomerOrderByUsers(userIds);
    }

	@Override
	public List<OrderStatisVo> statisMercTotalOrderByUsers(List<Long> userIds) {
		return dao.statisMercTotalOrderByUsers(userIds);
	}

	@Override
	public List<OrderStatisVo> statisMercTryOrderByUsers(List<Long> userIds) {
		return dao.statisMercTryOrderByUsers(userIds);
	}

	@Override
	public List<OrderStatisVo> statisMercSuccOrderByUsers(List<Long> userIds) {
		return dao.statisMercSuccOrderByUsers(userIds);
	}

	@Override
	public List<OrderStatisVo> statisMercFailOrderByUsers(List<Long> userIds) {
		return dao.statisMercFailOrderByUsers(userIds);
	}

}
