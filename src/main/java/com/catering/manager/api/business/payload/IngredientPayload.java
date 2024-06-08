package com.catering.manager.api.business.payload;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class IngredientPayload {
    private Integer id;
    private String name;
    private SubCategoryPayload subCategory;
    private UnitPayload unit;
}
