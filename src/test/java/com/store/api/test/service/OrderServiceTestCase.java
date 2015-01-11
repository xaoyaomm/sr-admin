/**
 * OrderServiceTestCase.java
 *
 * Copyright 2015 redmz, Inc. All Rights Reserved.
 *
 * created by vincent 2015年1月5日
 */
package com.store.api.test.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.store.api.mongo.dao.OrderStatisDao;

/**
 * 
 * Revision History
 * 
 * 2015年1月5日,vincent,created it
 */
public class OrderServiceTestCase extends BaseServiceTestCase{
    
    @Autowired
    OrderStatisDao dao;
    
    @Test
    public void testStatisOrderByUsers(){
        List<Long> ids=new ArrayList<Long>();
        ids.add(6L);
        ids.add(89L);
        dao.statisCustomerOrderByUsers(ids);
    }
    
    @Test
    public void testStatisMercTotalOrderByUsers(){
        List<Long> ids=new ArrayList<Long>();
        ids.add(21L);
        ids.add(113L);
        dao.statisMercTotalOrderByUsers(ids);
    }
    
    @Test
    public void testStatisMercTryOrderByUsers(){
        List<Long> ids=new ArrayList<Long>();
        ids.add(21L);
        ids.add(113L);
        dao.statisMercTryOrderByUsers(ids);
    }
    
    @Test
    public void testStatisMercSuccOrderByUsers(){
        List<Long> ids=new ArrayList<Long>();
        ids.add(21L);
        ids.add(113L);
        dao.statisMercSuccOrderByUsers(ids);
    }
    
    @Test
    public void testStatisMercFailOrderByUsers(){
        List<Long> ids=new ArrayList<Long>();
        ids.add(21L);
        ids.add(113L);
        dao.statisMercFailOrderByUsers(ids);
    }
    
    @Test
    public void testStatisMercTrySuccOrderByUsers(){
        List<Long> ids=new ArrayList<Long>();
        ids.add(21L);
        ids.add(113L);
        dao.statisMercTrySuccOrderByUsers(ids);
    }
    
    @Test
    public void testStatisHotProducts(){
        List<Long> ids=new ArrayList<Long>();
        ids.add(21L);
        ids.add(113L);
//        dao.statisHotProducts();
    }


}
