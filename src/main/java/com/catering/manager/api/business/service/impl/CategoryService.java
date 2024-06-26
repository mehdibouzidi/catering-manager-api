package com.catering.manager.api.business.service.impl;

import com.catering.manager.api.business.common.criteria.CategoryCriteria;
import com.catering.manager.api.business.common.mapper.CategoryMapper;
import com.catering.manager.api.business.model.CategoryEntity;
import com.catering.manager.api.business.payload.CategoryPayload;
import com.catering.manager.api.business.payload.SubCategoryPayload;
import com.catering.manager.api.business.payload.global.GlobalPayload;
import com.catering.manager.api.business.repository.CategoryRepository;
import com.catering.manager.api.business.service.inter.ICategoryService;
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
public class CategoryService implements ICategoryService {

    private CategoryRepository repository;
    private CategoryMapper mapper;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public CategoryService(CategoryRepository repository, CategoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public CategoryPayload save(CategoryPayload payload) {
        return mapper.entityToPayload(repository.save(mapper.payloadToEntity(payload)));
    }

    @Override
    public boolean delete(CategoryPayload payload) {
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
    public GlobalPayload<CategoryPayload> findAllByCriteria(CategoryCriteria criteria) {
        Pageable paging = CommonUtil.pageableBuilder(criteria);

        String queryStr = CommonUtil.selectCritQueryBuilder("CategoryEntity", criteria.toMap(), criteria);
        Query query = entityManager.createQuery(queryStr);

        List<CategoryEntity> entityResultList = query.getResultList();

        return CommonUtil.globalPayloadBuilder(criteria, paging, entityResultList, mapper);
    }

    @Override
    public List<CategoryPayload> findAll() {
        return mapper.entityListToPayload(repository.findAll());
    }

    @Override
    public CategoryPayload findById(Integer id) {
        return mapper.entityToPayload(getEntity(id));
    }

    public CategoryEntity getEntity(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public CategoryPayload update(CategoryPayload payload) {
        CategoryEntity entity = getEntity(payload.getId());
        if(entity!=null){
            entity.setName(Objects.nonNull(payload.getName()) ? payload.getName() : entity.getName());
            return mapper.entityToPayload(repository.save(entity));
        }
        return null;
    }
}
