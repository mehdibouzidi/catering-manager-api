package com.catering.manager.api.business.common.mapper;

import com.catering.manager.api.business.model.SubCategoryEntity;
import com.catering.manager.api.business.payload.SubCategoryPayload;
import com.catering.manager.api.business.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class SubCategoryMapper {

    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;

    @Autowired
    public SubCategoryMapper(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }


    public SubCategoryEntity payloadToEntity(SubCategoryPayload payload) {
        SubCategoryEntity entity = new SubCategoryEntity();
        entity.setId(payload.getId());
        entity.setName(payload.getName());
        entity.setCategory(categoryRepository.findById(payload.getCategory().getId()).orElse(null));
        return entity;
    }

    public SubCategoryPayload entityToPayload(SubCategoryEntity entity) {
        SubCategoryPayload payload = new SubCategoryPayload();
        if (Objects.nonNull(entity)) {
            payload.setId(entity.getId());
            payload.setName(entity.getName());
            payload.setCategory(categoryMapper.entityToPayload(entity.getCategory()));
        }

        return payload;
    }

    public List<SubCategoryPayload> entityListToPayload(List<SubCategoryEntity> entities) {
        return Objects.isNull(entities) ? null : entities.stream().map(this::entityToPayload).toList();
    }
}
