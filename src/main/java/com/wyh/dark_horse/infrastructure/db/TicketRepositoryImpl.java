package com.wyh.dark_horse.infrastructure.db;

import com.wyh.dark_horse.bookticket.model.Ticket;
import com.wyh.dark_horse.bookticket.repository.TicketRepository;
import com.wyh.dark_horse.infrastructure.db.entity.TicketEntity;
import com.wyh.dark_horse.infrastructure.db.jpa.TicketJpa;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class TicketRepositoryImpl implements TicketRepository {
    private TicketJpa ticketJpa;

    @Override
    public void save(Ticket ticket) {
        ticketJpa.save(TicketEntity.from(ticket));
    }
}
