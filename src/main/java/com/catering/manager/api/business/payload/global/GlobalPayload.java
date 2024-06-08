package com.catering.manager.api.business.payload.global;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GlobalPayload {
    private Integer totalNumberOfElements;
    private Integer totalNumberOfPages;
}

