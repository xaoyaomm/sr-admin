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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import com.store.api.mongo.dao.UserStatisDao;
import com.store.api.mongo.entity.User;
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

}
