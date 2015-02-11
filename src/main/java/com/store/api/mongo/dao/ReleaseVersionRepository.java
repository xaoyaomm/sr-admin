package com.store.api.mongo.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.store.api.mongo.entity.ReleaseVersion;
import com.store.api.mongo.entity.enumeration.UserType;

public interface ReleaseVersionRepository extends MongoRepository<ReleaseVersion, Long> {
	
	public Page<ReleaseVersion> findByClientTypeAndVersionCodeGreaterThan(UserType clientType,int versionCode,Pageable pr);
	
	public List<ReleaseVersion> findByClientTypeOrderByVersionCodeDesc(UserType type);

}
