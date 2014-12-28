package com.store.api.mongo.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.store.api.mongo.entity.Area;

public interface AreaRepository extends MongoRepository<Area, Long> {
	
	public Area findByTitle(String title);
	
	public List<Area> findByParentId(long parentId);
	
	public List<Area> findByType(int type);

}
