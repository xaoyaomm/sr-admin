package com.store.api.mongo.service;


import java.util.List;

import com.store.api.common.PageBean;
import com.store.api.mongo.entity.User;
import com.store.api.mongo.entity.enumeration.UserType;
import com.store.api.mongo.entity.vo.UserSearch;
import com.store.api.mongo.entity.vo.UserView;

public interface UserService {
	
	public void save(User entity);
	
	public void save(List<User> entitys);
	
	public User findOne(long id);
	
	public User findByUserName(String userName);
	
	public List<User> findByType(UserType type);
	
	public User findByUuid(String uuid);
	
	/**
	 * 按地址位置搜索商户
	 * @param type   用户类型
	 * @param location  坐标点([经度,纬度])
	 * @param distance  搜索半径(单位:米)
	 * @return
	 */
	public List<UserSearch> geoSearch(UserType type,double[] location,long distance);
	
	public List<UserView> findByCustomer(PageBean pageBean,long startTime, long endTime, int cid,UserType type);
	
	public List<UserView> findByMerc(PageBean pageBean,long startTime, long endTime, int cid);
	
	public List<User> findByPromoCode(String promoCode);
	
}
