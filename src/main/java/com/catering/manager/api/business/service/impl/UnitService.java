package com.catering.manager.api.business.service.impl;

import com.catering.manager.api.business.common.criteria.UnitCriteria;
import com.catering.manager.api.business.common.mapper.UnitMapper;
import com.catering.manager.api.business.model.UnitEntity;
import com.catering.manager.api.business.payload.UnitPayload;
import com.catering.manager.api.business.payload.global.GlobalPayload;
import com.catering.manager.api.business.repository.UnitRepository;
import com.catering.manager.api.business.service.inter.IUnitService;
import com.catering.manager.api.common.util.CommonUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UnitService implements IUnitService {

    private UnitRepository repository;
    private UnitMapper mapper;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public UnitService(UnitRepository repository, UnitMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public UnitPayload save(UnitPayload payload) {
        return mapper.entityToPayload(repository.save(mapper.payloadToEntity(payload)));
    }

    @Override
    public boolean delete(UnitPayload payload) {
        try {
            repository.delete(mapper.payloadToEntity(payload));
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean deleteById(Integer id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public GlobalPayload<UnitPayload> findAllByCriteria(UnitCriteria criteria) {

        Pageable paging = CommonUtil.pageableBuilder(criteria);

        String queryStr = CommonUtil.selectCritQueryBuilder("UnitEntity", criteria.toMap(), criteria);
        Query query = entityManager.createQuery(queryStr);

        List<UnitEntity> entityResultList = query.getResultList();

        return CommonUtil.globalPayloadBuilder(criteria, paging, entityResultList, mapper);
    }



    @Override
    public UnitPayload findById(Integer id) {
        return mapper.entityToPayload(getEntity(id));
    }

    public UnitEntity getEntity(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public UnitPayload update(UnitPayload payload) {
        UnitEntity entity = getEntity(payload.getId());
        if(entity!=null){
            entity.setName(Objects.nonNull(payload.getName()) ? payload.getName() : entity.getName());
            entity.setCode(Objects.nonNull(payload.getCode()) ? payload.getCode() : entity.getCode());
            return mapper.entityToPayload(repository.save(entity));
        }
        return null;
    }
}
