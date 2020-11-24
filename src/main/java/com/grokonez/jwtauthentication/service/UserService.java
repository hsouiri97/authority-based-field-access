package com.grokonez.jwtauthentication.service;

import com.grokonez.jwtauthentication.message.request.UpdateForm;
import com.grokonez.jwtauthentication.model.User;
import com.grokonez.jwtauthentication.repository.UserRepository;
import com.grokonez.jwtauthentication.repository.UserRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserRepositoryCustom userRepositoryCustom;


    @Autowired
    public UserService(UserRepository userRepository, UserRepositoryCustom userRepositoryCustom) {
        this.userRepository = userRepository;
        this.userRepositoryCustom = userRepositoryCustom;
    }

    public List<User> getAllUsers() {
        return userRepositoryCustom.findAllWithSpecificAttributes();
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException(username));
    }

    public User updateUserByUsername(Long id, UpdateForm updateForm) {
        User user = userRepository.findById(id).
                orElseThrow(() -> new RuntimeException(String.valueOf(id)));
        user.setName(updateForm.getName());
        return userRepository.save(user);
    }
}