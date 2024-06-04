package com.catering.manager.api.business.service.impl;

import com.catering.manager.api.business.common.criteria.CategoryCriteria;
import com.catering.manager.api.business.common.mapper.CategoryMapper;
import com.catering.manager.api.business.model.CategoryEntity;
import com.catering.manager.api.business.payload.CategoryPayload;
import com.catering.manager.api.business.repository.CategoryRepository;
import com.catering.manager.api.business.service.inter.ICategoryService;
import com.catering.manager.api.common.constant.CommonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {

    private CategoryRepository repository;
    private CategoryMapper mapper;

    @Autowired
    public CategoryService(CategoryRepository repository, CategoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public CategoryPayload save(CategoryPayload payload) {
        return mapper.entityToPayload(repository.save(mapper.payloadToEntity(payload)));
    }

    @Override
    public boolean delete(CategoryPayload payload) {
        try {
            repository.delete(mapper.payloadToEntity(payload));
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean deleteById(Integer id) {
        return deleteById(id);
    }

    @Override
    public List<CategoryPayload> findAllByCriteria(CategoryCriteria criteria) {
        Sort sort = Sort.by(Sort.Order.desc("name"));
        String sortCriteria = criteria.getSort();
        String sortColumnCriteria = criteria.getSortColumn();
        if (!sortCriteria.isEmpty()) {
            sort = Sort.by(
                    sortCriteria.equals(CommonConstants.ASC) ? Sort.Order.asc(sortColumnCriteria) : Sort.Order.desc(sortColumnCriteria)
            );
        }
        Pageable paging = PageRequest.of(criteria.getPages(), criteria.getSize(), sort);
        Page<CategoryEntity> categoryPage =
                repository.findAllByCriteria(criteria.getName(), paging);
        List<CategoryPayload> result = mapper.entityListToPayload(categoryPage.getContent());
        return result;
    }

    @Override
    public List<CategoryPayload> findAll() {
        return mapper.entityListToPayload(repository.findAll());
    }

    @Override
    public CategoryPayload findById(Integer id) {
        return mapper.entityToPayload(repository.findById(id).orElse(null));
    }

    @Override
    public CategoryPayload update(CategoryPayload payload) {
        CategoryEntity entity = repository.findById(payload.getId()).orElse(null);
        if(entity!=null){
            entity.setName(payload.getName());
            return mapper.entityToPayload(repository.save(entity));
        }
        return null;
    }
}
