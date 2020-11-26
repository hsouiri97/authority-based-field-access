package com.binarios.authorityBasedFieldAccess.mapper;

import com.binarios.authorityBasedFieldAccess.message.response.AppRoleResponse;
import com.binarios.authorityBasedFieldAccess.message.response.UserResponse;
import com.binarios.authorityBasedFieldAccess.model.AppRole;
import com.binarios.authorityBasedFieldAccess.model.User;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;
import org.mapstruct.factory.Mappers;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-26T16:11:31+0100",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_275 (Private Build)"
)
public class UserMapperImpl implements UserMapper {

    private final AppRoleMapper appRoleMapper = Mappers.getMapper( AppRoleMapper.class );

    @Override
    public UserResponse userToUserResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse userResponse = new UserResponse();

        userResponse.setId( user.getId() );
        userResponse.setName( user.getName() );
        userResponse.setUsername( user.getUsername() );
        userResponse.setEmail( user.getEmail() );
        userResponse.setPassword( user.getPassword() );
        userResponse.setAppRoles( appRoleSetToAppRoleResponseSet( user.getAppRoles() ) );

        removeSpecificProperties( user, userResponse );

        return userResponse;
    }

    @Override
    public List<UserResponse> userResponseList(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<UserResponse> list = new ArrayList<UserResponse>( users.size() );
        for ( User user : users ) {
            list.add( userToUserResponse( user ) );
        }

        return list;
    }

    protected Set<AppRoleResponse> appRoleSetToAppRoleResponseSet(Set<AppRole> set) {
        if ( set == null ) {
            return null;
        }

        Set<AppRoleResponse> set1 = new HashSet<AppRoleResponse>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( AppRole appRole : set ) {
            set1.add( appRoleMapper.toResponse( appRole ) );
        }

        return set1;
    }
}
