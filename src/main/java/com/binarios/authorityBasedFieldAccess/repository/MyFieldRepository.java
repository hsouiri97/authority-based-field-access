package com.binarios.authorityBasedFieldAccess.repository;

import com.binarios.authorityBasedFieldAccess.model.MyField;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyFieldRepository extends JpaRepository<MyField, Long> {
}
