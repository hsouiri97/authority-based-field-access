package com.grokonez.jwtauthentication.service;

import com.grokonez.jwtauthentication.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;

@Service("patchSecurityService")
public class ReadSecurityService {

    public List<Field> canRead() {
        List<GrantedAuthority> authorities = grantedAuthorities();

        return User.canRead(authorities);
    }

    private List<GrantedAuthority> grantedAuthorities() {
        return (List<GrantedAuthority>) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getAuthorities();
    }
}
