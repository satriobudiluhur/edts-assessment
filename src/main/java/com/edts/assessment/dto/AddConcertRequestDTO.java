package com.edts.assessment.dto;

import java.time.Instant;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddConcertRequestDTO {

  private String name;

  private String venue;

  private int capacity;

  private Instant eventTime;

  private Map<String, Integer> ticketAvailability;
}
