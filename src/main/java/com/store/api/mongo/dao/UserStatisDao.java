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

}
