package com.store.api.mongo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.store.api.mongo.dao.ReleaseVersionRepository;
import com.store.api.mongo.entity.ReleaseVersion;
import com.store.api.mongo.entity.enumeration.UserType;
import com.store.api.mongo.service.ReleaseVersionService;
import com.store.api.mongo.service.SequenceService;

@Service
public class ReleaseVersionServiceImpl implements ReleaseVersionService {
	
	@Autowired
    private SequenceService sequenceService;
	
	@Autowired
	private ReleaseVersionRepository repository;

	@Override
	public void save(ReleaseVersion entity) {
		if (0 == entity.getId()) {
            entity.setId(this.sequenceService.getNextSequence(entity));
        }
        repository.save(entity);
	}

	@Override
	public void delete(long id) {
		repository.delete(id);
	}

	@Override
	public ReleaseVersion findMax(UserType type,int versionCode) {
		PageRequest pr=new PageRequest(0,1, Direction.DESC, "versionCode");
        Page<ReleaseVersion> page=repository.findByClientTypeAndVersionCodeGreaterThan(type, versionCode, pr);
        if(page.hasContent())
        	return page.getContent().get(0);
		return null;
	}

	@Override
	public List<ReleaseVersion> findByClientType(UserType type) {
		return repository.findByClientTypeOrderByVersionCodeDesc(type);
	}

	@Override
	public List<ReleaseVersion> findAll() {
		return repository.findAll(new Sort(Direction.DESC,"versionCode"));
	}

	@Override
	public ReleaseVersion findOne(long id) {
		return repository.findOne(id);
	}

}
