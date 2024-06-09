package com.catering.manager.api.business.payload.global;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GlobalPayload<T> {
    private Integer totalNumberOfElements;
    private Integer totalNumberOfPages;
    private List<T> elements;
}

