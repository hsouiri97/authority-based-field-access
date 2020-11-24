package com.grokonez.jwtauthentication.service;

import com.grokonez.jwtauthentication.model.OEntity;
import com.grokonez.jwtauthentication.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("patchSecurityService")
public class PatchSecurityService {
    public boolean canUpdate(Object obj){

        List<GrantedAuthority> authorities = grantedAuthorities();

        if (obj instanceof OEntity){
            OEntity oEntity = (OEntity) obj;
            return oEntity.canUpdate(authorities);
        }else{
            return true;
        }
    }

    public boolean canRead() {
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
