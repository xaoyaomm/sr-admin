package com.store.api.mongo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.store.api.common.PageBean;
import com.store.api.mongo.dao.SysUserRepository;
import com.store.api.mongo.entity.SysUser;
import com.store.api.mongo.service.SequenceService;
import com.store.api.mongo.service.SysUserService;
import com.store.api.utils.Utils;

@Service
public class SysUserServiceImpl implements SysUserService {
	
	@Autowired
	private SequenceService sequenceService;

	@Autowired
	private SysUserRepository repository;


	@Override
	public void save(SysUser entity) {
		if (0 == entity.getId()) {
			entity.setId(this.sequenceService.getNextSequence(entity));
		}
		repository.save(entity);
	}

	@Override
	public void save(List<SysUser> entitys) {
		for (SysUser entity : entitys) {
			if (0 == entity.getId()) {
				entity.setId(sequenceService.getNextSequence(entity));
			}
		}
		repository.save(entitys);
	}

	@Override
	public SysUser findOne(long id) {
		return repository.findOne(id);
	}

	@Override
	public SysUser findByUserName(String userName) {
		return repository.findByUserName(userName);
	}

	@Override
	public List<SysUser> findByStatus(int status) {
		return repository.findByStatus(status);
	}

    @Override
    public List<SysUser> findAll(PageBean pageBean) {
        int page=pageBean.getPageNum()<0?0:pageBean.getPageNum()-1;
        String dirStr=pageBean.getOrderDirection();
        Direction direction=dirStr.equals(PageBean.ORDER_DIRECTION_DESC)? Direction.DESC:Direction.ASC;
        String orderField=Utils.isEmpty(pageBean.getOrderField())?"id":pageBean.getOrderField();
        PageRequest pr=new PageRequest(page, pageBean.getNumPerPage(), direction,orderField);
        Page<SysUser> users=repository.findAll(pr);
        pageBean.setTotalCount(users.getTotalElements());
        pageBean.setTotalPage(users.getTotalPages());
        return users.getContent();
    }

    @Override
    public List<SysUser> findByUserNameLike(PageBean pageBean, String str) {
        int page=pageBean.getPageNum()<0?0:pageBean.getPageNum()-1;
        String dirStr=pageBean.getOrderDirection();
        Direction direction=dirStr.equals(PageBean.ORDER_DIRECTION_DESC)? Direction.DESC:Direction.ASC;
        String orderField=Utils.isEmpty(pageBean.getOrderField())?"id":pageBean.getOrderField();
        PageRequest pr=new PageRequest(page, pageBean.getNumPerPage(), direction,orderField);
        Page<SysUser> users=repository.findByUserNameLike(str, pr);
        pageBean.setTotalCount(users.getTotalElements());
        pageBean.setTotalPage(users.getTotalPages());
        return users.getContent();
    }

    @Override
    public void delete(long id) {
        repository.delete(id);
    }

}
