package com.grokonez.jwtauthentication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grokonez.jwtauthentication.annotation.SecureRead;
import com.grokonez.jwtauthentication.annotation.SecureUpdate;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.MappedSuperclass;
import javax.persistence.PostLoad;
import javax.persistence.Transient;
import java.lang.reflect.Field;
import java.util.List;

@MappedSuperclass
public class OEntity<T> {

    @Transient
    @JsonIgnore
    T originalObj;

    @PostLoad
    public void onLoad() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String serialized = mapper.writeValueAsString(this);
            this.originalObj = (T) mapper.readValue(serialized, this.getClass());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean canUpdate(List<GrantedAuthority> authorities) {
        for (Field field : this.getClass().getDeclaredFields()) {
            SecureUpdate secureUpdate = field.getAnnotation(SecureUpdate.class);
            if (secureUpdate != null) {
                try {
                    field.setAccessible(true);
                    Object persistedField = field.get(this);
                    Object originalField = field.get(originalObj);
                    String[] allowedRoles = secureUpdate.value();
                    if (!persistedField.equals(originalField)) {
                        for (String role : allowedRoles) {
                            for (GrantedAuthority authority : authorities) {
                                if (authority.getAuthority().equalsIgnoreCase(role)) {
                                    return true;
                                }
                            }
                        }
                        return false;
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return true;
    }
}
