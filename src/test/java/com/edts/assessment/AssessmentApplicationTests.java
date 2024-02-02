package com.edts.assessment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

// ConcertControllerTest.java
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.edts.assessment.controller.ConcertController;
import com.edts.assessment.enums.TicketType;
import com.edts.assessment.model.Concert;
import com.edts.assessment.service.ConcertService;

@ExtendWith(MockitoExtension.class)
public class AssessmentApplicationTests {
	@Mock
	private ConcertService concertService;

	@InjectMocks
	private ConcertController concertController;

	@Test
	public void testGetConcertById_ValidId_ReturnsConcert() {
		// Arrange
		Long concertId = 1L;
		Concert concert = new Concert();
		concert.setId(concertId);
		when(concertService.getConcertById(concertId)).thenReturn(concert);

		// Act
		ResponseEntity<Concert> responseEntity = concertController.getConcertById(concertId);

		// Assert
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(concert, responseEntity.getBody());
	}

	@Test
	public void testGetConcertById_InvalidId_ReturnsNotFound() {
		// Arrange
		Long concertId = 1L;
		when(concertService.getConcertById(concertId)).thenReturn(null);

		// Act
		ResponseEntity<Concert> responseEntity = concertController.getConcertById(concertId);

		// Assert
		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	}

	@Test
	public void testReserveTickets_ValidReservation_ReturnsOk() {
		// Arrange
		Long concertId = 1L;
		TicketType ticketType = TicketType.VIP;
		int quantity = 2;
		String userId = "user123";
		String ticketNumber = "TICKET123";
		when(concertService.reserveTickets(concertId, ticketType, quantity, userId)).thenReturn(ticketNumber);

		// Act
		ResponseEntity<String> responseEntity = concertController.reserveTickets(concertId, ticketType, quantity, userId);

		// Assert
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals("Tickets reserved successfully! Ticket number: " + ticketNumber, responseEntity.getBody());
	}

	@Test
	public void testReserveTickets_InvalidReservation_ReturnsBadRequest() {
		// Arrange
		Long concertId = 1L;
		TicketType ticketType = TicketType.VIP;
		int quantity = 2;
		String userId = "user123";
		when(concertService.reserveTickets(concertId, ticketType, quantity, userId)).thenReturn(null);

		// Act
		ResponseEntity<String> responseEntity = concertController.reserveTickets(concertId, ticketType, quantity, userId);

		// Assert
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
		assertEquals("Failed to reserve tickets.", responseEntity.getBody());
	}
}
