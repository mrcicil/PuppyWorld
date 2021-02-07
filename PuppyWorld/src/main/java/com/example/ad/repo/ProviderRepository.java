package com.example.ad.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ad.domain.Provider;

public interface ProviderRepository extends JpaRepository<Provider, Integer> {

}
