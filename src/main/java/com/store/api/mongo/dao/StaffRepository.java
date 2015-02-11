package com.store.api.mongo.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.store.api.mongo.entity.Staff;

public interface StaffRepository extends MongoRepository<Staff, Long>{
	
	public Page<Staff> findByNameLike(String name,Pageable pr);
	
	public Page<Staff> findByPhone(String phone,Pageable pr);
	
	public Staff findByName(String name);

}
