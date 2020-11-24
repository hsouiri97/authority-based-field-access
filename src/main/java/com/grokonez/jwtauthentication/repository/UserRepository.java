package com.grokonez.jwtauthentication.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import com.grokonez.jwtauthentication.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

    @Override
    @PreAuthorize("@patchSecurityService.canUpdate(#user)")
    User save(@Param("user") User user);

    @Override
    List<User> findAll();

    @Query("select new com.grokonez.jwtauthentication.model.User(u.name, u.username, u.password) from User u")
    List<User> findAllWithoutEmailAndRoles();
}