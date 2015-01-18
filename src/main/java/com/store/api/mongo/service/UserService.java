package com.store.api.mongo.service;


import java.util.List;

import com.store.api.common.PageBean;
import com.store.api.mongo.entity.User;
import com.store.api.mongo.entity.enumeration.UserType;
import com.store.api.mongo.entity.vo.StatisVo;
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
	
	/**
	 * 按注册时间段统计新增用户
	 * @param start
	 * @param end
	 * @param cityCode
	 * @param type 用户类型
	 * @return
	 */
	public int findUserCountByTypeAndCreaeDate(long start,long end,int cityCode,UserType type);
	
	/**
	 * 按最后使用时间段统计登录用户
	 * @param start
	 * @param end
	 * @param cityCode
	 * @param types 用户类型列表
	 * @return
	 */
	public int findUserCountByTypeAndLoginDate(long start,long end,int cityCode,UserType[] types);
	
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
