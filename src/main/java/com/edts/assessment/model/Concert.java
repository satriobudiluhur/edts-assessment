package com.edts.assessment.model;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "concert")
public class Concert {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String venue;

  private int capacity;

  private Instant eventTime;

  @ElementCollection
  @CollectionTable(name = "ticket_availability", joinColumns = @JoinColumn(name = "concert_id"))
  @MapKeyColumn(name = "ticket_type")
  @Column(name = "quantity")
  private Map<String, Integer> ticketAvailability;

  @JsonIgnore
  @CreationTimestamp
  @Column(insertable = true, updatable = false)
  private Instant createdAt;

  @JsonIgnore
  @UpdateTimestamp
  @Column(insertable = false, updatable = true)
  private Instant updatedAt;

  @JsonIgnore
  @OneToMany(mappedBy = "concert", cascade = CascadeType.ALL)
  private List<Reservation> reservations;
}