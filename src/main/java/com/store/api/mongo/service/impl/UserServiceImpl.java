package com.store.api.mongo.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.store.api.common.Common;
import com.store.api.common.Constant;
import com.store.api.common.PageBean;
import com.store.api.mongo.dao.UserRepository;
import com.store.api.mongo.entity.Order;
import com.store.api.mongo.entity.User;
import com.store.api.mongo.entity.enumeration.UserType;
import com.store.api.mongo.entity.vo.UserSearch;
import com.store.api.mongo.entity.vo.UserView;
import com.store.api.mongo.service.OrderService;
import com.store.api.mongo.service.SequenceService;
import com.store.api.mongo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private SequenceService sequenceService;

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private OrderService orderService;

	@Override
	public void save(User entity) {
		if (0 == entity.getId()) {
			entity.setId(this.sequenceService.getNextSequence(entity));
		}
		repository.save(entity);

	}

	@Override
	public void save(List<User> entitys) {
		for (User entity : entitys) {
			if (0 == entity.getId()) {
				entity.setId(sequenceService.getNextSequence(entity));
			}
		}
		repository.save(entitys);
	}

	@Override
	public User findOne(long id) {
		return repository.findOne(id);
	}

	@Override
	public User findByUserName(String userName) {
		return repository.findByUserName(userName);
	}

	@Override
	public List<User> findByType(UserType type) {
		return repository.findByType(type);
	}

	@Override
	public User findByUuid(String uuid) {
		List<User> list = repository.findByUuid(uuid);
		if (list.size() > 0)
			return list.get(0);
		else
			return null;
	}

	/**
	 * 按地址位置搜索商户
	 * @param type   用户类型
	 * @param location  坐标点([经度,纬度])
	 * @param distance  搜索半径(单位:米)
	 * @return
	 */
	@Override
	public List<UserSearch> geoSearch(UserType type, double[] location,long distance) {
		List<User> list=repository.geoSearch(type, location, distance/Constant.EARTH_RADIUS);
		List<UserSearch> voList=new ArrayList<UserSearch>();
		for (User user : list) {
            UserSearch us=new UserSearch();
            us.setDistance(Common.getDistance(location[0], location[1], user.getLocation()[0], user.getLocation()[1]));
            us.setUser(user);
            voList.add(us);
        }
		Collections.sort(voList);
		return voList;
	}

	@Override
	public List<UserView> findByCustomer(PageBean pageBean, long startTime, long endTime, int cid) {
		int page=pageBean.getPlainPageNum()<0?0:pageBean.getPlainPageNum()-1;
		PageRequest pr=new PageRequest(page, pageBean.getNumPerPage(), Direction.DESC,"createTime");
		Page<User> users=repository.findByCustomer(startTime, endTime, cid, pr);
		pageBean.setTotalCount(users.getTotalElements());
        pageBean.setTotalPage(users.getTotalPages());
        List<UserView> uvs=new ArrayList<UserView>();
        for (User user : users) {
        	UserView uv=new UserView();
        	long totalSucc=0;
        	long totalFail=0;
        	uv.setUser(user);
			List<Order> orders=orderService.findByCustomerId(user.getId());
			for (Order order : orders) {
				if(order.getStatus()==1||order.getStatus()==2||order.getStatus()==4||order.getStatus()==6)
					totalSucc++;
				if(order.getStatus()==9||order.getStatus()==10)
					totalFail++;
			}
			uv.setTotalSucc(totalSucc);
			uv.setTotalFail(totalFail);
			uv.setTotalOrder(orders.size());
			uvs.add(uv);
		}
        
		return uvs;
	}
}
