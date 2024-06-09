package com.catering.manager.api.business.common.mapper;

import com.catering.manager.api.business.model.CategoryEntity;
import com.catering.manager.api.business.payload.CategoryPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class CategoryMapper implements IMapper<CategoryPayload,CategoryEntity>{

    private SubCategoryMapper subCategoryMapper;

    @Autowired
    public CategoryMapper(SubCategoryMapper subCategoryMapper) {
        this.subCategoryMapper = subCategoryMapper;
    }

    public CategoryEntity payloadToEntity(CategoryPayload payload){
        CategoryEntity entity = new CategoryEntity();
        entity.setId(payload.getId());
        entity.setName(payload.getName());
        return entity;
    }

    public CategoryPayload entityToPayload(CategoryEntity entity){
        CategoryPayload payload = new CategoryPayload();
        if (Objects.nonNull(entity)) {
            payload.setId(entity.getId());
            payload.setName(entity.getName());
            payload.setSubCategories(subCategoryMapper.entityListToPayload(entity.getSubCategories()));
        }
        return payload;
    }

    @Override
    public List<CategoryPayload> entityListToPayload(List<CategoryEntity> entities){
        return Objects.isNull(entities) ? null : entities.stream().map(this::entityToPayload).toList();
    }

}
