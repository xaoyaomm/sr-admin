/**
 * OrderStatisDaoImpl.java
 *
 * Copyright 2015 redmz, Inc. All Rights Reserved.
 *
 * created by vincent 2015年1月5日
 */
package com.store.api.mongo.dao.impl;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.unwind;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.limit;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Projection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.mapreduce.GroupByResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import com.store.api.mongo.dao.OrderStatisDao;
import com.store.api.mongo.entity.Order;
import com.store.api.mongo.entity.vo.StatisVo;

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
    public List<StatisVo> statisCustomerOrderByUsers(List<Long> userIds) {
        Criteria cri=Criteria.where("customerId").in(userIds);
        GroupBy gb=GroupBy.key("customerId");
        gb.initialDocument("{totalOrder:0,totalSucc:0,totalFail:0,totalNone:0}");
        gb.reduceFunction("function(doc, prev) { prev.totalOrder += 1;if(doc.status==1||doc.status==2||doc.status==4||doc.status==6)prev.totalSucc +=1;if(doc.status==9||doc.status==10)prev.totalFail+=1;if(doc.status==0)prev.totalNone+=1;}");
        GroupByResults<StatisVo> result=mongoOps.group(cri, "order", gb, StatisVo.class);
        List<StatisVo> vos=new ArrayList<StatisVo>();
        for (StatisVo vo : result) {
            vos.add(vo);
        }
         return vos;
    }

	@Override
	public List<StatisVo> statisMercTotalOrderByUsers(List<Long> userIds) {
		Criteria cri=Criteria.where("offers.merchantsId").in(userIds);
		TypedAggregation<Order> agg = newAggregation(Order.class,
				unwind("offers"),
				match(cri),
				group("offers.merchantsId")
					.count().as("totalOrder")
					.sum("totalPrice").as("totalPrice")
					.first("offers.merchantsId").as("merchantsId")
				);
		AggregationResults<StatisVo> result=mongoOps.aggregate(agg, StatisVo.class);
		List<StatisVo> vos=result.getMappedResults();
		return vos;
	}

	@Override
	public List<StatisVo> statisMercTryOrderByUsers(List<Long> userIds) {
		Criteria cri=Criteria.where("offers.merchantsId").in(userIds);
		Criteria cri2=Criteria.where("_id.isAct").is(Boolean.TRUE);
		TypedAggregation<Order> agg = newAggregation(Order.class,
				unwind("offers"),
				match(cri),
				group("offers.merchantsId","offers.isAct")
					.count().as("totalTry"),
				match(cri2)
				);
		AggregationResults<StatisVo> result=mongoOps.aggregate(agg, StatisVo.class);
		List<StatisVo> vos=result.getMappedResults();
		return vos; 
	}

	@Override
	public List<StatisVo> statisMercSuccOrderByUsers(List<Long> userIds) {
		Criteria cri=Criteria.where("merchantsId").in(userIds);
		Criteria cri2=Criteria.where("_id.status").gt(0L).lt(8L);
		TypedAggregation<Order> agg = newAggregation(Order.class,
				match(cri),
				group("merchantsId","status")
					.count().as("total"),
				match(cri2),
				group("_id.merchantsId")
					.sum("total").as("totalSucc")
					.first("_id.merchantsId").as("merchantsId")
				);
		AggregationResults<StatisVo> result=mongoOps.aggregate(agg, StatisVo.class);
		List<StatisVo> vos=result.getMappedResults();
		return vos;
	}

	@Override
	public List<StatisVo> statisMercFailOrderByUsers(List<Long> userIds) {
		Criteria cri=Criteria.where("merchantsId").in(userIds);
		Criteria cri2=Criteria.where("_id.status").gt(8L);
		TypedAggregation<Order> agg = newAggregation(Order.class,
				match(cri),
				group("merchantsId","status")
					.count().as("total"),
				match(cri2),
				group("_id.merchantsId")
					.sum("total").as("totalFail")
					.first("_id.merchantsId").as("merchantsId")
				);
		AggregationResults<StatisVo> result=mongoOps.aggregate(agg, StatisVo.class);
		List<StatisVo> vos=result.getMappedResults();
		return vos;
	}

    @Override
    public List<StatisVo> statisMercTrySuccOrderByUsers(List<Long> userIds) {
        Criteria cri=Criteria.where("merchantsId").in(userIds);
        TypedAggregation<Order> agg = newAggregation(Order.class,
                match(cri),
                group("merchantsId")
                    .count().as("totalTrySucc")
                    .first("merchantsId").as("merchantsId")
                );
        AggregationResults<StatisVo> result=mongoOps.aggregate(agg, StatisVo.class);
        List<StatisVo> vos=result.getMappedResults();
        return vos;
    }
    
    @Override
    public List<StatisVo> statisHotProducts(long start,long end,int cid,int limit) {
        Criteria cri=Criteria.where("cityCode").is(cid);
        Criteria cri2=Criteria.where("createDate").gte(start).lt(end);
        TypedAggregation<Order> agg = newAggregation(Order.class,
                match(cri),
                match(cri2),
                project("products"),
                unwind("products"),
                group("products.productId")
                    .first("products.productName").as("productName")
                    .first("products.productPrice").as("productPrice")
                    .first("products.productImg").as("productImg")
                    .sum("products.amount").as("totalAmount"),
                sort(Direction.DESC,"totalAmount"),
                limit(limit)
                );
        AggregationResults<StatisVo> result=mongoOps.aggregate(agg, StatisVo.class);
        List<StatisVo> vos=result.getMappedResults();
        return vos;
    }

	@Override
	public List<StatisVo> statisTotalOrderInfo(long start, long end,int cid) {
		 Criteria cri=Criteria.where("createDate").gte(start).lt(end).and("cityCode").is(cid);
	        GroupBy gb=GroupBy.keyFunction("function(doc) {"+
                    "return {dateStr:dateFormat(new Date(doc.createDate),'yyyy-MM-dd')};"+
                "}");
	        gb.initialDocument("{totalOrder:0,totalSucc:0,totalFail:0,totalNone:0,totalPrice:0}");
	        gb.reduceFunction("function(doc, prev) { prev.totalOrder += 1;if(doc.status>0&&doc.status<8)prev.totalSucc +=1;"+
	        		"if(doc.status==9||doc.status==10)prev.totalFail+=1;if(doc.status==0)prev.totalNone +=1;"+
	        		"prev.totalPrice +=doc.totalPrice;}");
	        gb.finalizeFunction("function(result){result.totalAvgPrice=result.totalPrice/result.totalOrder;}");
	        GroupByResults<StatisVo> result=mongoOps.group(cri, "order", gb, StatisVo.class);
	        List<StatisVo> vos=new ArrayList<StatisVo>();
	        for (StatisVo vo : result) {
	            vos.add(vo);
	        }
	         return vos;
	}

}
