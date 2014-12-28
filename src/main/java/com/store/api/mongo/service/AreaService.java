package com.store.api.mongo.service;

import java.util.List;

import com.store.api.mongo.entity.Area;

public interface AreaService {

	public Area findOne(long id);
	
	public void  save(Area entity);
	
	public void save(List<Area> entitys);
	
	public Area findByTitle(String title);
	
	public List<Area> findByParentId(long parentId);
	
	public List<Area> findByAllTop();
	
}
