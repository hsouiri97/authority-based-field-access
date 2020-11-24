package com.grokonez.jwtauthentication.service;

import com.grokonez.jwtauthentication.message.request.UpdateForm;
import com.grokonez.jwtauthentication.model.User;
import com.grokonez.jwtauthentication.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PatchSecurityService patchSecurityService;

    public UserService(UserRepository userRepository, PatchSecurityService patchSecurityService) {
        this.userRepository = userRepository;
        this.patchSecurityService = patchSecurityService;
    }

    public List<User> getAllUsers() {
        if (patchSecurityService.canRead()) {
            return userRepository.findAll();
        }
        return userRepository.findAllWithoutEmailAndRoles();
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