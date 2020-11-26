package com.binarios.authorityBasedFieldAccess.repository;

import com.binarios.authorityBasedFieldAccess.model.MyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyEntityRepository extends JpaRepository<MyEntity, Long> {
    boolean existsByName(String name);
}
