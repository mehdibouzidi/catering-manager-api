package com.catering.manager.api.business.repository;

import com.catering.manager.api.business.model.ProductTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTypeRepository extends JpaRepository<ProductTypeEntity,Integer> {

}
