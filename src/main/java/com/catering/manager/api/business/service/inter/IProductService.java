package com.catering.manager.api.business.service.inter;

import com.catering.manager.api.business.common.criteria.ProductCriteria;
import com.catering.manager.api.business.model.ProductEntity;
import com.catering.manager.api.business.payload.ProductPayload;
import com.catering.manager.api.business.payload.global.GlobalPayload;

import java.util.List;

public interface IProductService {

    ProductPayload save(ProductPayload payload);
    boolean delete(ProductPayload payload);
    boolean deleteById(Integer id);
    ProductPayload findById(Integer id);
    ProductEntity getEntity(Integer id);
    ProductPayload update(ProductPayload payload);

    GlobalPayload<ProductPayload> findAllByCriteria(ProductCriteria criteria);

    List<ProductPayload> findAll();
}
