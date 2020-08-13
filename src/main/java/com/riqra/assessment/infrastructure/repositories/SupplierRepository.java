package com.riqra.assessment.infrastructure.repositories;

import com.riqra.assessment.domain.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    @Query(value = "SELECT * FROM supplier ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Supplier getRandomSupplier();
}
