/**
 * UserStatisDao.java
 *
 * Copyright 2015 redmz, Inc. All Rights Reserved.
 *
 * created by vincent 2015年1月10日
 */
package com.store.api.mongo.dao;

import java.util.List;
import com.store.api.mongo.entity.vo.StatisVo;

/**
 * 
 * Revision History
 * 
 * 2015年1月10日,vincent,created it
 */
public interface UserStatisDao {
    
    /**
     * 查询商户推广的用户数
     * @param codes
     * @return
     */
    public List<StatisVo> findByMercRec(List<String> codes);
    
    /**
     * 按时间段分组统计注册用户数
     * @param start
     * @param end
     * @param cid
     * @return
     */
    public List<StatisVo> statisTotalNewCustomer(long start,long end,int cid);
    
    /**
     * 按时间段分组统计临时用户数
     * @param start
     * @param end
     * @param cid
     * @return
     */
    public List<StatisVo> statisTotalNewVisitor(long start,long end,int cid);
    
    /**
     * 按时间段分组统计注册商户数
     * @param start
     * @param end
     * @param cid
     * @return
     */
    public List<StatisVo> statisTotalNewMerc(long start,long end,int cid);
    
    /**
     * 按时间段分组统计登录用户数
     * @param start
     * @param end
     * @param cid
     * @return
     */
    public List<StatisVo> statisTotalLoginUsers(long start,long end,int cid);
    
    /**
     * 按时间段分组统计登录商户
     * @param start
     * @param end
     * @param cid
     * @return
     */
    public List<StatisVo> statisTotalLoginMerc(long start,long end,int cid);
    
}
