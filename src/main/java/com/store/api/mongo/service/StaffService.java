package com.store.api.mongo.service;

import java.util.List;

import com.store.api.common.PageBean;
import com.store.api.mongo.entity.Staff;

public interface StaffService {
	
	public Staff findOne(long id);
	
	public void save(Staff entity);
	
	public void delete(long id);
	
	public void delete(Staff entity);
	
	public List<Staff> findByNameLikeOrPhone(PageBean pageBean,String name,String phone);
	
	public Staff findByName(String name); 
}
