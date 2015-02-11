package com.store.api.mongo.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.store.api.mongo.entity.User;
import com.store.api.mongo.entity.enumeration.UserType;

public interface UserRepository extends MongoRepository<User, Long>{
	
	public User findByUserName(String userName);
	
	public List<User> findByType(UserType type);
	
	public List<User> findByUuid(String uuid);
	
	/**
	 * 按地址位置搜索商户
	 * @param type   用户类型
	 * @param location  坐标点([经度,纬度])
	 * @param distance  搜索半径(单位:米)
	 * @return
	 */
	@Query(value="{'type':?0,'status':1,'location':{'$geoWithin':{'$centerSphere':[?1,?2]}}}")
	public List<User> geoSearch(UserType type,double[] location,double distance);
	
	@Query(value="{'type':?3,'createTime':{'$gte':?0,'$lt':?1},'cityCode':?2}")
	public Page<User> findByType(long start,long end,int cityCode,Pageable pr,UserType type);
	
	public List<User> findByPromoCodeAndType(String promoCode,UserType type);
	
	@Query(value="{'promoCode':?0,'createTime':{'$gte':?1,'$lt':?2}}")
	public List<User> findByPromoCodeAndCreateTimeAndType(String promoCode,long start,long end,UserType type);
	
	/**
	 * 按注册时间段统计新增用户
	 * @return
	 */
	@Query(value="{'type':?3,'createTime':{'$gte':?0,'$lt':?1},'cityCode':?2}",count=true)
	public int findUserCountByTypeAndCreaeDate(long start,long end,int cityCode,UserType type);
	
	/**
	 * 按最后使用时间段统计登录用户
	 * @return
	 */
	@Query(value="{'type':{'$in':?3},'lastUserTime':{'$gte':?0,'$lt':?1},'cityCode':?2}",count=true)
	public int findUserCountByTypeAndLoginDate(long start,long end,int cityCode,UserType[] types);
	
	/** 按昵称和用户类型搜索 **/
	public Page<User> findByNickNameLikeAndTypeIn(String name,UserType[] types,Pageable pr);
	
	/** 按手机号和用户类型搜索 **/
	public Page<User> findByPhoneAndTypeIn(String phone,UserType[] types,Pageable pr);
	
	/** 按昵称,手机号和用户类型搜索 **/
	public Page<User> findByNickNameLikeAndPhoneAndTypeIn(String name,String phone,UserType[] types,Pageable pr);
	
	/** 按商户推广码查询商户 **/
	public List<User> findByMercNumIn(List<Long> nums);
	
	public User findByMercNumAndType(long mercNum,UserType type);

}
