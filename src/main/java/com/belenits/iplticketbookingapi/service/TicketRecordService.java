package com.belenits.iplticketbookingapi.service;

import com.belenits.iplticketbookingapi.DTO.TicketrecordDTO;
import com.belenits.iplticketbookingapi.entities.TicketRecord;
import com.belenits.iplticketbookingapi.exceptions.BookingIdNotFoundException;
import com.belenits.iplticketbookingapi.repository.TicketRecordRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class TicketRecordService {
    @Autowired
    private TicketRecordRepo recordRepo;

    public TicketrecordDTO bookTicket(@RequestBody TicketrecordDTO ticketrecordDTO) {
        TicketRecord tr = new TicketRecord();
        List<String> seats=recordRepo.findAll().stream().filter((tr1->tr1.getBookingStatus().equalsIgnoreCase("CONFIRMED"))).map(TicketRecord::getSeatNumber).toList();
        if(seats.contains(ticketrecordDTO.getSeatNumber())){
            log.error("Seat number {} is already booked", ticketrecordDTO.getSeatNumber());
            throw new RuntimeException("Seat number "+ticketrecordDTO.getSeatNumber()+" is already booked");
        }
        BeanUtils.copyProperties(ticketrecordDTO, tr);
        BeanUtils.copyProperties(recordRepo.save(tr), ticketrecordDTO);
        log.info("Booking Ticket Confirmed,seat no: {}", ticketrecordDTO.getSeatNumber());
        return ticketrecordDTO;
    }

    public List<TicketrecordDTO> getAllTickets() {
        List<TicketRecord> ticketRecords = recordRepo.findAll().stream().filter((tr->tr.getBookingStatus().equalsIgnoreCase("CONFIRMED"))).toList();
        List<TicketrecordDTO> ticketDTOs = new ArrayList<>();
        for (TicketRecord ticketRecord : ticketRecords) {
            TicketrecordDTO dto = new TicketrecordDTO();
            BeanUtils.copyProperties(ticketRecord, dto);
            ticketDTOs.add(dto);
        }
        log.info("Fetched all tickets from DB, total tickets: {}", ticketDTOs.size());
        return ticketDTOs;
    }

    public TicketrecordDTO getTicket(Integer id) {
        TicketRecord ticketRecord=recordRepo.findById(id).orElseThrow(()->new BookingIdNotFoundException("Ticket with id "+id+" not found"));
        TicketrecordDTO ticketrecordDTO=new TicketrecordDTO();
        BeanUtils.copyProperties(ticketRecord,ticketrecordDTO);
        log.info("Fetched ticket with id: {}", id);
        return ticketrecordDTO;
    }

    public TicketrecordDTO updateTicket(@RequestBody TicketrecordDTO ticketrecordDTO){
        TicketRecord tr=recordRepo.findById(ticketrecordDTO.getTicketId()).orElseThrow(()->new BookingIdNotFoundException("Ticket with id "+ticketrecordDTO.getTicketId()+" not found"));
        BeanUtils.copyProperties(tr,ticketrecordDTO);
        log.info("Updated ticket with id: {}", ticketrecordDTO.getTicketId());
        return ticketrecordDTO;
    }

    public void deleteTicket(Integer id) {
        TicketRecord tr=recordRepo.findById(id).orElseThrow(()->new BookingIdNotFoundException("Ticket with id "+id+" not found"));
        tr.setBookingStatus("CANCELLED");
        recordRepo.save(tr);
        log.info("Cancelled ticket with id: {}", id);
    }
}
