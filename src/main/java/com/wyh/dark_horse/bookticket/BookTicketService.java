package com.wyh.dark_horse.bookticket;

import com.wyh.dark_horse.bookticket.client.FlightClient;
import com.wyh.dark_horse.bookticket.model.BookResult;
import com.wyh.dark_horse.bookticket.model.Ticket;
import com.wyh.dark_horse.bookticket.repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class BookTicketService {
    private FlightClient flightClient;
    private TicketRepository ticketRepository;

    @Transactional
    public String create(Ticket bookTicket) {
        bookTicket.init();
        ticketRepository.save(bookTicket);
        final BookResult bookResult = flightClient.bookTicket(bookTicket);
        bookTicket.update(bookResult);
        ticketRepository.save(bookTicket);
        return bookTicket.getId();
    }
}
