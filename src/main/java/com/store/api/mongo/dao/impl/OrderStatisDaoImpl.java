/**
 * OrderStatisDaoImpl.java
 *
 * Copyright 2015 redmz, Inc. All Rights Reserved.
 *
 * created by vincent 2015年1月5日
 */
package com.store.api.mongo.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.mapreduce.GroupByResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.store.api.mongo.dao.OrderStatisDao;
import com.store.api.mongo.entity.Order;
import com.store.api.mongo.entity.vo.OrderStatisVo;

/**
 * 
 * Revision History
 * 
 * 2015年1月5日,vincent,created it
 */
@Repository
public class OrderStatisDaoImpl implements OrderStatisDao {

    @Autowired
    private MongoOperations mongoOps=null;
    
    @Override
    public List<OrderStatisVo> statisCustomerOrderByUsers(List<Long> userIds) {
        Criteria cri=Criteria.where("customerId").in(userIds);
        GroupBy gb=GroupBy.key("customerId");
        gb.initialDocument("{totalOrder:0,totalSucc:0,totalFail:0,totalNone:0}");
        gb.reduceFunction("function(doc, prev) { prev.totalOrder += 1;if(doc.status==1||doc.status==2||doc.status==4||doc.status==6)prev.totalSucc +=1;if(doc.status==9||doc.status==10)prev.totalFail+=1;if(doc.status==0)prev.totalNone+=1;}");
        GroupByResults<OrderStatisVo> result=mongoOps.group(cri, "order", gb, OrderStatisVo.class);
        List<OrderStatisVo> vos=new ArrayList<OrderStatisVo>();
        for (OrderStatisVo vo : result) {
            vos.add(vo);
        }
         return vos;
    }

	@Override
	public List<OrderStatisVo> statisMercTotalOrderByUsers(List<Long> userIds) {
		Criteria cri=Criteria.where("offers.merchantsId").in(userIds);
		TypedAggregation<Order> agg = newAggregation(Order.class,
				unwind("offers"),
				match(cri),
				group("offers.merchantsId")
					.count().as("totalOrder")
					.sum("totalPrice").as("totalPrice")
					.first("offers.merchantsId").as("merchantsId")
				);
		AggregationResults<OrderStatisVo> result=mongoOps.aggregate(agg, OrderStatisVo.class);
		List<OrderStatisVo> vos=result.getMappedResults();
		return vos;
	}

	@Override
	public List<OrderStatisVo> statisMercTryOrderByUsers(List<Long> userIds) {
		Criteria cri=Criteria.where("offers.merchantsId").in(userIds);
		Criteria cri2=Criteria.where("_id.isAct").is(Boolean.TRUE);
		TypedAggregation<Order> agg = newAggregation(Order.class,
				unwind("offers"),
				match(cri),
				group("offers.merchantsId","offers.isAct")
					.count().as("totalTry"),
				match(cri2)
				);
		AggregationResults<OrderStatisVo> result=mongoOps.aggregate(agg, OrderStatisVo.class);
		List<OrderStatisVo> vos=result.getMappedResults();
		return vos; 
	}

	@Override
	public List<OrderStatisVo> statisMercSuccOrderByUsers(List<Long> userIds) {
		Criteria cri=Criteria.where("offers.merchantsId").in(userIds);
		Criteria cri2=Criteria.where("_id.status").gt(0L).lt(8L);
		TypedAggregation<Order> agg = newAggregation(Order.class,
				unwind("offers"),
				match(cri),
				group("offers.merchantsId","status")
					.count().as("total"),
				match(cri2),
				group("_id.merchantsId")
					.sum("total").as("totalSucc")
					.first("_id.merchantsId").as("merchantsId")
				);
		AggregationResults<OrderStatisVo> result=mongoOps.aggregate(agg, OrderStatisVo.class);
		List<OrderStatisVo> vos=result.getMappedResults();
		return vos;
	}

	@Override
	public List<OrderStatisVo> statisMercFailOrderByUsers(List<Long> userIds) {
		Criteria cri=Criteria.where("offers.merchantsId").in(userIds);
		Criteria cri2=Criteria.where("_id.status").gt(8L);
		TypedAggregation<Order> agg = newAggregation(Order.class,
				unwind("offers"),
				match(cri),
				group("offers.merchantsId","status")
					.count().as("total"),
				match(cri2),
				group("_id.merchantsId")
					.sum("total").as("totalSucc")
					.first("_id.merchantsId").as("merchantsId")
				);
		AggregationResults<OrderStatisVo> result=mongoOps.aggregate(agg, OrderStatisVo.class);
		List<OrderStatisVo> vos=result.getMappedResults();
		return vos;
	}

}
