package com.catering.manager.api.business.common.criteria;

import com.catering.manager.api.common.criteria.PaginationCriteria;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NomenclatureCriteria extends PaginationCriteria {
    private Integer id;
    private String code;
    private String name;
    private String productCode;
    private String productName;

    public Map<String, String> toMap(){
        Map<String, String> columnsValues = new HashMap<>();
        if(Objects.nonNull(id)){
            columnsValues.put("id",id.toString());
        }
        if(Objects.nonNull(code)){
            columnsValues.put("name",code);
        }
        if(Objects.nonNull(name)){
            columnsValues.put("name",name);
        }
        if(Objects.nonNull(productCode)){
            columnsValues.put("product.code",productCode);
        }
        if(Objects.nonNull(productName)){
            columnsValues.put("product.name",productName);
        }
        return columnsValues;
    }
}
