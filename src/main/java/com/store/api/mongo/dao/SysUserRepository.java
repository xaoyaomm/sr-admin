package com.store.api.mongo.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.store.api.mongo.entity.SysUser;

public interface SysUserRepository extends MongoRepository<SysUser, Long>{
	
	public SysUser findByUserName(String userName);
	
	public List<SysUser> findByStatus(int status);
	
	public Page<SysUser> findByUserNameLike(String str,Pageable pr);


}
