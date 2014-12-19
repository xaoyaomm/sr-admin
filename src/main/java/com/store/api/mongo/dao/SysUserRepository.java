package com.store.api.mongo.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.store.api.mongo.entity.SysUser;

public interface SysUserRepository extends MongoRepository<SysUser, Long>{
	
	public SysUser findByUserName(String userName);
	
	public List<SysUser> findByStatus(int status);


}
