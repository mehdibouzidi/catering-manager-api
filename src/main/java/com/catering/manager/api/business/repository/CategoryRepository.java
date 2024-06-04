package com.catering.manager.api.business.repository;

import com.catering.manager.api.business.model.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository  extends JpaRepository<CategoryEntity,Integer> {

    @Query(value = "SELECT cat from CategoryEntity cat WHERE cat.name LIKE %:name%")
    Page<CategoryEntity> findAllByCriteria(@Param("name") String name, Pageable pageable);
}
