package com.catering.manager.api.business.service.inter;

import com.catering.manager.api.business.common.criteria.ProductTypeCriteria;
import com.catering.manager.api.business.model.ProductTypeEntity;
import com.catering.manager.api.business.payload.ProductTypePayload;
import com.catering.manager.api.business.payload.global.GlobalPayload;

import java.util.List;

public interface IProductTypeService {

    ProductTypePayload save(ProductTypePayload payload);
    boolean delete(ProductTypePayload payload);
    boolean deleteById(Integer id);
    GlobalPayload<ProductTypePayload> findAllByCriteria(ProductTypeCriteria criteria);
    List<ProductTypePayload> findAll();
    ProductTypePayload findById(Integer id);
    ProductTypeEntity getEntity(Integer id);
    ProductTypePayload update(ProductTypePayload payload);
}
