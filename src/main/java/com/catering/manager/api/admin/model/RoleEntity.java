package com.catering.manager.api.admin.model;

import com.thebooknation.admin.common.enums.RoleName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import static com.thebooknation.admin.common.util.AdminConstants.ADMIN_SCHEMA;
import static com.thebooknation.admin.common.util.AdminConstants.ROLE_TABLE;

@Entity
@Table(schema = ADMIN_SCHEMA,name = ROLE_TABLE)
@XmlRootElement
@Data
@ToString(of = "id", doNotUseGetters = true)
@EqualsAndHashCode(of = "id", doNotUseGetters = true)
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private RoleName name;
}
