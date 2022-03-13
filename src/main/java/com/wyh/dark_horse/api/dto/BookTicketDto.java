package com.wyh.dark_horse.api.dto;

import com.wyh.dark_horse.bookticket.model.BookTicket;
import lombok.Data;

@Data
public class BookTicketDto {
    private String flightNumber;
    private String number;

    public BookTicket toDomain() {
        return new BookTicket();
    }
}
