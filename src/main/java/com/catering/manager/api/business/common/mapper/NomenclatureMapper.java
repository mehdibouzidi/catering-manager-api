package com.catering.manager.api.business.common.mapper;

import com.catering.manager.api.business.model.NomenclatureEntity;
import com.catering.manager.api.business.payload.NomenclaturePayload;
import com.catering.manager.api.business.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class NomenclatureMapper implements IMapper<NomenclaturePayload, NomenclatureEntity>{

    private ProductRepository productRepository;
    private ProductMapper productMapper;

    @Autowired
    public NomenclatureMapper(ProductRepository productRepository, ProductMapper productMapper) {

        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }


    public NomenclatureEntity payloadToEntity(NomenclaturePayload payload) {
        NomenclatureEntity entity = new NomenclatureEntity();
        entity.setId(payload.getId());
        entity.setCode(payload.getCode());
        entity.setName(payload.getName());

        entity.setMaxBuyPrice(payload.getMaxBuyPrice());

        entity.setSummerPrice(payload.getSummerPrice());
        entity.setAutumnPrice(payload.getAutumnPrice());
        entity.setWinterPrice(payload.getWinterPrice());
        entity.setSpringPrice(payload.getSpringPrice());

        entity.setIsSummerPrice(payload.getIsSummerPrice());
        entity.setIsAutumnPrice(payload.getIsAutumnPrice());
        entity.setIsWinterPrice(payload.getIsWinterPrice());
        entity.setIsSpringPrice(payload.getIsSpringPrice());

        entity.setProduct(productRepository.findById(payload.getProduct().getId()).orElse(null));
        return entity;
    }

    public NomenclaturePayload entityToPayload(NomenclatureEntity entity) {
        NomenclaturePayload payload = new NomenclaturePayload();
        if (Objects.nonNull(entity)) {
            payload.setId(entity.getId());
            payload.setCode(entity.getCode());
            payload.setName(entity.getName());

            payload.setMaxBuyPrice(entity.getMaxBuyPrice());

            payload.setSummerPrice(entity.getSummerPrice());
            payload.setAutumnPrice(entity.getAutumnPrice());
            payload.setWinterPrice(entity.getWinterPrice());
            payload.setSpringPrice(entity.getSpringPrice());

            payload.setIsSummerPrice(entity.getIsSummerPrice());
            payload.setIsAutumnPrice(entity.getIsAutumnPrice());
            payload.setIsWinterPrice(entity.getIsWinterPrice());
            payload.setIsSpringPrice(entity.getIsSpringPrice());

            payload.setProduct(productMapper.entityToPayload(entity.getProduct()));
        }

        return payload;
    }

    @Override
    public List<NomenclaturePayload> entityListToPayload(List<NomenclatureEntity> entities) {
        return Objects.isNull(entities) ? null : entities.stream().map(this::entityToPayload).toList();
    }
}