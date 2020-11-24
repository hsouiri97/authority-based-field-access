package com.grokonez.jwtauthentication.repository;

import com.grokonez.jwtauthentication.model.User;

import java.util.List;

public interface UserRepositoryCustom {
    List<User> findAllWithSpecificAttributes();
}
