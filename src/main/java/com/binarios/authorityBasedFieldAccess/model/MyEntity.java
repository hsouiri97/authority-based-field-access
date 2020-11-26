package com.binarios.authorityBasedFieldAccess.model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
public class MyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NaturalId
    private String name;

    @OneToMany(mappedBy = "myEntity")
    private List<MyField> myFields = new ArrayList<>();

    public MyEntity() {
    }

    public MyEntity(String name) {
        this.name = name;
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

    public List<MyField> getMyFields() {
        return myFields;
    }

    public void setMyFields(List<MyField> myFields) {
        this.myFields = myFields;
    }

    @Override
    public String toString() {
        return "MyEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", myFields=" + myFields +
                '}';
    }
}
