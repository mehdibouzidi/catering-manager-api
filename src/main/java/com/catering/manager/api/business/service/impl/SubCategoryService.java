package com.catering.manager.api.business.service.impl;

import com.catering.manager.api.business.common.mapper.SubCategoryMapper;
import com.catering.manager.api.business.common.util.BusinessError;
import com.catering.manager.api.business.model.SubCategoryEntity;
import com.catering.manager.api.business.payload.SubCategoryPayload;
import com.catering.manager.api.business.repository.SubCategoryRepository;
import com.catering.manager.api.business.service.inter.ICategoryService;
import com.catering.manager.api.business.service.inter.ISubCategoryService;
import com.catering.manager.api.common.exception.CRUDException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SubCategoryService implements ISubCategoryService {

    private SubCategoryRepository repository;
    private SubCategoryMapper mapper;

    private ICategoryService categoryService;

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
}
