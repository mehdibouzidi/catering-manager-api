package com.catering.manager.api.business.common.mapper;

import com.catering.manager.api.business.model.IngredientEntity;
import com.catering.manager.api.business.payload.IngredientPayload;
import com.catering.manager.api.business.repository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class IngredientMapper {

    private SubCategoryRepository subCategoryRepository;
    private SubCategoryMapper subCategoryMapper;

    @Autowired
    public IngredientMapper(SubCategoryRepository subCategoryRepository, SubCategoryMapper subCategoryMapper) {
        this.subCategoryRepository = subCategoryRepository;
        this.subCategoryMapper = subCategoryMapper;
    }


    public IngredientEntity payloadToEntity(IngredientPayload payload) {
        IngredientEntity entity = new IngredientEntity();
        entity.setId(payload.getId());
        entity.setName(payload.getName());
        entity.setSubCategory(subCategoryRepository.findById(payload.getSubCategory().getId()).orElse(null));
        return entity;
    }

    public IngredientPayload entityToPayload(IngredientEntity entity) {
        IngredientPayload payload = new IngredientPayload();
        if (Objects.nonNull(entity)) {
            payload.setId(entity.getId());
            payload.setName(entity.getName());
            payload.setSubCategory(subCategoryMapper.entityToPayload(entity.getSubCategory()));
        }

        return payload;
    }

    public List<IngredientPayload> entityListToPayload(List<IngredientEntity> entities) {
        return Objects.isNull(entities) ? null : entities.stream().map(this::entityToPayload).toList();
    }
}
