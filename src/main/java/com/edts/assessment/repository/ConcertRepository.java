package com.edts.assessment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edts.assessment.model.Concert;

@Repository
public interface ConcertRepository extends JpaRepository<Concert, Long> {
  
}