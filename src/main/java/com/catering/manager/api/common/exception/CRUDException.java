package com.catering.manager.api.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CRUDException extends RuntimeException{
    private String description;
}
