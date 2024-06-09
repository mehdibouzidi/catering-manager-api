package com.catering.manager.api.business.payload.global;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GlobalUnitPayload<T> extends GlobalPayload{
    List<T> units;
}
