package com.grokonez.jwtauthentication.repository;

import com.grokonez.jwtauthentication.model.User;
import com.grokonez.jwtauthentication.service.ReadSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.List;

@Repository
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    private final ReadSecurityService readSecurityService;

    @Autowired
    public UserRepositoryCustomImpl(ReadSecurityService readSecurityService) {
        this.readSecurityService = readSecurityService;
    }

    @Override
    public List<User> findAllWithSpecificAttributes() {
        StringBuilder selectedAttributes = new StringBuilder();
        StringBuilder queryBuilder = new StringBuilder();
        for (Field field : readSecurityService.canRead()) {
            selectedAttributes.append(field.getName());
            selectedAttributes.append(",");
        }
        selectedAttributes.deleteCharAt(selectedAttributes.length() - 1);

        queryBuilder.append("SELECT ");
        queryBuilder.append(selectedAttributes);
        queryBuilder.append(" FROM User");

        Query query = entityManager.createQuery(queryBuilder.toString());

        return query.getResultList();
    }
}
