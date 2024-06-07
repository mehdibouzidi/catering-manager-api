package com.catering.manager.api.business.model;

import com.catering.manager.api.business.common.util.BusinessConstants;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@Table(schema = BusinessConstants.BUSINESS_SCH, name = BusinessConstants.SUB_CATEGORY_TABLE)
@XmlRootElement
@Data
@NoArgsConstructor
public class SubCategoryEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private String name;

    @JoinColumn(name = "category_fk", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private CategoryEntity category;

}
