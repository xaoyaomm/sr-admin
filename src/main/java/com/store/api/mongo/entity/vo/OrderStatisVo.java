/**
 * OrderStatisVo.java
 *
 * Copyright 2015 redmz, Inc. All Rights Reserved.
 *
 * created by vincent 2015年1月5日
 */
package com.store.api.mongo.entity.vo;

/**
 * 
 * Revision History
 * 
 * 2015年1月5日,vincent,created it
 */
public class OrderStatisVo {
    
    private long customerId=0;
    
    /** 总订单数 **/
    private long totalOrder=0;
    
    /** 总成功订单数 **/
    private long totalSucc=0;
    
    /** 总失败订单数 **/
    private long totalFail=0;
    
    /** 总无人接单数 **/
    private long totalNone=0;

    public long getTotalOrder() {
        return totalOrder;
    }

    public long getTotalSucc() {
        return totalSucc;
    }

    public long getTotalFail() {
        return totalFail;
    }

    public void setTotalOrder(long totalOrder) {
        this.totalOrder = totalOrder;
    }

    public void setTotalSucc(long totalSucc) {
        this.totalSucc = totalSucc;
    }

    public void setTotalFail(long totalFail) {
        this.totalFail = totalFail;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getTotalNone() {
        return totalNone;
    }

    public void setTotalNone(long totalNone) {
        this.totalNone = totalNone;
    }
    
    

}
