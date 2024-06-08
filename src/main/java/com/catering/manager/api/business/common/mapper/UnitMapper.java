package com.catering.manager.api.business.common.mapper;

import com.catering.manager.api.business.model.UnitEntity;
import com.catering.manager.api.business.payload.UnitPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class UnitMapper {


    public UnitEntity payloadToEntity(UnitPayload payload){
        UnitEntity entity = new UnitEntity();
        entity.setId(payload.getId());
        entity.setCode(payload.getCode());
        entity.setName(payload.getName());
        return entity;
    }

    public UnitPayload entityToPayload(UnitEntity entity){
        UnitPayload payload = new UnitPayload();
        if (Objects.nonNull(entity)) {
            payload.setId(entity.getId());
            payload.setCode(entity.getCode());
            payload.setName(entity.getName());
        }
        return payload;
    }

    public List<UnitPayload> entityListToPayload(List<UnitEntity> entities){
        return Objects.isNull(entities) ? null : entities.stream().map(this::entityToPayload).toList();
    }
}
