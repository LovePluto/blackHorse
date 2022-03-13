package com.wyh.dark_horse.bookticket;

import com.wyh.dark_horse.bookticket.client.FlightClient;
import com.wyh.dark_horse.bookticket.model.BookResult;
import com.wyh.dark_horse.bookticket.model.Ticket;
import com.wyh.dark_horse.bookticket.repository.TicketRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static com.wyh.dark_horse.bookticket.model.TicketStatus.CONFIRM;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookTicketServiceTest {

    @InjectMocks
    private BookTicketService bookTicketService;
    @Mock
    private FlightClient flightClient;
    @Mock
    private TicketRepository ticketRepository;


    @Test
    public void should_create_book_ticket_successfully() {
        Ticket ticket = new Ticket();
        BookResult bookResult = BookResult.builder()
                .result(true)
                .id("id")
                .price("22")
                .build();
        when(flightClient.bookTicket(any())).thenReturn(bookResult);

        final String id = bookTicketService.create(ticket);

        assertThat(id).isNotEmpty();
        assertThat(ticket.getStatus()).isEqualTo(CONFIRM);
        assertThat(ticket.getConfirmId()).isEqualTo("id");
        assertThat(ticket.getPrice()).isEqualTo(new BigDecimal("22"));
        verify(ticketRepository, times(2)).save(any());
    }
}
