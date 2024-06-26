package com.catering.manager.api.business.payload;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubCategoryPayload {
    private Integer id;
    private String code;
    private String name;
    private CategoryPayload category;
}
