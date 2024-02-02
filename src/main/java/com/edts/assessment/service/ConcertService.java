package com.edts.assessment.service;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edts.assessment.enums.TicketType;
import com.edts.assessment.model.Concert;
import com.edts.assessment.model.Reservation;
import com.edts.assessment.repository.ConcertRepository;
import com.edts.assessment.repository.ReservationRepository;

@Service
public class ConcertService {

  @Autowired
  private ConcertRepository concertRepository;

  @Autowired
  private ReservationRepository reservationRepository;

  public Concert getConcertById(Long id) {
    return concertRepository.findById(id).orElse(null);
  }

  public Concert saveConcert(Concert concert) {
    return concertRepository.save(concert);
  }

  public List<Concert> getAllConcerts() {
    return concertRepository.findAll();
  }

  public String reserveTickets(Long concertId, TicketType ticketType, int quantity, String userId) {
    Concert concert = concertRepository.findById(concertId).orElse(null);

    if (concert == null)
      return null;

    Map<String, Integer> availability = concert.getTicketAvailability();

    int availableTickets = availability.getOrDefault(ticketType.name(), 0);

    if (availableTickets >= quantity) {
      availability.put(ticketType.name(), availableTickets - quantity);
      concertRepository.save(concert);
      String ticketNumber = generateTicketNumber(concert.getName(), ticketType, concert.getEventTime());
      saveReservation(userId, ticketNumber, concert, quantity, ticketType) ;
      return ticketNumber;
    }

    return null;
  }

  private void saveReservation(
      String userId, String ticketNumber,
      Concert concert, int quantity, TicketType ticketType) {
    reservationRepository.save(Reservation.builder()
        .userId(userId)
        .ticketNumber(ticketNumber)
        .concert(concert)
        .quantity(quantity)
        .ticketType(ticketType)
        .build());
  }

  private String generateTicketNumber(String concertName, TicketType ticketType, Instant eventTime) {
    Random random = new Random();

    int randomNumber = random.nextInt(1000000000);

    return concertName.replaceAll("\\s", "") + "_"
        + ticketType.name() + "_"
        + eventTime.toString().replaceAll("\\W", "") + "_"
        + randomNumber;
  }
}