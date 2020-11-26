package com.binarios.authorityBasedFieldAccess.repository;

import com.binarios.authorityBasedFieldAccess.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {



    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);


    /*@Query("select new com.grokonez.jwtauthentication.model.User(u.name, u.username, u.password) from User u")
    List<User> findAllWithoutEmailAndRoles();*/
}