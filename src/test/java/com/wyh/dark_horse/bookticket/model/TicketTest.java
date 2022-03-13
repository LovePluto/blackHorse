package com.wyh.dark_horse.bookticket.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.wyh.dark_horse.bookticket.model.TicketStatus.CONFIRM;
import static com.wyh.dark_horse.bookticket.model.TicketStatus.WAITING_CONFIRM;
import static org.assertj.core.api.Assertions.assertThat;

class TicketTest {

    @Test
    public void should_init_ticket_successfully() {
        Ticket ticket = new Ticket();

        ticket.init();

        assertThat(ticket.getId()).isNotEmpty();
        assertThat(ticket.getCreatedAt()).isNotNull();
        assertThat(ticket.getStatus()).isEqualTo(WAITING_CONFIRM);
    }

    @Test
    public void should_update_ticket_successfully() {
        Ticket ticket = new Ticket();
        BookResult bookResult = BookResult.builder()
                .result(true)
                .id("id")
                .price("22")
                .build();

        ticket.update(bookResult);

        assertThat(ticket.getStatus()).isEqualTo(CONFIRM);
        assertThat(ticket.getConfirmId()).isEqualTo("id");
        assertThat(ticket.getPrice()).isEqualTo(new BigDecimal("22"));
    }
}
