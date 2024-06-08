package com.catering.manager.api.business.repository;

import com.catering.manager.api.business.model.CategoryEntity;
import com.catering.manager.api.business.model.UnitEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitRepository extends JpaRepository<UnitEntity,Integer> {

    @Query(value = "SELECT un from UnitEntity un WHERE un.name LIKE %:name% OR un.code LIKE %:code%")
    Page<UnitEntity> findAllByCriteria(@Param("name") String name,@Param("code") String code, Pageable pageable);
}
