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
    public List<OrderStatisVo> statisOrderByUsers(List<Long> userIds) {
        return dao.statisOrderByUsers(userIds);
    }

}
