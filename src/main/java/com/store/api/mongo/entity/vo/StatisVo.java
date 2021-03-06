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
public class StatisVo {
    
    private long customerId=0;
    
    private long merchantsId=0;
    
    /** 商户推广码 **/
    private String promoCode="";
    
    private String id="";
    
    /** 报价状态 **/
    private int status=0;
    
    /** 总订单数 **/
    private long totalOrder=0;
    
    /** 总成功订单数 **/
    private long totalSucc=0;
    
    /** 总失败订单数 **/
    private long totalFail=0;
    
    /** 总无人接单数 **/
    private long totalNone=0;
    
    /** 订单总金额(分) **/
    private long totalPrice=0;
    
    /** 尝试接单数 **/
    private long totalTry=0;
    
    /** 尝试接单成功数 **/
    private long totalTrySucc=0;
    
    /** 推广用户数 **/
    private long totalRecuser=0;
    
    /** 商品名 **/
    private String productName="";
    
    /** 商品单价（分） **/
    private long productPrice=0;
    
    /** 商品图片地址 **/
    private String productImg="";
    
    /** 商品总数量 **/
    private long totalAmount=0;
    
    /** 日期(yyyy-MM-dd) **/
    private String dateStr="";
    
    /** 订单均价(分) **/
    private long totalAvgPrice=0;
    
    /** 下单用户数 **/
    private long totalOrderUsers=0;
    
    /** 接单商户数 **/
    private long totalOrderMercs=0;
    
    /** 新增用户数 **/
    private long totalNewCustomer=0;
    
    /** 新增临时用户数 **/
    private long totalNewVisitor=0;
    
    /** 新增商户数 **/
    private long totalNewMerc=0;
    
    /** 登录用户数 **/
    private long totalLoginUsers=0;
    
    /** 登录商户数 **/
    private long totalLoginMerc=0;
    
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

	public long getMerchantsId() {
		return merchantsId;
	}

	public void setMerchantsId(long merchantsId) {
		this.merchantsId = merchantsId;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

    public long getTotalTrySucc() {
        return totalTrySucc;
    }

    public void setTotalTrySucc(long totalTrySucc) {
        this.totalTrySucc = totalTrySucc;
    }

    public long getTotalRecuser() {
        return totalRecuser;
    }

    public void setTotalRecuser(long totalRecuser) {
        this.totalRecuser = totalRecuser;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public String getProductName() {
        return productName;
    }

    public long getProductPrice() {
        return productPrice;
    }

    public String getProductImg() {
        return productImg;
    }

    public long getTotalAmount() {
        return totalAmount;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductPrice(long productPrice) {
        this.productPrice = productPrice;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public void setTotalAmount(long totalAmount) {
        this.totalAmount = totalAmount;
    }

	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	public long getTotalAvgPrice() {
		return totalAvgPrice;
	}

	public void setTotalAvgPrice(long totalAvgPrice) {
		this.totalAvgPrice = totalAvgPrice;
	}

    public long getTotalOrderUsers() {
        return totalOrderUsers;
    }

    public long getTotalOrderMercs() {
        return totalOrderMercs;
    }

    public void setTotalOrderUsers(long totalOrderUsers) {
        this.totalOrderUsers = totalOrderUsers;
    }

    public void setTotalOrderMercs(long totalOrderMercs) {
        this.totalOrderMercs = totalOrderMercs;
    }

    public long getTotalNewCustomer() {
        return totalNewCustomer;
    }

    public long getTotalNewVisitor() {
        return totalNewVisitor;
    }

    public long getTotalNewMerc() {
        return totalNewMerc;
    }

    public long getTotalLoginUsers() {
        return totalLoginUsers;
    }

    public long getTotalLoginMerc() {
        return totalLoginMerc;
    }

    public void setTotalNewCustomer(long totalNewCustomer) {
        this.totalNewCustomer = totalNewCustomer;
    }

    public void setTotalNewVisitor(long totalNewVisitor) {
        this.totalNewVisitor = totalNewVisitor;
    }

    public void setTotalNewMerc(long totalNewMerc) {
        this.totalNewMerc = totalNewMerc;
    }

    public void setTotalLoginUsers(long totalLoginUsers) {
        this.totalLoginUsers = totalLoginUsers;
    }

    public void setTotalLoginMerc(long totalLoginMerc) {
        this.totalLoginMerc = totalLoginMerc;
    }
}
