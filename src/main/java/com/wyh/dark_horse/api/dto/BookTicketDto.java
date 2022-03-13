package com.wyh.dark_horse.api.dto;

import com.wyh.dark_horse.bookticket.model.Ticket;
import lombok.Data;

@Data
public class BookTicketDto {
    private String flightNumber;
    private Integer number;

    public Ticket toDomain() {
        final Ticket bookTicket = new Ticket();
        bookTicket.setFlightNumber(flightNumber);
        bookTicket.setNumber(number);
        return bookTicket;
    }
}
