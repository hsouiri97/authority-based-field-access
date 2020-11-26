package com.binarios.authorityBasedFieldAccess.mapper;

import com.binarios.authorityBasedFieldAccess.message.response.UserResponse;
import com.binarios.authorityBasedFieldAccess.model.User;
import com.binarios.authorityBasedFieldAccess.utils.AfterMappingUtils;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {AppRoleMapper.class})
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserResponse userToUserResponse(User user);
    List<UserResponse> userResponseList(List<User> users);

    @AfterMapping
    default void removeSpecificProperties(User user, @MappingTarget UserResponse userResponse) {
        AfterMappingUtils.removeSpecificProperties(userResponse.getClass(), userResponse);
    }
}
