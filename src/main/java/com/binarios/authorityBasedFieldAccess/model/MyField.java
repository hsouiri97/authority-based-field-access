package com.binarios.authorityBasedFieldAccess.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
public class MyField {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    private Boolean configurable;

    private String myFieldType;

    @ManyToOne
    @JoinColumn(name = "my_entity_id", nullable = false)
    private MyEntity myEntity;



    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "Field_Role",
            joinColumns = { @JoinColumn(name = "field_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") }
    )

    @JsonIgnore
    List<AppRole> roles = new ArrayList<>();

    public MyField() {

    }

    public MyField(String name, Boolean configurable, String myFieldType) {
        this.name = name;
        this.configurable = configurable;
        this.myFieldType = myFieldType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getConfigurable() {
        return configurable;
    }

    public void setConfigurable(Boolean configurable) {
        this.configurable = configurable;
    }

    public String getMyFieldType() {
        return myFieldType;
    }

    public void setMyFieldType(String myFieldType) {
        this.myFieldType = myFieldType;
    }

    public MyEntity getMyEntity() {
        return myEntity;
    }

    public void setMyEntity(MyEntity myEntity) {
        this.myEntity = myEntity;
    }

    public List<AppRole> getRoles() {
        return roles;
    }

    public void setRoles(List<AppRole> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "MyField{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", configurable=" + configurable +
                ", myFieldType='" + myFieldType + '\'' +
                ", myEntity=" + myEntity +
                '}';
    }
}
