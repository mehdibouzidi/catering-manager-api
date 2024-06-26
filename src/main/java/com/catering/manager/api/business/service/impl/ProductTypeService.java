package com.catering.manager.api.business.service.impl;

import com.catering.manager.api.business.common.criteria.ProductTypeCriteria;
import com.catering.manager.api.business.common.mapper.ProductTypeMapper;
import com.catering.manager.api.business.model.ProductTypeEntity;
import com.catering.manager.api.business.payload.ProductTypePayload;
import com.catering.manager.api.business.payload.global.GlobalPayload;
import com.catering.manager.api.business.repository.ProductTypeRepository;
import com.catering.manager.api.business.service.inter.IProductTypeService;
import com.catering.manager.api.common.util.CommonUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProductTypeService implements IProductTypeService {

    private ProductTypeRepository repository;
    private ProductTypeMapper mapper;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public ProductTypeService(ProductTypeRepository repository, ProductTypeMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ProductTypePayload save(ProductTypePayload payload) {
        return mapper.entityToPayload(repository.save(mapper.payloadToEntity(payload)));
    }

    @Override
    public boolean delete(ProductTypePayload payload) {
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
    public GlobalPayload<ProductTypePayload> findAllByCriteria(ProductTypeCriteria criteria) {

        Pageable paging = CommonUtil.pageableBuilder(criteria);

        String queryStr = CommonUtil.selectCritQueryBuilder("ProductTypeEntity", criteria.toMap(), criteria);
        Query query = entityManager.createQuery(queryStr);

        List<ProductTypeEntity> entityResultList = query.getResultList();

        return CommonUtil.globalPayloadBuilder(criteria, paging, entityResultList, mapper);
    }

    @Override
    public List<ProductTypePayload> findAll() {
        return mapper.entityListToPayload(repository.findAll());
    }

    @Override
    public ProductTypePayload findById(Integer id) {
        return mapper.entityToPayload(getEntity(id));
    }

    public ProductTypeEntity getEntity(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public ProductTypePayload update(ProductTypePayload payload) {
        ProductTypeEntity entity = getEntity(payload.getId());
        if(entity!=null){
            entity.setName(Objects.nonNull(payload.getName()) ? payload.getName() : entity.getName());
            return mapper.entityToPayload(repository.save(entity));
        }
        return null;
    }
}
