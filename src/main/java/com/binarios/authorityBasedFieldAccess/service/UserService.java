package com.binarios.authorityBasedFieldAccess.service;

import com.binarios.authorityBasedFieldAccess.mapper.UserMapper;
import com.binarios.authorityBasedFieldAccess.message.request.UpdateForm;
import com.binarios.authorityBasedFieldAccess.message.response.UserResponse;
import com.binarios.authorityBasedFieldAccess.model.User;
import com.binarios.authorityBasedFieldAccess.repository.UserRepository;
import com.binarios.authorityBasedFieldAccess.security.model.FieldAuthority;
import com.binarios.authorityBasedFieldAccess.security.services.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;


    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponse> getAllUsers() {
        //return userRepository.findAll();
        return UserMapper.INSTANCE.userResponseList(userRepository.findAll());
    }

    public UserResponse getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException(username));

        return UserMapper.INSTANCE.userToUserResponse(user);
    }

    public User updateUserByUsername(Long id, UpdateForm updateForm) {
        User user = userRepository.findById(id).
                orElseThrow(() -> new RuntimeException(String.valueOf(id)));
        user.setName(updateForm.getName());
        return userRepository.save(user);
    }
}