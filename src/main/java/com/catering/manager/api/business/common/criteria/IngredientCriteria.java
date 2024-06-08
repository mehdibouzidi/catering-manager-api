package com.catering.manager.api.business.common.criteria;

import com.catering.manager.api.common.criteria.PaginationCriteria;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class IngredientCriteria extends PaginationCriteria {
    private Integer id;
    private String name;
    private Integer subCategoryId;
    private Integer unityId;
}
