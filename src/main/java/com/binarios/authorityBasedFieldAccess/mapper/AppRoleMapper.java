package com.binarios.authorityBasedFieldAccess.mapper;

import com.binarios.authorityBasedFieldAccess.message.response.AppRoleResponse;
import com.binarios.authorityBasedFieldAccess.model.AppRole;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AppRoleMapper {
    AppRoleMapper INSTANCE = Mappers.getMapper(AppRoleMapper.class);
    AppRoleResponse toResponse(AppRole appRole);
}
