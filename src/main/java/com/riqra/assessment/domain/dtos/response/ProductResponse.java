package com.riqra.assessment.domain.dtos.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.riqra.assessment.domain.entities.Product;

import java.math.BigDecimal;

public class ProductResponse {
    private String name;
    private String supplier;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER_FLOAT)
    private BigDecimal price;

    public ProductResponse(Product product) {
        this.name = product.getName();
        this.supplier = product.getSupplier().getName();
        this.price = product.getPrice();
    }

    public String getName() {
        return name;
    }

    public ProductResponse setName(String name) {
        this.name = name;
        return this;
    }

    public String getSupplier() {
        return supplier;
    }

    public ProductResponse setSupplier(String supplier) {
        this.supplier = supplier;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductResponse setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
