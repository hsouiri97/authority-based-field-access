package com.binarios.authorityBasedFieldAccess.message.response;

import com.binarios.authorityBasedFieldAccess.model.RoleName;

public class AppRoleResponse {
    private Long id;
    private RoleName name;

    public AppRoleResponse() {

    }

    public AppRoleResponse(Long id, RoleName name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }
}
