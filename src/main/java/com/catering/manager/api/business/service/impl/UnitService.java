package com.catering.manager.api.business.service.impl;

import com.catering.manager.api.business.common.criteria.UnitCriteria;
import com.catering.manager.api.business.common.mapper.UnitMapper;
import com.catering.manager.api.business.model.UnitEntity;
import com.catering.manager.api.business.payload.UnitPayload;
import com.catering.manager.api.business.repository.UnitRepository;
import com.catering.manager.api.business.service.inter.IUnitService;
import com.catering.manager.api.common.constant.CommonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitService implements IUnitService {

    private UnitRepository repository;
    private UnitMapper mapper;
    
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
    public List<UnitPayload> findAllByCriteria(UnitCriteria criteria) {
        Sort sort = Sort.by(Sort.Order.desc("name"));
        String sortCriteria = criteria.getSort();
        String sortColumnCriteria = criteria.getSortColumn();
        if (!sortCriteria.isEmpty()) {
            sort = Sort.by(
                    sortCriteria.equals(CommonConstants.ASC) ? Sort.Order.asc(sortColumnCriteria) : Sort.Order.desc(sortColumnCriteria)
            );
        }
        Pageable paging = PageRequest.of(criteria.getPages(), criteria.getSize(), sort);
        Page<UnitEntity> categoryPage =
                repository.findAllByCriteria(criteria.getName(), criteria.getCode(), paging);
        List<UnitPayload> result = mapper.entityListToPayload(categoryPage.getContent());
        return result;
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
