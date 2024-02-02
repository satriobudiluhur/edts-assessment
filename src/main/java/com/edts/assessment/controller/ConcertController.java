package com.edts.assessment.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.edts.assessment.dto.AddConcertRequestDTO;
import com.edts.assessment.enums.TicketType;
import com.edts.assessment.model.Concert;
import com.edts.assessment.service.ConcertService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@Tag(name = "Concert API")
@RequestMapping("/concert")
public class ConcertController {

  @Autowired
  private ConcertService concertService;

  @Autowired
  private ModelMapper mapper;

  @Operation(summary = "Get a Concert by id", description = "Returns a Concert as per the id")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
      @ApiResponse(responseCode = "404", description = "Not found - The Concert with the id was not found")
  })
  @GetMapping("/{id}")
  public ResponseEntity<Concert> getConcertById(@PathVariable Long id) {
    Concert concert = concertService.getConcertById(id);
    if (concert != null) {
      return ResponseEntity.ok(concert);
    } else {
      return ResponseEntity.notFound().build();
    }
  }


  @Operation(summary = "Get all available Concerts", description = "Return list of all available Concerts")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
      @ApiResponse(responseCode = "404", description = "Not found - no available Concert")
  })
  @GetMapping("/all")
  public ResponseEntity<List<Concert>> getAllConcerts() {
    List<Concert> allConcerts = concertService.getAllConcerts();
    if (!allConcerts.isEmpty()) {
      return ResponseEntity.ok(allConcerts);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @Operation(summary = "Add a Concert", description = "Add a Concert with the data")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
      @ApiResponse(responseCode = "400", description = "Failed add Concert data")
  })
  @PostMapping("/add")
  public ResponseEntity<Concert> addConcert(@RequestBody AddConcertRequestDTO addConcertRequestDTO) {
      Concert concert = mapper.map(addConcertRequestDTO, Concert.class);
      Concert savedConcert = concertService.saveConcert(concert);
      if (savedConcert != null) {
          return ResponseEntity.ok(savedConcert);
      } else {
          return ResponseEntity.badRequest().build();
      }
  }

  @Operation(summary = "Reserve for a Concert", description = "Reserve a Concert per User ID\n- Choose Ticket Type\n- Input Availabiity\n- Input UserID")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully reserved"),
      @ApiResponse(responseCode = "404", description = "Failed to reserve the Concert")
  })
  @PostMapping("/reserve/{concertId}")
  public ResponseEntity<String> reserveTickets(
      @PathVariable Long concertId,
      @RequestParam TicketType ticketType,
      @RequestParam int quantity,
      @RequestParam String userId) {

    String ticketNumber = concertService.reserveTickets(concertId, ticketType, quantity, userId);

    if (ticketNumber != null) {
      return ResponseEntity.ok("Tickets reserved successfully! Ticket number: " + ticketNumber);
    } else {
      return ResponseEntity.badRequest().body("Failed to reserve tickets.");
    }
  }
}