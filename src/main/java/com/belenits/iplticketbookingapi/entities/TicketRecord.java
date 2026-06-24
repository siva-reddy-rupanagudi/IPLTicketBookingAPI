package com.belenits.iplticketbookingapi.entities;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ticketrecord")
public class TicketRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ticketId;
    @Column(name = "customerName")
    private String customerName;
    @NonNull
    @Column(name = "matchDetails")
    private String matchDetails;
    @NonNull
    @Column(unique = true)
    private String seatNumber;
    @Column(name = "ticketPrice")
    private String ticketPrice;
    @Column(name = "bookingStatus")
    private String bookingStatus;// Conformed or cancelled

}
