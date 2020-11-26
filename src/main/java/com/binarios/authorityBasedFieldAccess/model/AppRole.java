package com.binarios.authorityBasedFieldAccess.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.NaturalId;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
public class AppRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private RoleName name;

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private List<MyField> myFieldList = new ArrayList<>();

    public AppRole() {}

    public AppRole(RoleName name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }

    public List<MyField> getMyFieldList() {
        return myFieldList;
    }

    public void setMyFieldList(List<MyField> myFieldList) {
        this.myFieldList = myFieldList;
    }
}