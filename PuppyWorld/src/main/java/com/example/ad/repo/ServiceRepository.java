package com.example.ad.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ad.domain.Services;


public interface ServiceRepository extends JpaRepository<Services, Integer> {

}
