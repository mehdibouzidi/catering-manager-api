package com.catering.manager.api.business.payload;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UnitPayload {
    private Integer id;
    private String code;
    private String name;
}
