package com.store.api.mongo.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.store.api.common.Common;
import com.store.api.common.Constant;
import com.store.api.common.PageBean;
import com.store.api.mongo.dao.UserRepository;
import com.store.api.mongo.dao.UserStatisDao;
import com.store.api.mongo.entity.Order;
import com.store.api.mongo.entity.User;
import com.store.api.mongo.entity.enumeration.UserType;
import com.store.api.mongo.entity.vo.StatisVo;
import com.store.api.mongo.entity.vo.UserSearch;
import com.store.api.mongo.entity.vo.UserView;
import com.store.api.mongo.service.OrderService;
import com.store.api.mongo.service.OrderStatisService;
import com.store.api.mongo.service.SequenceService;
import com.store.api.mongo.service.UserService;
import com.store.api.utils.Utils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private SequenceService sequenceService;

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private UserStatisDao userStatisDao;
	
	@Autowired
	private OrderStatisService orderStatisService;

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
	public List<UserView> findByCustomer(PageBean pageBean, long startTime, long endTime, int cid,UserType type,String userName,String phone) {
		int page=pageBean.getPlainPageNum()<0?0:pageBean.getPlainPageNum()-1;
		PageRequest pr=new PageRequest(page, pageBean.getNumPerPage(), Direction.DESC,"createTime");
		Page<User> users=null;
		if(!Utils.isEmpty(userName) || !Utils.isEmpty(phone))
			users=findByUserNameAndPhoneWithCustomer(userName, phone, pr);
		else
			users=repository.findByType(startTime, endTime, cid, pr,type);
		pageBean.setTotalCount(users.getTotalElements());
        pageBean.setTotalPage(users.getTotalPages());
        List<UserView> uvs=new ArrayList<UserView>();
        List<Long> userIds=new ArrayList<Long>();
        for (User user : users.getContent())
            userIds.add(user.getId());
        List<StatisVo> statisvos=orderStatisService.statisCustomerOrderByUsers(userIds);
        for (User user : users.getContent()) {
        	UserView uv=new UserView();
        	uv.setUser(user);
			for (StatisVo vo : statisvos) {
				if(user.getId()==vo.getCustomerId()){
				    uv.setTotalSucc(vo.getTotalSucc());
		            uv.setTotalFail(vo.getTotalFail());
		            uv.setTotalOrder(vo.getTotalOrder());
		            uv.setTotalNone(vo.getTotalNone());
				}
			}
			uvs.add(uv);
		}
		return uvs;
	}

	@Override
	public List<UserView> findByMerc(PageBean pageBean, long startTime, long endTime, int cid,String userName,String phone) {
		int page=pageBean.getPlainPageNum()<0?0:pageBean.getPlainPageNum()-1;
		PageRequest pr=new PageRequest(page, pageBean.getNumPerPage(), Direction.DESC,"createTime");
		Page<User> users=null;
		if(!Utils.isEmpty(userName) && !Utils.isEmpty(phone))
			users=findByUserNameAndPhoneWithMerc(userName, phone, pr);
		else
			users=repository.findByType(startTime, endTime, cid, pr,UserType.merchants);
		pageBean.setTotalCount(users.getTotalElements());
        pageBean.setTotalPage(users.getTotalPages());
        List<UserView> uvs=new ArrayList<UserView>();
        List<Long> userIds=new ArrayList<Long>();
        List<String> codes=new ArrayList<>();
        for (User user : users.getContent()){
            userIds.add(user.getId());
            if(user.getMercNum()!=0)
                codes.add(user.getMercNum()+"");
        }
        List<StatisVo> failOrder=orderStatisService.statisMercFailOrderByUsers(userIds);
        List<StatisVo> succOrder=orderStatisService.statisMercSuccOrderByUsers(userIds);
        List<StatisVo> totalOrder=orderStatisService.statisMercTotalOrderByUsers(userIds);
        List<StatisVo> tryOrder=orderStatisService.statisMercTryOrderByUsers(userIds);
        List<StatisVo> trySuccOrder=orderStatisService.statisMercTrySuccOrderByUsers(userIds);
        List<StatisVo> recTotal=userStatisDao.findByMercRec(codes);
        for (User user : users.getContent()) {
        	UserView uv=new UserView();
        	uv.setUser(user);
			for (StatisVo vo : failOrder) {
				if(user.getId()==vo.getMerchantsId()){
		            uv.setTotalFail(vo.getTotalFail());
				}
			}
			for (StatisVo vo : succOrder) {
				if(user.getId()==vo.getMerchantsId()){
		            uv.setTotalSucc(vo.getTotalSucc());
				}
			}
			for (StatisVo vo : totalOrder) {
				if(user.getId()==vo.getMerchantsId()){
		            uv.setTotalOrder(vo.getTotalOrder());
		            uv.setTotalPrice(vo.getTotalPrice());
				}
			}
			for (StatisVo vo : tryOrder) {
				if(user.getId()==vo.getMerchantsId()){
		            uv.setTotalTry(vo.getTotalTry());
				}
			}
			for (StatisVo vo : trySuccOrder) {
                if(user.getId()==vo.getMerchantsId()){
                    uv.setTotalTrySucc(vo.getTotalTrySucc());
                }
            }
			for (StatisVo vo : recTotal) {
                if(user.getMercNum()==Long.parseLong(vo.getPromoCode())){
                    uv.setTotalRecuser(vo.getTotalRecuser());
                }
            }
			uvs.add(uv);
		}
		return uvs;
	}

    @Override
    public List<User> findByPromoCodeWithCustomer(String promoCode) {
        return repository.findByPromoCodeAndType(promoCode,UserType.customer);
    }

    @Override
    public int findUserCountByTypeAndCreaeDate(long start, long end, int cityCode, UserType type) {
        return repository.findUserCountByTypeAndCreaeDate(start, end, cityCode, type);
    }

    @Override
    public int findUserCountByTypeAndLoginDate(long start, long end, int cityCode, UserType[] types) {
        return repository.findUserCountByTypeAndLoginDate(start, end, cityCode, types);
    }

    @Override
    public List<StatisVo> statisTotalNewCustomer(long start, long end, int cid) {
        return userStatisDao.statisTotalNewCustomer(start, end, cid);
    }

    @Override
    public List<StatisVo> statisTotalNewVisitor(long start, long end, int cid) {
        return userStatisDao.statisTotalNewVisitor(start, end, cid);
    }

    @Override
    public List<StatisVo> statisTotalNewMerc(long start, long end, int cid) {
        return userStatisDao.statisTotalNewMerc(start, end, cid);
    }

    @Override
    public List<StatisVo> statisTotalLoginUsers(long start, long end, int cid) {
        return userStatisDao.statisTotalLoginUsers(start, end, cid);
    }

    @Override
    public List<StatisVo> statisTotalLoginMerc(long start, long end, int cid) {
        return userStatisDao.statisTotalLoginMerc(start, end, cid);
    }

	private Page<User> findByUserNameAndPhoneWithMerc(String userName,
			String phone,Pageable pr) {
		if(!Utils.isEmpty(userName) && !Utils.isEmpty(phone))
			return repository.findByNickNameLikeAndPhoneAndTypeIn(userName, phone, new UserType[]{UserType.merchants},pr);
		else if(!Utils.isEmpty(userName))
			return repository.findByNickNameLikeAndTypeIn(userName, new UserType[]{UserType.merchants},pr);
		else if(!Utils.isEmpty(phone))
			return repository.findByPhoneAndTypeIn(phone, new UserType[]{UserType.merchants},pr);
		else
			return null;
	}

	public Page<User> findByUserNameAndPhoneWithCustomer(String userName,
			String phone,Pageable pr) {
		if(!Utils.isEmpty(userName) && !Utils.isEmpty(phone))
			return repository.findByNickNameLikeAndPhoneAndTypeIn(userName, phone, new UserType[]{UserType.customer,UserType.visitor},pr);
		else if(!Utils.isEmpty(userName))
			return repository.findByNickNameLikeAndTypeIn(userName, new UserType[]{UserType.customer,UserType.visitor},pr);
		else if(!Utils.isEmpty(phone))
			return repository.findByPhoneAndTypeIn(phone, new UserType[]{UserType.customer,UserType.visitor},pr);
		else
			return null;
	}

	@Override
	public List<UserView> statisTopMercRec(long start, long end, int limit) {
		List<StatisVo> vos=userStatisDao.statisTopMercRec(start, end, limit);
		List<Long> nums=new ArrayList<Long>();
		for (StatisVo vo : vos) {
			if(!Utils.isEmpty(vo.getPromoCode()) && Utils.isNumber(vo.getPromoCode()))
				nums.add(Long.valueOf(vo.getPromoCode()));
		}
		List<User> temp_users=repository.findByMercNumIn(nums);
		List<UserView> users=new ArrayList<UserView>();
		for (StatisVo vo : vos) {
			for(User user:temp_users){
				if(vo.getPromoCode().equals(user.getMercNum()+"")){
					UserView uv=new UserView();
					uv.setTotalRecuser(vo.getTotalRecuser());
					uv.setUser(user);
					users.add(uv);
				}
			}
		}
		return users;
	}

	@Override
	public List<User> findByPromoCodeAndCreateTimeWithCustomer(String promoCode,long start,long end) {
		return repository.findByPromoCodeAndCreateTimeAndType(promoCode, start, end,UserType.customer);
	}
	
	@Override
	public User findByMercNum(long mercNum ){
		return repository.findByMercNumAndType(mercNum, UserType.merchants);
	}
}
