package com.catering.manager.api.business.common.mapper;

import com.catering.manager.api.business.model.CategoryEntity;
import com.catering.manager.api.business.payload.CategoryPayload;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryMapper {

    public CategoryEntity payloadToEntity(CategoryPayload payload){
        CategoryEntity entity = new CategoryEntity();
        entity.setId(payload.getId());
        entity.setName(payload.getName());
        return entity;
    }

    public CategoryPayload entityToPayload(CategoryEntity entity){
        CategoryPayload payload = new CategoryPayload();
        payload.setId(entity.getId());
        payload.setName(entity.getName());
        return payload;
    }

    public List<CategoryPayload> entityListToPayload(List<CategoryEntity> entities){
        return entities.stream().map(this::entityToPayload).toList();
    }
}
