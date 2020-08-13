package com.riqra.assessment.infrastructure.repositories;

import com.riqra.assessment.domain.entities.Product;
import com.riqra.assessment.domain.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findBySupplier(Supplier supplier);
}
