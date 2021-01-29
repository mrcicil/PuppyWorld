package com.example.ad.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ad.domain.Service;


public interface ServiceRepository extends JpaRepository<Service, Long> {

}
