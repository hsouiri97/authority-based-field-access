package com.binarios.authorityBasedFieldAccess.repository;

import java.util.Optional;

import com.binarios.authorityBasedFieldAccess.model.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.binarios.authorityBasedFieldAccess.model.RoleName;

@Repository
public interface RoleRepository extends JpaRepository<AppRole, Long> {
    Optional<AppRole> findByName(RoleName roleName);
}