package com.catering.manager.api.business.common.mapper;

import com.catering.manager.api.business.model.ProductTypeEntity;
import com.catering.manager.api.business.payload.ProductTypePayload;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class ProductTypeMapper implements IMapper<ProductTypePayload,ProductTypeEntity>{


    public ProductTypeEntity payloadToEntity(ProductTypePayload payload){
        ProductTypeEntity entity = new ProductTypeEntity();
        entity.setId(payload.getId());
        entity.setCode(payload.getCode());
        entity.setName(payload.getName());
        return entity;
    }

    public ProductTypePayload entityToPayload(ProductTypeEntity entity){
        ProductTypePayload payload = new ProductTypePayload();
        if (Objects.nonNull(entity)) {
            payload.setId(entity.getId());
            payload.setCode(entity.getCode());
            payload.setName(entity.getName());
        }
        return payload;
    }

    @Override
    public List<ProductTypePayload> entityListToPayload(List<ProductTypeEntity> entities){
        return Objects.isNull(entities) ? null : entities.stream().map(this::entityToPayload).toList();
    }

}
