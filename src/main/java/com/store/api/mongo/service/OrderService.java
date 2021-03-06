package com.store.api.mongo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.store.api.common.PageBean;
import com.store.api.mongo.entity.Order;

/**
 * 
 * Revision History
 * 
 * 2014年11月15日,vincent,created it
 */
public interface OrderService {

    /**
     * 按买家ID分页查询
     * @param id
     * @param page
     * @param size
     * @return
     */
    public Page<Order> findByCustomerId(long id,int page,int size);
    
    /**
     * 按卖家ID分页查询
     * @param id
     * @param page
     * @param size
     * @return
     */
    public Page<Order> findByMerchantsId(long id,int page,int size);
    
    public void save(Order entity);
    
    public void save(List<Order> entitys);
    
    public void remove(long id);
    
    public void remove(List<Order> entitys);
    
    public void remove(Order entity);
    
    public Order findOne(long id);
    
    /**
     * 查询今天商户错过的订单数
     * @param id
     * @param date
     * @return
     */
    public int findTadayLostByUserId(long id,long date);
    
    /**
     * 分页查询推送给商户的订单数(top)
     * @param mercId
     * @param orderId
     * @return
     */
    public Page<Order> findTopOrder(long mercId,long orderId,int page,int size);
    
    /**
     * 分页查询推送给商户的订单数(tail)
     * @param mercId
     * @param orderId
     * @return
     */
    public Page<Order> findTailOrder(long mercId,long orderId,int page,int size);
    
    /**
     * 按创建时间,状态,地区分页查询
     * @param start
     * @param end
     * @param status
     * @param cityCode
     * @return
     */
    public List<Order> findByCreateDateAndStatusAndArea(PageBean pageBean,long start,long end,int status,int cityCode);
    
    /**
     * 查询抢单成功的商户订单
     * @param id
     * @return
     */
    public List<Order> findByMerchantsId(long id);
    
    /**
     * 查询用户下过的订单
     * @param id
     * @return
     */
    public List<Order> findByCustomerId(long id);
    
    /**
     * 按状态查询用户下过的订单
     * @param customerId
     * @param status
     * @return
     */
    public List<Order> findByCustomerIdAndStatusIn(long customerId,Long[] status);
    
    /**
     * 按状态查询商户的订单
     * @param mercId
     * @param status
     * @return
     */
    public List<Order> findByMercIdAndStatusIn(long mercId,Long[] status);
    
    /**
     * 查询商户尝试抢的订单
     * @param mercId
     * @return
     */
    public List<Order> findByMercTryOrders(long mercId);
    
    public List<Order> findByMercReceiveOrders(long mercId);
}
