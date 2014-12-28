package com.store.api.mongo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.api.mongo.dao.AreaRepository;
import com.store.api.mongo.entity.Area;
import com.store.api.mongo.service.AreaService;
import com.store.api.mongo.service.SequenceService;

@Service
public class AreaServiceImpl implements AreaService {
	
	@Autowired
	private AreaRepository repository;
	
	@Autowired
    private SequenceService sequenceService;

	@Override
	public Area findOne(long id) {
		return repository.findOne(id);
	}

	@Override
	public void save(Area entity) {
		if (0 == entity.getId()) {
            entity.setId(this.sequenceService.getNextSequence(entity));
        }
        repository.save(entity);
	}

	@Override
	public void save(List<Area> entitys) {
		for (Area entity : entitys) {
            if (0 == entity.getId()) {
                entity.setId(sequenceService.getNextSequence(entity));
            }
        }
        repository.save(entitys);
	}

	@Override
	public Area findByTitle(String title) {
		return repository.findByTitle(title);
	}

	@Override
	public List<Area> findByParentId(long parentId) {
		return repository.findByParentId(parentId);
	}

	@Override
	public List<Area> findByAllTop() {
		return repository.findByType(1);
	}

}
