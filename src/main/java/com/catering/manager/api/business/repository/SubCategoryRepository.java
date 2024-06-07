package com.catering.manager.api.business.repository;

import com.catering.manager.api.business.model.SubCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategoryEntity,Integer> {
}
