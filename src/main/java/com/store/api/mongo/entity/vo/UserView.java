/**
 * UserSearch.java
 *
 * Copyright 2014 redmz, Inc. All Rights Reserved.
 *
 * created by vincent 2014年11月29日
 */
package com.store.api.mongo.entity.vo;

import com.store.api.mongo.entity.User;

/**
 * 
 * Revision History
 * 
 * 2014年11月29日,vincent,created it
 */
public class UserView {

    
    /** 用户对象 **/
    private User user;
    
    /** 总订单数 **/
    private long totalOrder=0;
    
    /** 总成功订单数 **/
    private long totalSucc=0;
    
    /** 总失败订单数 **/
    private long totalFail=0;
    
    /** 总未接订单数 **/
    private long totalNone=0;
    
    /** 订单总金额(分) **/
    private long totalPrice=0;
    
    /** 尝试接单数 **/
    private long totalTry=0;

    public long getTotalOrder() {
		return totalOrder;
	}

	public void setTotalOrder(long totalOrder) {
		this.totalOrder = totalOrder;
	}

	public long getTotalSucc() {
		return totalSucc;
	}

	public void setTotalSucc(long totalSucc) {
		this.totalSucc = totalSucc;
	}

	public long getTotalFail() {
		return totalFail;
	}

	public void setTotalFail(long totalFail) {
		this.totalFail = totalFail;
	}

	public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getTotalNone() {
        return totalNone;
    }

    public void setTotalNone(long totalNone) {
        this.totalNone = totalNone;
    }

	public long getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(long totalPrice) {
		this.totalPrice = totalPrice;
	}

	public long getTotalTry() {
		return totalTry;
	}

	public void setTotalTry(long totalTry) {
		this.totalTry = totalTry;
	}
    
    
    
}
