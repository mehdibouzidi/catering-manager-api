package com.catering.manager.api.business.common.mapper;

import com.catering.manager.api.business.model.ProductEntity;
import com.catering.manager.api.business.payload.ProductPayload;
import com.catering.manager.api.business.repository.SubCategoryRepository;
import com.catering.manager.api.business.repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class ProductMapper implements IMapper<ProductPayload, ProductEntity>{

    private SubCategoryRepository subCategoryRepository;
    private UnitRepository unitRepository;
    private SubCategoryMapper subCategoryMapper;
    private UnitMapper unitMapper;

    @Autowired
    public ProductMapper(SubCategoryRepository subCategoryRepository, UnitRepository unitRepository, SubCategoryMapper subCategoryMapper, UnitMapper unitMapper) {
        this.subCategoryRepository = subCategoryRepository;
        this.unitRepository = unitRepository;
        this.subCategoryMapper = subCategoryMapper;
        this.unitMapper = unitMapper;
    }


    public ProductEntity payloadToEntity(ProductPayload payload) {
        ProductEntity entity = new ProductEntity();
        entity.setId(payload.getId());
        entity.setCode(payload.getCode());
        entity.setName(payload.getName());
        entity.setSubCategory(subCategoryRepository.findById(payload.getSubCategory().getId()).orElse(null));
        entity.setUnit(unitRepository.findById(payload.getUnit().getId()).orElse(null));
        return entity;
    }

    public ProductPayload entityToPayload(ProductEntity entity) {
        ProductPayload payload = new ProductPayload();
        if (Objects.nonNull(entity)) {
            payload.setId(entity.getId());
            payload.setCode(entity.getCode());
            payload.setName(entity.getName());
            payload.setSubCategory(subCategoryMapper.entityToPayload(entity.getSubCategory()));
            payload.setUnit(unitMapper.entityToPayload(entity.getUnit()));
        }

        return payload;
    }

    @Override
    public List<ProductPayload> entityListToPayload(List<ProductEntity> entities) {
        return Objects.isNull(entities) ? null : entities.stream().map(this::entityToPayload).toList();
    }
}
