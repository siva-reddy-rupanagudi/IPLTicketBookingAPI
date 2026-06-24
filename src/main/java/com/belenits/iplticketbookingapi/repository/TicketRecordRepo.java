package com.belenits.iplticketbookingapi.repository;

import com.belenits.iplticketbookingapi.entities.TicketRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRecordRepo extends JpaRepository<TicketRecord,Integer> {
}
