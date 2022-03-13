package com.wyh.dark_horse.infrastructure.client;

import com.wyh.dark_horse.bookticket.client.FlightClient;
import com.wyh.dark_horse.bookticket.model.BookResult;
import com.wyh.dark_horse.bookticket.model.Ticket;
import org.springframework.stereotype.Component;

@Component
public class FlightClientImpl implements FlightClient {
    @Override
    public BookResult bookTicket(Ticket bookTicket) {
        return null;
    }
}
