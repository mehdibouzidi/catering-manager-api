package com.catering.manager.api.business.model;

import com.catering.manager.api.business.common.util.BusinessConstants;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(schema = BusinessConstants.BUSINESS_SCH, name = BusinessConstants.CATEGORY_TABLE)
@XmlRootElement
@Data
@NoArgsConstructor
public class CategoryEntity  implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private String code;
    private String name;

    @OneToMany(
            mappedBy = "category",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<SubCategoryEntity> subCategories;

}
