package com.grokonez.jwtauthentication.controller;

import com.grokonez.jwtauthentication.message.request.UpdateForm;
import com.grokonez.jwtauthentication.model.User;
import com.grokonez.jwtauthentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
		return this.userService.getAllUsers();
	}

	@GetMapping("/api/test/user/{username}")
	//@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public User getUserByUsername(@PathVariable String username) {
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