package com.store.api.mongo.service;


import java.util.List;

import com.store.api.mongo.entity.SysUser;

public interface SysUserService {
	
	public void save(SysUser entity);
	
	public void save(List<SysUser> entitys);
	
	public SysUser findOne(long id);
	
	public SysUser findByUserName(String userName);
	
	public List<SysUser> findByStatus(int status);
	
}
