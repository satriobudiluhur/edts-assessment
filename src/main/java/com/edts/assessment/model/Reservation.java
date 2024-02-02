package com.edts.assessment.model;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.edts.assessment.enums.TicketType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "reservation")
public class Reservation {

  @JsonIgnore
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String userId;

  @Column(unique = true)
  private String ticketNumber;

  private Integer quantity;

  @Enumerated(EnumType.STRING)
  private TicketType ticketType;

  @ManyToOne
  @JoinColumn(name = "concert_id")
  private Concert concert;

  @JsonIgnore
  @CreationTimestamp
  @Column(insertable = true, updatable = false)
  private Instant createdAt;

  @JsonIgnore
  @UpdateTimestamp
  @Column(insertable = false, updatable = true)
  private Instant updatedAt;
}