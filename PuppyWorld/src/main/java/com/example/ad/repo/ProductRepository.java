package com.example.ad.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ad.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
