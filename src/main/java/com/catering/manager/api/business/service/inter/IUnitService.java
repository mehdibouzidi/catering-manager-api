package com.catering.manager.api.business.service.inter;

import com.catering.manager.api.business.common.criteria.UnitCriteria;
import com.catering.manager.api.business.model.UnitEntity;
import com.catering.manager.api.business.payload.UnitPayload;

import java.util.List;

public interface IUnitService {

    UnitPayload save(UnitPayload payload);
    boolean delete(UnitPayload payload);
    boolean deleteById(Integer id);
    List<UnitPayload> findAllByCriteria(UnitCriteria criteria);
    UnitPayload findById(Integer id);
    UnitEntity getEntity(Integer id);
    UnitPayload update(UnitPayload payload);
}
