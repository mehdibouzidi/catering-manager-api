package com.catering.manager.api.business.service.inter;

import com.catering.manager.api.business.common.criteria.IngredientCriteria;
import com.catering.manager.api.business.model.IngredientEntity;
import com.catering.manager.api.business.model.SubCategoryEntity;
import com.catering.manager.api.business.payload.CategoryPayload;
import com.catering.manager.api.business.payload.IngredientPayload;
import com.catering.manager.api.business.payload.global.GlobalPayload;

import java.util.List;

public interface IIngredientService {

    IngredientPayload save(IngredientPayload payload);
    boolean delete(IngredientPayload payload);
    boolean deleteById(Integer id);
    IngredientPayload findById(Integer id);
    IngredientEntity getEntity(Integer id);
    IngredientPayload update(IngredientPayload payload);

    GlobalPayload<IngredientPayload> findAllByCriteria(IngredientCriteria criteria);
}
