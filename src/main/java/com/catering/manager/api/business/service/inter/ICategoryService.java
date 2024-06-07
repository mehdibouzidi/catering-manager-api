package com.catering.manager.api.business.service.inter;

import com.catering.manager.api.business.common.criteria.CategoryCriteria;
import com.catering.manager.api.business.model.CategoryEntity;
import com.catering.manager.api.business.payload.CategoryPayload;

import java.util.List;

public interface ICategoryService {

    CategoryPayload save(CategoryPayload payload);
    boolean delete(CategoryPayload payload);
    boolean deleteById(Integer id);
    List<CategoryPayload> findAllByCriteria(CategoryCriteria criteria);
    List<CategoryPayload> findAll();
    CategoryPayload findById(Integer id);
    CategoryEntity getEntity(Integer id);
    CategoryPayload update(CategoryPayload payload);
}
