package com.catering.manager.api.business.model;

import com.catering.manager.api.business.common.util.BusinessConstants;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@Table(schema = BusinessConstants.BUSINESS_SCH, name = BusinessConstants.NOMENCLATURE_TABLE)
@XmlRootElement
@Data
@NoArgsConstructor
public class NomenclatureEntity  implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private String code;
    private String name;

    private Double maxBuyPrice;

    private Double summerPrice;
    private Double autumnPrice;
    private Double winterPrice;
    private Double springPrice;

    private Boolean isSummerPrice;
    private Boolean isAutumnPrice;
    private Boolean isWinterPrice;
    private Boolean isSpringPrice;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private ProductEntity product;
}