package com.catering.manager.api.business.service.impl;

import com.catering.manager.api.business.common.criteria.SubCategoryCriteria;
import com.catering.manager.api.business.common.mapper.SubCategoryMapper;
import com.catering.manager.api.business.common.util.BusinessError;
import com.catering.manager.api.business.model.SubCategoryEntity;
import com.catering.manager.api.business.payload.SubCategoryPayload;
import com.catering.manager.api.business.payload.global.GlobalPayload;
import com.catering.manager.api.business.repository.SubCategoryRepository;
import com.catering.manager.api.business.service.inter.ICategoryService;
import com.catering.manager.api.business.service.inter.ISubCategoryService;
import com.catering.manager.api.common.exception.CRUDException;
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
public class SubCategoryService implements ISubCategoryService {

    private SubCategoryRepository repository;
    private SubCategoryMapper mapper;

    private ICategoryService categoryService;


    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public SubCategoryService(SubCategoryRepository repository, SubCategoryMapper mapper, ICategoryService categoryService) {
        this.repository = repository;
        this.mapper = mapper;
        this.categoryService = categoryService;
    }

    @Override
    public SubCategoryPayload save(SubCategoryPayload payload) {
        try {
            return mapper.entityToPayload(repository.save(mapper.payloadToEntity(payload)));
        }catch (Exception ex){
            throw new CRUDException(BusinessError.NON_EXISTING_CATEGORY.getLibelle());
        }
    }

    @Override
    public boolean delete(SubCategoryPayload payload) {
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
    public SubCategoryPayload findById(Integer id) {
        return mapper.entityToPayload(getEntity(id));
    }

    public SubCategoryEntity getEntity(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public SubCategoryPayload update(SubCategoryPayload payload) {
        SubCategoryEntity entity = getEntity(payload.getId());
        if(entity!=null){
            entity.setName(Objects.nonNull(payload.getName()) ? payload.getName() : entity.getName());
            entity.setCategory(categoryService.getEntity(payload.getCategory().getId()));
            return mapper.entityToPayload(repository.save(entity));
        }
        return null;
    }

    @Override
    public List<SubCategoryPayload> findAll() {
        return mapper.entityListToPayload(repository.findAll());
    }

    @Override
    public GlobalPayload<SubCategoryPayload> findAllByCriteria(SubCategoryCriteria criteria) {

        Pageable paging = CommonUtil.pageableBuilder(criteria);

        String queryStr = CommonUtil.selectCritQueryBuilder("SubCategoryEntity", criteria.toMap(), criteria);
        Query query = entityManager.createQuery(queryStr);

        List<SubCategoryEntity> entityResultList = query.getResultList();

        return CommonUtil.globalPayloadBuilder(criteria, paging, entityResultList, mapper);
    }
}
