package com.binarios.authorityBasedFieldAccess.message.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashSet;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {
    private Long id;
    private String name;
    private String username;
    private String email;
    private String password;
    private Set<AppRoleResponse> appRoles = new HashSet<>();

    public UserResponse() {
    }

    public UserResponse(Long id, String name, String username, String email, String password, Set<AppRoleResponse> appRoles ) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.appRoles = appRoles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<AppRoleResponse> getAppRoles() {
        return appRoles;
    }

    public void setAppRoles(Set<AppRoleResponse> appRoles) {
        this.appRoles = appRoles;
    }
}
