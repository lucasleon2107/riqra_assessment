package com.riqra.assessment.domain.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ApplicationUser {
    @Id
    @GeneratedValue
    private Long id;
    private String email;
    private String password;

    @ManyToOne
    private Supplier supplier;

    public ApplicationUser() {

    }

    public ApplicationUser(String email, String password, Supplier supplier) {
        this.email = email;
        this.password = password;
        this.supplier = supplier;
    }

    public Long getId() {
        return id;
    }

    public ApplicationUser setId(Long id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ApplicationUser setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public ApplicationUser setPassword(String password) {
        this.password = password;
        return this;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public ApplicationUser setSupplier(Supplier supplier) {
        this.supplier = supplier;
        return this;
    }
}
