package com.catering.manager.api.business.service.impl;

import com.catering.manager.api.business.common.criteria.IngredientCriteria;
import com.catering.manager.api.business.common.mapper.IngredientMapper;
import com.catering.manager.api.business.common.util.BusinessError;
import com.catering.manager.api.business.model.IngredientEntity;
import com.catering.manager.api.business.payload.IngredientPayload;
import com.catering.manager.api.business.payload.global.GlobalPayload;
import com.catering.manager.api.business.repository.IngredientRepository;
import com.catering.manager.api.business.service.inter.IIngredientService;
import com.catering.manager.api.business.service.inter.ISubCategoryService;
import com.catering.manager.api.business.service.inter.IUnitService;
import com.catering.manager.api.common.exception.CRUDException;
import com.catering.manager.api.common.util.CommonUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class IngredientService implements IIngredientService {

    private IngredientRepository repository;
    private IngredientMapper mapper;

    private ISubCategoryService subCategoryService;
    private IUnitService unitService;

    @PersistenceContext
    private EntityManager entityManager;

    public IngredientService(IngredientRepository repository, IngredientMapper mapper, ISubCategoryService subCategoryService, IUnitService unitService) {
        this.repository = repository;
        this.mapper = mapper;
        this.subCategoryService = subCategoryService;
        this.unitService = unitService;
    }

    @Override
    public IngredientPayload save(IngredientPayload payload) {
        return mapper.entityToPayload(repository.save(mapper.payloadToEntity(payload)));
    }

    @Override
    public boolean delete(IngredientPayload payload) {
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
    public IngredientPayload findById(Integer id) {
        return mapper.entityToPayload(getEntity(id));
    }

    @Override
    public IngredientEntity getEntity(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public IngredientPayload update(IngredientPayload payload) {
        IngredientEntity entity = getEntity(payload.getId());
        if(entity!=null){
            entity.setName(Objects.nonNull(payload.getName()) ? payload.getName() : entity.getName());
            try {
                entity.setSubCategory(subCategoryService.getEntity(payload.getSubCategory().getId()));
                entity.setUnit(unitService.getEntity(payload.getUnit().getId()));
                return mapper.entityToPayload(repository.save(entity));
            }catch (Exception ex){
                throw new CRUDException(BusinessError.NON_EXISTING_SUB_CATEGORY.getLibelle());
            }
        }
        return null;
    }

    @Override
    public GlobalPayload<IngredientPayload> findAllByCriteria(IngredientCriteria criteria) {

        Pageable paging = CommonUtil.pageableBuilder(criteria);

        String queryStr = CommonUtil.selectCritQueryBuilder("IngredientEntity", criteria.toMap(), criteria);
        Query query = entityManager.createQuery(queryStr);

        List<IngredientEntity> entityResultList = query.getResultList();

        return CommonUtil.globalPayloadBuilder(criteria, paging, entityResultList, mapper);
    }

}
