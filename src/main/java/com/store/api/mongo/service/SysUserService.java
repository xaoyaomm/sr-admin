package com.store.api.mongo.service;


import java.util.List;

import com.store.api.common.PageBean;
import com.store.api.mongo.entity.SysUser;

public interface SysUserService {
	
	public void save(SysUser entity);
	
	public void save(List<SysUser> entitys);
	
	public SysUser findOne(long id);
	
	public SysUser findByUserName(String userName);
	
	public List<SysUser> findByStatus(int status);
	
	public List<SysUser> findAll(PageBean pageBean);
	
	public List<SysUser> findByUserNameLike(PageBean pageBean,String str);
	
	public void delete(long id);
	
}
