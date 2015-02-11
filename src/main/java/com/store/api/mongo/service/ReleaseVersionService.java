package com.store.api.mongo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.store.api.mongo.entity.ReleaseVersion;
import com.store.api.mongo.entity.enumeration.UserType;

public interface ReleaseVersionService {
	
	public void save(ReleaseVersion entity);
	
	public void delete(long id);
	
	public ReleaseVersion findOne(long id);
	
	public ReleaseVersion findMax(UserType type,int versionCode);
	
	public List<ReleaseVersion> findByClientType(UserType type);
	
	public List<ReleaseVersion> findAll();
	
}
