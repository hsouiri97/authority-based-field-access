package com.grokonez.jwtauthentication.repository;

import com.grokonez.jwtauthentication.model.User;
import com.grokonez.jwtauthentication.service.ReadSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {



    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);


    /*@Query("select new com.grokonez.jwtauthentication.model.User(u.name, u.username, u.password) from User u")
    List<User> findAllWithoutEmailAndRoles();*/
}