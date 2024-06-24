package com.catering.manager.api.business.repository;

import com.catering.manager.api.business.model.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<ProductEntity,Integer> {

    @Query(value = "SELECT ingr from ProductEntity ingr WHERE (ingr.name LIKE %:name% OR ingr.name IS NULL) AND " +
            "(ingr.subCategory.id=:subCategoryId OR ingr.subCategory.id IS NULL)")
    Page<ProductEntity> findAllByCriteria(@Param("name") String name, @Param("subCategoryId") Integer subCategoryId, Pageable pageable);
}
