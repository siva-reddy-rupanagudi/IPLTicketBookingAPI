package com.belenits.iplticketbookingapi.controller;

import com.belenits.iplticketbookingapi.response.ApiResponse;
import com.belenits.iplticketbookingapi.DTO.TicketrecordDTO;
import com.belenits.iplticketbookingapi.service.TicketRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@Slf4j
public class TicketRecordController {
    @Autowired
    private TicketRecordService ticketRecordService;

    @PostMapping("/")
    public ResponseEntity<ApiResponse<TicketrecordDTO>> bookTicket(@RequestBody TicketrecordDTO ticketrecordDTO){
        log.info("Ticket booking processing......");
        TicketrecordDTO dto= ticketRecordService.bookTicket(ticketrecordDTO);
        ApiResponse<TicketrecordDTO> response=new ApiResponse<>();
        response.setData(dto);
        response.setStatus(201);
        response.setMessage("Ticket Booked Successfully....!");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<TicketrecordDTO>>> getAllTickets(){
        log.info("Fetching Tickets from DB processing......");
        List<TicketrecordDTO> dto= ticketRecordService.getAllTickets();
        ApiResponse<List<TicketrecordDTO>> response=new ApiResponse<>();
        response.setData(dto);
        response.setStatus(200);
        response.setMessage("Tickets fetched successfully....!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TicketrecordDTO>> getTicket(@PathVariable Integer id){
        log.info("Fetching Ticket from DB processing......");
        TicketrecordDTO dto= ticketRecordService.getTicket(id);
        ApiResponse<TicketrecordDTO> response=new ApiResponse<>();
        response.setData(dto); // Return the fetched ticket
        response.setStatus(200);
        response.setMessage("Ticket fetched successfully....!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<ApiResponse<TicketrecordDTO>> updateTicket(@RequestBody TicketrecordDTO ticketrecordDTO){
        log.info("Updating Ticket in DB processing......");
        TicketrecordDTO dto=ticketRecordService.updateTicket(ticketrecordDTO);
        log.info("Ticket with id {} updated successfully", ticketrecordDTO.getTicketId());
        ApiResponse<TicketrecordDTO> response=new ApiResponse<>();
        response.setData(dto);
        response.setStatus(200);
        response.setMessage("Ticket updated successfully....!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/")
    public ResponseEntity<ApiResponse<String>> deleteTicket(@RequestParam Integer id){
        log.info("Deleting Ticket from DB processing......");
        ticketRecordService.deleteTicket(id);
        ApiResponse<String> response=new ApiResponse<>();
        response.setData("Ticket with id "+id+" deleted successfully");
        response.setStatus(200);
        response.setMessage("Ticket deleted successfully....!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
