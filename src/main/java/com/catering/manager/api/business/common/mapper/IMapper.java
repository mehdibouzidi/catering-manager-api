package com.catering.manager.api.business.common.mapper;

import com.catering.manager.api.business.model.CategoryEntity;
import com.catering.manager.api.business.payload.CategoryPayload;

import java.util.List;

public interface IMapper<T,S>  {
    public List<T> entityListToPayload(List<S> entities);
}
