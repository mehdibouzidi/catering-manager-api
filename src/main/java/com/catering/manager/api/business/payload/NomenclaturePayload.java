package com.catering.manager.api.business.payload;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NomenclaturePayload {
    private Integer id;
    private String code;
    private String name;

    private Double maxBuyPrice;

    private Double summerPrice;
    private Double autumnPrice;
    private Double winterPrice;
    private Double springPrice;

    private Boolean isSummerPrice;
    private Boolean isAutumnPrice;
    private Boolean isWinterPrice;
    private Boolean isSpringPrice;

    private ProductPayload product;
}
