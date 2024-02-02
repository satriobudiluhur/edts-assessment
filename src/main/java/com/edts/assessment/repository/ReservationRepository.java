package com.edts.assessment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edts.assessment.model.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}