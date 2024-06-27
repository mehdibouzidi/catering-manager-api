package com.catering.manager.api.business.service.impl;

import com.catering.manager.api.business.common.criteria.NomenclatureCriteria;
import com.catering.manager.api.business.common.mapper.NomenclatureMapper;
import com.catering.manager.api.business.common.util.BusinessError;
import com.catering.manager.api.business.model.NomenclatureEntity;
import com.catering.manager.api.business.payload.NomenclaturePayload;
import com.catering.manager.api.business.payload.global.GlobalPayload;
import com.catering.manager.api.business.repository.NomenclatureRepository;
import com.catering.manager.api.business.service.inter.INomeclatureService;
import com.catering.manager.api.common.exception.CRUDException;
import com.catering.manager.api.common.util.CommonUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class NomenclatureService implements INomeclatureService {

    private NomenclatureRepository repository;
    private NomenclatureMapper mapper;

    @PersistenceContext
    private EntityManager entityManager;

    public NomenclatureService(NomenclatureRepository repository, NomenclatureMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public NomenclaturePayload save(NomenclaturePayload payload) {
        return mapper.entityToPayload(repository.save(mapper.payloadToEntity(payload)));
    }

    @Override
    public boolean delete(NomenclaturePayload payload) {
        try {
            repository.delete(mapper.payloadToEntity(payload));
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean deleteById(Integer id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public NomenclaturePayload findById(Integer id) {
        return mapper.entityToPayload(getEntity(id));
    }

    @Override
    public NomenclatureEntity getEntity(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public NomenclaturePayload update(NomenclaturePayload payload) {
        NomenclatureEntity entity = getEntity(payload.getId());
        if(entity!=null){
            entity.setName(Objects.nonNull(payload.getName()) ? payload.getName() : entity.getName());
            try {
                return mapper.entityToPayload(repository.save(entity));
            }catch (Exception ex){
                throw new CRUDException(BusinessError.NON_EXISTING_PRODUCT.getLibelle());
            }
        }
        return null;
    }

    @Override
    public GlobalPayload<NomenclaturePayload> findAllByCriteria(NomenclatureCriteria criteria) {

        Pageable paging = CommonUtil.pageableBuilder(criteria);

        String queryStr = CommonUtil.selectCritQueryBuilder("NomenclatureEntity", criteria.toMap(), criteria);
        Query query = entityManager.createQuery(queryStr);

        List<NomenclatureEntity> entityResultList = query.getResultList();

        return CommonUtil.globalPayloadBuilder(criteria, paging, entityResultList, mapper);
    }

    @Override
    public List<NomenclaturePayload> findAll() {
        return mapper.entityListToPayload(repository.findAll());
    }

}
