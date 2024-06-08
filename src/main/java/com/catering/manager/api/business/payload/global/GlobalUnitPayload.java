package com.catering.manager.api.business.payload.global;

import com.catering.manager.api.business.payload.UnitPayload;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GlobalUnitPayload extends GlobalPayload{
    List<UnitPayload> units;
}
