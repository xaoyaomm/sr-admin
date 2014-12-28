package com.store.api.mongo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.store.api.mongo.dao.CatalogRepository;
import com.store.api.mongo.entity.Catalog;
import com.store.api.mongo.service.CatalogService;
import com.store.api.mongo.service.SequenceService;

@Service
public class CatalogServiceImpl implements CatalogService {

	@Autowired
    private SequenceService sequenceService;
	
	@Autowired
	private CatalogRepository repository;
    
	@Override
	public void save(Catalog entity) {
		if (0 == entity.getId()) {
            entity.setId(this.sequenceService.getNextSequence(entity));
        }
        repository.save(entity);
	}

	@Override
	public void save(List<Catalog> entitys) {
		for (Catalog entity : entitys) {
            if (0 == entity.getId()) {
                entity.setId(sequenceService.getNextSequence(entity));
            }
        }
        repository.save(entitys);
	}

	@Override
	public void remove(long id) {
		repository.delete(id);
	}

	@Override
	public void remove(Catalog entity) {
		repository.delete(entity);
	}

	@Override
	public List<Catalog> findAllCatalog() {
		Sort sort=new Sort(Direction.DESC, "order");
		return repository.findAll(sort);
	}

	@Override
	public Catalog findByName(String name) {
		return repository.findByName(name);
	}

	@Override
	public Catalog findOne(long id) {
		return repository.findOne(id);
	}

}
