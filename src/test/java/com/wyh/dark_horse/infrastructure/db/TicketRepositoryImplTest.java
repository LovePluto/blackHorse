package com.wyh.dark_horse.infrastructure.db;

import com.wyh.dark_horse.bookticket.model.Ticket;
import com.wyh.dark_horse.infrastructure.db.entity.TicketEntity;
import com.wyh.dark_horse.infrastructure.db.jpa.TicketJpa;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.wyh.dark_horse.bookticket.model.TicketStatus.CONFIRM;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TicketRepositoryImplTest {

    @Autowired
    private TicketRepositoryImpl ticketRepository;
    @Autowired
    private TicketJpa ticketJpa;

    @Test
    public void should_save_ticket_successfully() {
        Ticket ticket = new Ticket("id", "flightNumber", 1, BigDecimal.valueOf(2),
                "confirmId", LocalDateTime.now(), CONFIRM);

        ticketRepository.save(ticket);

        final TicketEntity ticketEntity = ticketJpa.findById("id").get();
        assertThat(ticketEntity.getFlightNumber()).isEqualTo("flightNumber");
        assertThat(ticketEntity.getNumber()).isEqualTo(1);
        assertThat(ticketEntity.getPrice()).isEqualTo(new BigDecimal("2.00"));
        assertThat(ticketEntity.getConfirmId()).isEqualTo("confirmId");
        assertThat(ticketEntity.getStatus()).isEqualTo(CONFIRM);
    }

}
