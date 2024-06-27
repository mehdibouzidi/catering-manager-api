package com.catering.manager.api.business.service.inter;

import com.catering.manager.api.business.common.criteria.UnitCriteria;
import com.catering.manager.api.business.model.UnitEntity;
import com.catering.manager.api.business.payload.UnitPayload;
import com.catering.manager.api.business.payload.global.GlobalPayload;

import java.util.List;

public interface IUnitService {

    UnitPayload save(UnitPayload payload);
    boolean delete(UnitPayload payload);
    boolean deleteById(Integer id);
    GlobalPayload<UnitPayload> findAllByCriteria(UnitCriteria criteria);
    UnitPayload findById(Integer id);
    UnitEntity getEntity(Integer id);
    UnitPayload update(UnitPayload payload);

    List<UnitPayload> findAll();
}
