package com.catering.manager.api.business.common.criteria;

import com.catering.manager.api.common.criteria.PaginationCriteria;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UnitCriteria extends PaginationCriteria {
    private Integer id;
    private String code;
    private String name;

    public Map<String, String> toMap(){
        Map<String, String> columnsValues = new HashMap<>();
        if(Objects.nonNull(id)){
            columnsValues.put("id",id.toString());
        }
        if(Objects.nonNull(name)){
            columnsValues.put("name",name);
        }
        if(Objects.nonNull(code)){
            columnsValues.put("code",code);
        }
        return columnsValues;
    }
}
