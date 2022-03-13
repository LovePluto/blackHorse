package com.wyh.dark_horse.bookticket.client;

import com.wyh.dark_horse.bookticket.model.BookResult;
import com.wyh.dark_horse.bookticket.model.Ticket;

public interface FlightClient {
    BookResult bookTicket(Ticket bookTicket);
}
