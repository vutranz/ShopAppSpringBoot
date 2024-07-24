package com.project.shopapp.Repository;

import com.project.shopapp.Models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
        boolean existsByName(String name);

        @Override
        Page<Product> findAll(Pageable pageable);
}
