package com.store.api.mongo.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.store.api.mongo.entity.Order;

public interface OrderRepository extends MongoRepository<Order, Long> {
    
    public Page<Order> findByCustomerId(Long id,Pageable pr);
    
    public Page<Order> findByMerchantsId(Long id,Pageable pr);
    
    public List<Order> findByCustomerIdOrderByCreateDateDesc(Long id);
    
    public List<Order> findByMerchantsId(Long id);
    
    /**
     * 查询今天商户错过的订单数
     * @param id
     * @param date
     * @return
     */
    @Query(value="{'offers':{'$elemMatch':{'merchantsId':?0,'createDate':{$gte:?1},'status':{$ne:1}}}}",count=true)
    public int findTadayLostByUserId(Long id,Long date);
    
    /**
     * 分页查询推送给商户的订单数(top)
     * @param mercId
     * @param orderId
     * @return
     */
    @Query(value="{'offers':{'$elemMatch':{'merchantsId':?0,'status':{'$ne':1}}},'id':{'$gt':?1}}")
    public Page<Order> findTopOrder(Long mercId, Long orderId,Pageable pr);
    
    /**
     * 分页查询推送给商户的订单数(tail)
     * @param mercId
     * @param orderId
     * @return
     */
    @Query(value="{'offers':{'$elemMatch':{'merchantsId':?0,'status':{'$ne':1}}},'id':{'$lt':?1}}")
    public Page<Order> findTailOrder(Long mercId, Long orderId,Pageable pr);
    
    /**
     * 按创建时间,状态,地区分页查询
     * @param start
     * @param end
     * @param status
     * @param cityCode
     * @return
     */
    @Query(value="{'createDate':{'$gte':?0,'$lt':?1},'status':{'$in':?2},'cityCode':?3}")
    public Page<Order> findByCreateDateAndStatusAndArea(long start,long end,int[] status,int cityCode,Pageable pr);
    
    public List<Order> findByCustomerIdAndStatusInOrderByCreateDateDesc(long customerId,Long[] status);

}
