package com.binarios.authorityBasedFieldAccess.security.model;

import org.springframework.security.core.GrantedAuthority;

import java.util.Objects;

public class FieldAuthority implements GrantedAuthority {

    private final String fieldName;
    private final String fieldType;

    public FieldAuthority(String fieldName, String fieldType) {
        this.fieldName = fieldName;
        this.fieldType = fieldType;
    }

    @Override
    public String getAuthority() {
        return this.fieldName;
    }

    public String getFieldType() {
        return fieldType;
    }

    @Override
    public String toString() {
        return "FieldAuthority{" +
                "fieldName='" + fieldName + '\'' +
                ", fieldType='" + fieldType + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FieldAuthority that = (FieldAuthority) o;
        return fieldName.equals(that.fieldName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fieldName);
    }
}
