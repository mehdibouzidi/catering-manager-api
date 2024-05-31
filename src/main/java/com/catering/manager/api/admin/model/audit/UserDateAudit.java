package com.catering.manager.api.admin.model.audit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@JsonIgnoreProperties(
        value = {"createdBy", "updatedBy"},
        allowGetters = true
)
@Data
public abstract class UserDateAudit extends DateAudit {

    @CreatedBy
    private Integer createdBy;

    @LastModifiedBy
    private Integer updatedBy;

}
