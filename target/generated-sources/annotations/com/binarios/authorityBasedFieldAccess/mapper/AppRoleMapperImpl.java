package com.binarios.authorityBasedFieldAccess.mapper;

import com.binarios.authorityBasedFieldAccess.message.response.AppRoleResponse;
import com.binarios.authorityBasedFieldAccess.model.AppRole;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-26T16:11:31+0100",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_275 (Private Build)"
)
public class AppRoleMapperImpl implements AppRoleMapper {

    @Override
    public AppRoleResponse toResponse(AppRole appRole) {
        if ( appRole == null ) {
            return null;
        }

        AppRoleResponse appRoleResponse = new AppRoleResponse();

        appRoleResponse.setId( appRole.getId() );
        appRoleResponse.setName( appRole.getName() );

        return appRoleResponse;
    }
}
