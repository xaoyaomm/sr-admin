/**
 * OrderServiceImpl.java
 *
 * Copyright 2014 redmz, Inc. All Rights Reserved.
 *
 * created by vincent 2014年11月15日
 */
package com.store.api.mongo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.store.api.common.PageBean;
import com.store.api.mongo.dao.OrderRepository;
import com.store.api.mongo.entity.Order;
import com.store.api.mongo.service.OrderService;
import com.store.api.mongo.service.SequenceService;
import com.store.api.utils.Utils;

/**
 * 
 * Revision History
 * 
 * 2014年11月15日,vincent,created it
 */
@Service
public class OrderServiceImpl implements OrderService {
    
    @Autowired
    private SequenceService sequenceService;
    
    @Autowired
    private OrderRepository repository;

    @Override
    public Page<Order> findByCustomerId(long id, int page, int size) {
        
        PageRequest pr=new PageRequest(page<0?0:page-1, size, Direction.DESC, "status","id");
        return repository.findByCustomerId(id, pr);
    }

    @Override
    public Page<Order> findByMerchantsId(long id, int page, int size) {
        PageRequest pr=new PageRequest(page<0?0:page-1, size, Direction.DESC, "status","id");
        return repository.findByMerchantsId(id, pr);
    }

    @Override
    public void save(Order entity) {
        if (0 == entity.getId()) {
            entity.setId(this.sequenceService.getNextSequence(entity));
        }
        repository.save(entity);
    }

    @Override
    public void save(List<Order> entitys) {
        for (Order entity : entitys) {
            if (0 == entity.getId()) {
                entity.setId(sequenceService.getNextSequence(entity));
            }
        }
        repository.save(entitys);
    }

    @Override
    public void remove(long id) {
        repository.delete(id);
    }

    @Override
    public void remove(List<Order> entitys) {
        repository.delete(entitys);
    }

	@Override
	public Order findOne(long id) {
		return repository.findOne(id);
	}

	@Override
	public void remove(Order entity) {
		repository.delete(entity);
	}

    @Override
    public int findTadayLostByUserId(long id, long date) {
        return repository.findTadayLostByUserId(id, date);
    }

    @Override
    public Page<Order> findTopOrder(long mercId, long orderId, int page, int size) {
        PageRequest pr=new PageRequest(page<0?0:page-1, size, Direction.DESC, "createDate");
        return repository.findTopOrder(mercId,orderId,pr);
    }

    @Override
    public Page<Order> findTailOrder(long mercId, long orderId, int page, int size) {
        PageRequest pr=new PageRequest(page<0?0:page-1, size, Direction.DESC, "createDate");
        return repository.findTailOrder(mercId,orderId,pr);
    }

	@Override
	public List<Order> findByCreateDateAndStatusAndArea(PageBean pageBean, long start, long end, int status, int cityCode) {
		int page=pageBean.getPlainPageNum()<0?0:pageBean.getPlainPageNum()-1;
		String statusStr=null;
		if(status==0)
			statusStr="0";
		if(status==1)
			statusStr="1,2,4,6";
		if(status==2)
			statusStr="9,10";
		int[] statusArry=Utils.string2IntArray(statusStr, ",");
        PageRequest pr=new PageRequest(page, pageBean.getNumPerPage(), Direction.DESC,"createDate");
        Page<Order> orders=repository.findByCreateDateAndStatusAndArea(start, end, statusArry, cityCode, pr);
        pageBean.setTotalCount(orders.getTotalElements());
        pageBean.setTotalPage(orders.getTotalPages());
		return orders.getContent();
	}

	@Override
	public List<Order> findByMerchantsId(long id) {
		return repository.findByMerchantsId(id);
	}

	@Override
	public List<Order> findByCustomerId(long id) {
		return repository.findByCustomerIdOrderByCreateDateDesc(id);
	}

    @Override
    public List<Order> findByCustomerIdAndStatusIn(long customerId, Long[] status) {
        return repository.findByCustomerIdAndStatusInOrderByCreateDateDesc(customerId, status);
    }

    @Override
    public List<Order> findByMercIdAndStatusIn(long mercId, Long[] status) {
        return repository.findByMerchantsIdAndStatusInOrderByCreateDateDesc(mercId, status);
    }

    @Override
    public List<Order> findByMercTryOrders(long mercId) {
        return repository.findByMercTryOrders(mercId);
    }

    @Override
    public List<Order> findByMercReceiveOrders(long mercId) {
        return repository.findByMercReceiveOrders(mercId);
    }

}
