package com.catering.manager.api.business.service.impl;

import com.catering.manager.api.business.common.criteria.IngredientCriteria;
import com.catering.manager.api.business.common.mapper.IngredientMapper;
import com.catering.manager.api.business.common.util.BusinessError;
import com.catering.manager.api.business.model.IngredientEntity;
import com.catering.manager.api.business.payload.IngredientPayload;
import com.catering.manager.api.business.repository.IngredientRepository;
import com.catering.manager.api.business.service.inter.IIngredientService;
import com.catering.manager.api.business.service.inter.ISubCategoryService;
import com.catering.manager.api.common.constant.CommonConstants;
import com.catering.manager.api.common.exception.CRUDException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class IngredientService implements IIngredientService {

    private IngredientRepository repository;
    private IngredientMapper mapper;

    private ISubCategoryService subCategoryService;

    public IngredientService(IngredientRepository repository, IngredientMapper mapper, ISubCategoryService subCategoryService) {
        this.repository = repository;
        this.mapper = mapper;
        this.subCategoryService = subCategoryService;
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
            entity.setName(payload.getName());
            try {
                entity.setSubCategory(subCategoryService.getEntity(payload.getSubCategory().getId()));
                return mapper.entityToPayload(repository.save(entity));
            }catch (Exception ex){
                throw new CRUDException(BusinessError.NON_EXISTING_SUB_CATEGORY.getLibelle());
            }
        }
        return null;
    }

    @Override
    public List<IngredientPayload> findAllByCriteria(IngredientCriteria criteria) {
        Sort sort = Sort.by(Sort.Order.desc("name"));
        String sortCriteria = criteria.getSort();
        String sortColumnCriteria = criteria.getSortColumn();
        if (!sortCriteria.isEmpty()) {
            sort = Sort.by(
                    sortCriteria.equals(CommonConstants.ASC) ? Sort.Order.asc(sortColumnCriteria) : Sort.Order.desc(sortColumnCriteria)
            );
        }
        Pageable paging = PageRequest.of(criteria.getPages(), criteria.getSize(), sort);
        Page<IngredientEntity> categoryPage =
                repository.findAllByCriteria(criteria.getName(), criteria.getSubCategoryId(), paging);
        List<IngredientPayload> result = mapper.entityListToPayload(categoryPage.getContent());
        return result;
    }

}
