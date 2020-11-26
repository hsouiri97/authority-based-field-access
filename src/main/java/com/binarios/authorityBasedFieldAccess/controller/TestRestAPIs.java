package com.binarios.authorityBasedFieldAccess.controller;

import com.binarios.authorityBasedFieldAccess.message.request.UpdateForm;
import com.binarios.authorityBasedFieldAccess.message.response.UserResponse;
import com.binarios.authorityBasedFieldAccess.model.User;
import com.binarios.authorityBasedFieldAccess.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class TestRestAPIs {

	private final UserService userService;

	public TestRestAPIs(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/api/test/users")
	public List<User> userAccess() {
		System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		return this.userService.getAllUsers();
	}

	@GetMapping("/api/test/user/{username}")
	public UserResponse getUserByUsername(@PathVariable String username) {
		return userService.getUserByUsername(username);
	}

	@PutMapping("/api/test/user/{id}")
	public User updateUserById(@PathVariable Long id, @RequestBody UpdateForm updateForm) {
		return userService.updateUserByUsername(id, updateForm);
	}

	@GetMapping("/api/test/pm")
	@PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
	public String projectManagementAccess() {
		return ">>> Board Management Project";
	}
	
	@GetMapping("/api/test/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return ">>> Admin Contents";
	}
}