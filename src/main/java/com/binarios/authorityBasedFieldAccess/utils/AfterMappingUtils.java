package com.binarios.authorityBasedFieldAccess.utils;

import com.binarios.authorityBasedFieldAccess.security.model.FieldAuthority;
import com.binarios.authorityBasedFieldAccess.security.services.UserPrinciple;
import org.springframework.security.core.context.SecurityContextHolder;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class AfterMappingUtils {
    public static void removeSpecificProperties(Class targetClass, Object object) {
        UserPrinciple principle = (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<Field> fields = Arrays.asList(targetClass.getDeclaredFields());

        Collection<FieldAuthority> fieldAuthorities = (Collection<FieldAuthority>) principle.getFieldAuthorities();

        List<FieldAuthority> unauthorizedFields = fields.stream().map(field -> new FieldAuthority(field.getName(), field.getType().getTypeName()))
                .collect(Collectors.toList());

        unauthorizedFields.removeAll(fieldAuthorities);

        for (FieldAuthority unauthorizedField : unauthorizedFields) {
            Class[] paramTypes = new Class[1];
            try {
                paramTypes[0] = Class.forName(unauthorizedField.getFieldType()); // get the actual param type
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            String parameterName = unauthorizedField.getAuthority().substring(0, 1).toUpperCase() + unauthorizedField.getAuthority().substring(1);
            String methodName = "set" + parameterName;// fieldName String
            Method method = null;
            try {
                method = targetClass.getMethod(methodName, paramTypes);
            } catch (NoSuchMethodException noSuchMethodException) {
                noSuchMethodException.printStackTrace();
            }
            try {
                method.invoke(object, (Object) null); // field value
            } catch (IllegalAccessException | InvocationTargetException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
        }
    }
}
