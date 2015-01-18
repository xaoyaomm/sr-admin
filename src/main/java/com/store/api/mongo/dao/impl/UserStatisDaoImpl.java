/**
 * UserStatisDaoImpl.java
 *
 * Copyright 2015 redmz, Inc. All Rights Reserved.
 *
 * created by vincent 2015年1月10日
 */
package com.store.api.mongo.dao.impl;


import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.mapreduce.GroupByResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import com.store.api.mongo.dao.UserStatisDao;
import com.store.api.mongo.entity.User;
import com.store.api.mongo.entity.enumeration.UserType;
import com.store.api.mongo.entity.vo.StatisVo;

/**
 * 
 * Revision History
 * 
 * 2015年1月10日,vincent,created it
 */
@Repository
public class UserStatisDaoImpl implements UserStatisDao {

    @Autowired
    private MongoOperations mongoOps=null;
    
    @Override
    public List<StatisVo> findByMercRec(List<String> codes) {
        Criteria cri=Criteria.where("promoCode").in(codes);
        TypedAggregation<User> agg = newAggregation(User.class,
                match(cri),
                group("promoCode")
                    .count().as("totalRecuser")
                    .first("promoCode").as("promoCode")
                );
        AggregationResults<StatisVo> result=mongoOps.aggregate(agg, StatisVo.class);
        List<StatisVo> vos=result.getMappedResults();
        return vos;
    }

    @Override
    public List<StatisVo> statisTotalNewCustomer(long start, long end, int cid) {
        Criteria cri=Criteria.where("createTime").gte(start).lt(end).and("cityCode").is(cid).and("type").is(UserType.customer);
        GroupBy gb=GroupBy.keyFunction("function(doc) {"+
                "return {dateStr:dateFormat(new Date(doc.createTime),'yyyy-MM-dd')};"+
            "}");
        gb.initialDocument("{totalNewCustomer:0}");
        gb.reduceFunction("function(doc, prev) { prev.totalNewCustomer += 1;}");
        GroupByResults<StatisVo> result=mongoOps.group(cri, "user", gb, StatisVo.class);
        List<StatisVo> vos=new ArrayList<StatisVo>();
        for (StatisVo vo : result) {
            vos.add(vo);
        }
         return vos;
    }
    
    @Override
    public List<StatisVo> statisTotalNewVisitor(long start, long end, int cid) {
        Criteria cri=Criteria.where("createTime").gte(start).lt(end).and("cityCode").is(cid).and("type").is(UserType.visitor);
        GroupBy gb=GroupBy.keyFunction("function(doc) {"+
                "return {dateStr:dateFormat(new Date(doc.createTime),'yyyy-MM-dd')};"+
            "}");
        gb.initialDocument("{totalNewVisitor:0}");
        gb.reduceFunction("function(doc, prev) { prev.totalNewVisitor += 1;}");
        GroupByResults<StatisVo> result=mongoOps.group(cri, "user", gb, StatisVo.class);
        List<StatisVo> vos=new ArrayList<StatisVo>();
        for (StatisVo vo : result) {
            vos.add(vo);
        }
         return vos;
    }

    @Override
    public List<StatisVo> statisTotalNewMerc(long start, long end, int cid) {
        Criteria cri=Criteria.where("createTime").gte(start).lt(end).and("cityCode").is(cid).and("type").is(UserType.merchants);
        GroupBy gb=GroupBy.keyFunction("function(doc) {"+
                "return {dateStr:dateFormat(new Date(doc.createTime),'yyyy-MM-dd')};"+
            "}");
        gb.initialDocument("{totalNewMerc:0}");
        gb.reduceFunction("function(doc, prev) { prev.totalNewMerc += 1;}");
        GroupByResults<StatisVo> result=mongoOps.group(cri, "user", gb, StatisVo.class);
        List<StatisVo> vos=new ArrayList<StatisVo>();
        for (StatisVo vo : result) {
            vos.add(vo);
        }
         return vos;
    }

    @Override
    public List<StatisVo> statisTotalLoginUsers(long start, long end, int cid) {
        Criteria cri=Criteria.where("lastUserTime").gte(start).lt(end).and("cityCode").is(cid).and("type").in(UserType.visitor,UserType.customer);
        GroupBy gb=GroupBy.keyFunction("function(doc) {"+
                "return {dateStr:dateFormat(new Date(doc.lastUserTime),'yyyy-MM-dd')};"+
            "}");
        gb.initialDocument("{totalLoginUsers:0}");
        gb.reduceFunction("function(doc, prev) { prev.totalLoginUsers += 1;}");
        GroupByResults<StatisVo> result=mongoOps.group(cri, "user", gb, StatisVo.class);
        List<StatisVo> vos=new ArrayList<StatisVo>();
        for (StatisVo vo : result) {
            vos.add(vo);
        }
         return vos;
    }
    
    @Override
    public List<StatisVo> statisTotalLoginMerc(long start, long end, int cid) {
        Criteria cri=Criteria.where("lastUserTime").gte(start).lt(end).and("cityCode").is(cid).and("type").is(UserType.merchants);
        GroupBy gb=GroupBy.keyFunction("function(doc) {"+
                "return {dateStr:dateFormat(new Date(doc.lastUserTime),'yyyy-MM-dd')};"+
            "}");
        gb.initialDocument("{totalNewMerc:0}");
        gb.reduceFunction("function(doc, prev) { prev.totalNewMerc += 1;}");
        GroupByResults<StatisVo> result=mongoOps.group(cri, "user", gb, StatisVo.class);
        List<StatisVo> vos=new ArrayList<StatisVo>();
        for (StatisVo vo : result) {
            vos.add(vo);
        }
         return vos;
    }
}
