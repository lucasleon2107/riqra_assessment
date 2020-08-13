package com.riqra.assessment.domain.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
public class Product {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    private Supplier supplier;

    private BigDecimal price;

    public Product() {
    }

    public Product(Long id, String name, Supplier supplier, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.supplier = supplier;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public Product setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public Product setSupplier(Supplier supplier) {
        this.supplier = supplier;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Product setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
