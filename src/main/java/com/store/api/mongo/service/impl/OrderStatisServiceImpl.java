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
import com.store.api.mongo.entity.vo.StatisVo;
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
    public List<StatisVo> statisCustomerOrderByUsers(List<Long> userIds) {
        return dao.statisCustomerOrderByUsers(userIds);
    }

	@Override
	public List<StatisVo> statisMercTotalOrderByUsers(List<Long> userIds) {
		return dao.statisMercTotalOrderByUsers(userIds);
	}

	@Override
	public List<StatisVo> statisMercTryOrderByUsers(List<Long> userIds) {
		return dao.statisMercTryOrderByUsers(userIds);
	}

	@Override
	public List<StatisVo> statisMercSuccOrderByUsers(List<Long> userIds) {
		return dao.statisMercSuccOrderByUsers(userIds);
	}

	@Override
	public List<StatisVo> statisMercFailOrderByUsers(List<Long> userIds) {
		return dao.statisMercFailOrderByUsers(userIds);
	}

    @Override
    public List<StatisVo> statisMercTrySuccOrderByUsers(List<Long> userIds) {
        return dao.statisMercTrySuccOrderByUsers(userIds);
    }

    @Override
    public List<StatisVo> statisHotProducts(long start, long end, int cid, int limit) {
        return dao.statisHotProducts(start, end, cid, limit);
    }

	@Override
	public List<StatisVo> statisTotalOrderInfo(long start, long end,int cid) {
		return dao.statisTotalOrderInfo(start, end, cid);
	}

    @Override
    public int statisTotalOrderUsers(long start, long end, int cid) {
        return dao.statisTotalOrderUsers(start, end, cid);
    }

    @Override
    public int statisTotalOrderMerc(long start, long end, int cid) {
        return dao.statisTotalOrderMerc(start, end, cid);
    }

    @Override
    public List<StatisVo> statisTotalOrderMercsGroupDay(long start, long end, int cid) {
        return dao.statisTotalOrderMercsGroupDay(start, end, cid);
    }

    @Override
    public List<StatisVo> statisTotalOrderUsersGroupDay(long start, long end, int cid) {
        return dao.statisTotalOrderUsersGroupDay(start, end, cid);
    }

}
