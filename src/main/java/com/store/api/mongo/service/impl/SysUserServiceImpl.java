package com.store.api.mongo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.api.mongo.dao.SysUserRepository;
import com.store.api.mongo.entity.SysUser;
import com.store.api.mongo.entity.User;
import com.store.api.mongo.service.SequenceService;
import com.store.api.mongo.service.SysUserService;

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

}
