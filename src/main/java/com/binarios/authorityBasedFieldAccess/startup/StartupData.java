package com.binarios.authorityBasedFieldAccess.startup;

import com.binarios.authorityBasedFieldAccess.annotation.SaveMetadataAtStartup;
import com.binarios.authorityBasedFieldAccess.model.AppRole;
import com.binarios.authorityBasedFieldAccess.model.MyEntity;
import com.binarios.authorityBasedFieldAccess.model.MyField;
import com.binarios.authorityBasedFieldAccess.model.RoleName;
import com.binarios.authorityBasedFieldAccess.repository.MyEntityRepository;
import com.binarios.authorityBasedFieldAccess.repository.MyFieldRepository;
import com.binarios.authorityBasedFieldAccess.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.event.EventListener;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class StartupData {

    private final MyEntityRepository myEntityRepository;
    private final MyFieldRepository myFieldRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public StartupData(MyEntityRepository myEntityRepository,
                       MyFieldRepository myFieldRepository,
                       RoleRepository roleRepository) {
        this.myEntityRepository = myEntityRepository;
        this.myFieldRepository = myFieldRepository;
        this.roleRepository = roleRepository;
    }

    @EventListener
    public void appReady(ApplicationReadyEvent event) {

        AppRole roleAdmin = roleRepository.findByName(RoleName.ROLE_ADMIN)
                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));

        AppRole roleUser = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));

        ClassPathScanningCandidateComponentProvider scanner =
                new ClassPathScanningCandidateComponentProvider(false);

        scanner.addIncludeFilter(new AnnotationTypeFilter(SaveMetadataAtStartup.class));

        for (BeanDefinition bd : scanner.findCandidateComponents("com.binarios.authorityBasedFieldAccess.model")) {
            if (!myEntityRepository.existsByName(bd.getBeanClassName())) {
                MyEntity myEntity = myEntityRepository.save(new MyEntity(bd.getBeanClassName()));
                try {
                    Optional<Class> classOptional = Optional.of(Class.forName(bd.getBeanClassName()));
                    Field[] declaredFields = classOptional.orElseThrow(RuntimeException::new).getDeclaredFields();
                    for (Field field : declaredFields) {
                        Boolean configurable = !field.getGenericType().getTypeName().endsWith("String");
                        MyField myField = new MyField(field.getName(), configurable, field.getGenericType().getTypeName());
                        myField.setMyEntity(myEntity);
                        List<AppRole> roles = new ArrayList<>();
                        if (field.getName().equals("id") || field.getName().equals("password") || field.getName().equals("email")) {
                            roles.add(roleAdmin);
                        } else {
                            roles.add(roleAdmin);
                            roles.add(roleUser);
                        }
                        myField.setRoles(roles);
                        myFieldRepository.save(myField);
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
