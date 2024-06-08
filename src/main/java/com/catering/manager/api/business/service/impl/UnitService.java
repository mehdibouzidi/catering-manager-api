package com.catering.manager.api.business.service.impl;

import com.catering.manager.api.business.common.criteria.UnitCriteria;
import com.catering.manager.api.business.common.mapper.UnitMapper;
import com.catering.manager.api.business.model.UnitEntity;
import com.catering.manager.api.business.payload.UnitPayload;
import com.catering.manager.api.business.payload.global.GlobalUnitPayload;
import com.catering.manager.api.business.repository.UnitRepository;
import com.catering.manager.api.business.service.inter.IUnitService;
import com.catering.manager.api.common.constant.CommonConstants;
import com.catering.manager.api.common.util.CommonUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class UnitService implements IUnitService {

    private UnitRepository repository;
    private UnitMapper mapper;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public UnitService(UnitRepository repository, UnitMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public UnitPayload save(UnitPayload payload) {
        return mapper.entityToPayload(repository.save(mapper.payloadToEntity(payload)));
    }

    @Override
    public boolean delete(UnitPayload payload) {
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
    public GlobalUnitPayload findAllByCriteria(UnitCriteria criteria) {
        GlobalUnitPayload globalPayload = new GlobalUnitPayload();

        Sort sort = Sort.by(Sort.Order.desc("name"));
        String sortCriteria = criteria.getSort();
        String sortColumnCriteria = criteria.getSortColumn();
        if (!sortCriteria.isEmpty()) {
            sort = Sort.by(
                    sortCriteria.equals(CommonConstants.ASC) ? Sort.Order.asc(sortColumnCriteria) : Sort.Order.desc(sortColumnCriteria)
            );
        }
        Pageable paging = PageRequest.of(criteria.getPages(), criteria.getSize(), sort);

        String queryStr = "SELECT un from UnitEntity un " +
                ( Objects.nonNull(criteria.getName()) || Objects.nonNull(criteria.getCode()) ? " WHERE " : Strings.EMPTY )+
                ( Objects.nonNull(criteria.getName()) ? " LOWER(un.name) LIKE '%"+ criteria.getName().toLowerCase() +"%' AND "  : Strings.EMPTY )+
                ( Objects.nonNull(criteria.getCode()) ? " LOWER(un.code) LIKE '%"+ criteria.getCode().toLowerCase() +"%' "  : Strings.EMPTY );
        queryStr = CommonUtil.cleanQueryConditions(queryStr);
        //SORT
        String orderBy = (Strings.isBlank(criteria.getSortColumn())) ? "id": criteria.getSortColumn();
        String sortType = (Strings.isBlank(criteria.getSortColumn())) ? " ASC" : " "+criteria.getSort();
        queryStr+=(" ORDER BY un."+ orderBy + sortType);
        Query query = entityManager.createQuery(queryStr);

        List<UnitEntity> entityResultList = query.getResultList();

        int totalNumberOfElements = entityResultList.size();
        List<UnitPayload> result = Arrays.asList();
        if(totalNumberOfElements>0){
            final int start = (int)paging.getOffset();
            final int end = Math.min((start + paging.getPageSize()), entityResultList.size());

            Page<UnitEntity> bookEntityPage = new PageImpl<>(entityResultList.subList(start,end), paging,criteria.getSize());

            result = mapper.entityListToPayload(bookEntityPage.getContent());
        }

        globalPayload.setUnits(result);
        globalPayload.setTotalNumberOfElements(totalNumberOfElements);
        globalPayload.setTotalNumberOfPages(CommonUtil.calculateNumberOfPages(totalNumberOfElements, criteria.getSize()));

        return globalPayload;
    }


    @Override
    public UnitPayload findById(Integer id) {
        return mapper.entityToPayload(getEntity(id));
    }

    public UnitEntity getEntity(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public UnitPayload update(UnitPayload payload) {
        UnitEntity entity = getEntity(payload.getId());
        if(entity!=null){
            entity.setName(payload.getName());
            return mapper.entityToPayload(repository.save(entity));
        }
        return null;
    }
}
