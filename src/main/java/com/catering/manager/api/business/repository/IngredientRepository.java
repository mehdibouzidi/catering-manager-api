package com.catering.manager.api.business.repository;

import com.catering.manager.api.business.model.IngredientEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IngredientRepository extends JpaRepository<IngredientEntity,Integer> {

    @Query(value = "SELECT ingr from IngredientEntity ingr WHERE ingr.name LIKE %:name% OR ingr.subCategory.id=:subCategoryId")
    Page<IngredientEntity> findAllByCriteria(@Param("name") String name, @Param("subCategoryId") Integer subCategoryId, Pageable pageable);
}
