package com.catering.manager.api.business.service.inter;

import com.catering.manager.api.business.common.criteria.SubCategoryCriteria;
import com.catering.manager.api.business.model.SubCategoryEntity;
import com.catering.manager.api.business.payload.SubCategoryPayload;
import com.catering.manager.api.business.payload.global.GlobalPayload;

import java.util.List;

public interface ISubCategoryService {

    SubCategoryPayload save(SubCategoryPayload payload);
    boolean delete(SubCategoryPayload payload);
    boolean deleteById(Integer id);
    SubCategoryPayload findById(Integer id);
    SubCategoryEntity getEntity(Integer id);
    SubCategoryPayload update(SubCategoryPayload payload);

    List<SubCategoryPayload> findAll();

    GlobalPayload<SubCategoryPayload> findAllByCriteria(SubCategoryCriteria criteria);
}
