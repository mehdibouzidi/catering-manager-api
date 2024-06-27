package com.catering.manager.api.business.service.inter;

import com.catering.manager.api.business.common.criteria.NomenclatureCriteria;
import com.catering.manager.api.business.model.NomenclatureEntity;
import com.catering.manager.api.business.payload.NomenclaturePayload;
import com.catering.manager.api.business.payload.global.GlobalPayload;

import java.util.List;

public interface INomeclatureService {

    NomenclaturePayload save(NomenclaturePayload payload);
    boolean delete(NomenclaturePayload payload);
    boolean deleteById(Integer id);
    NomenclaturePayload findById(Integer id);
    NomenclatureEntity getEntity(Integer id);
    NomenclaturePayload update(NomenclaturePayload payload);

    GlobalPayload<NomenclaturePayload> findAllByCriteria(NomenclatureCriteria criteria);

    List<NomenclaturePayload> findAll();
}