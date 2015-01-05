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
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.mapreduce.GroupByResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

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
    public List<OrderStatisVo> statisOrderByUsers(List<Long> userIds) {
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

}
