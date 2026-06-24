package com.belenits.iplticketbookingapi.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketrecordDTO {

    private Integer ticketId;

    private String customerName;

    private String matchDetails;

    private String seatNumber;
    private String ticketPrice;

    private String bookingStatus;// Conformed or cancelled
}
