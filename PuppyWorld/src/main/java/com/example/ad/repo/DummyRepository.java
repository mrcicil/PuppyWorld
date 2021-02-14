package com.example.ad.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ad.domain.Dummy;

public interface DummyRepository extends JpaRepository<Dummy, Integer> {

}
