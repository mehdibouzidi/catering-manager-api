package com.catering.manager.api.business.service.inter;

import com.catering.manager.api.business.common.criteria.SubCategoryCriteria;
import com.catering.manager.api.business.model.SubCategoryEntity;
import com.catering.manager.api.business.payload.SubCategoryPayload;
import com.catering.manager.api.business.payload.global.GlobalPayload;

public interface ISubCategoryService {

    SubCategoryPayload save(SubCategoryPayload payload);
    boolean delete(SubCategoryPayload payload);
    boolean deleteById(Integer id);
    SubCategoryPayload findById(Integer id);
    SubCategoryEntity getEntity(Integer id);
    SubCategoryPayload update(SubCategoryPayload payload);

    GlobalPayload<SubCategoryPayload> findAll();

    GlobalPayload<SubCategoryPayload> findAllByCriteria(SubCategoryCriteria criteria);
}
