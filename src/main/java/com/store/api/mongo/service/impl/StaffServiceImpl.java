package com.store.api.mongo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.store.api.common.PageBean;
import com.store.api.mongo.dao.StaffRepository;
import com.store.api.mongo.entity.Staff;
import com.store.api.mongo.entity.SysUser;
import com.store.api.mongo.service.SequenceService;
import com.store.api.mongo.service.StaffService;
import com.store.api.utils.Utils;

@Service
public class StaffServiceImpl implements StaffService {
	
	@Autowired
    private SequenceService sequenceService;
	
	@Autowired
	private StaffRepository repository;
	

	@Override
	public void save(Staff entity) {
		if (0 == entity.getId()) {
            entity.setId(this.sequenceService.getNextSequence(entity));
        }
        repository.save(entity);
	}

	@Override
	public void delete(long id) {
		repository.delete(id);
	}

	@Override
	public void delete(Staff entity) {
		repository.delete(entity);
	}

	@Override
	public List<Staff> findByNameLikeOrPhone(PageBean pageBean,String name,String phone) {
		int page=pageBean.getPlainPageNum()<0?0:pageBean.getPlainPageNum()-1;
        String dirStr=pageBean.getOrderDirection();
        Direction direction=dirStr.equals(PageBean.ORDER_DIRECTION_DESC)? Direction.DESC:Direction.ASC;
        String orderField=Utils.isEmpty(pageBean.getOrderField())?"id":pageBean.getOrderField();
        PageRequest pr=new PageRequest(page, pageBean.getNumPerPage(), direction,orderField);
        Page<Staff> staffs=null;
        if(!Utils.isEmpty(phone))
        	staffs=repository.findByPhone(phone, pr);
        else
        	staffs=repository.findByNameLike(name, pr);
        pageBean.setTotalCount(staffs.getTotalElements());
        pageBean.setTotalPage(staffs.getTotalPages());
        return staffs.getContent();
	}

	@Override
	public Staff findByName(String name) {
		return repository.findByName(name);
	}

	@Override
	public Staff findOne(long id) {
		return repository.findOne(id);
	}

}
