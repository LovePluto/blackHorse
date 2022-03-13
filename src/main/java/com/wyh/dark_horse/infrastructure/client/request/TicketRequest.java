package com.wyh.dark_horse.infrastructure.client.request;

import com.wyh.dark_horse.bookticket.model.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketRequest {
    private String bookId;
    private String flightNumber;
    private Integer number;
    private LocalDateTime createdAt;

    public static TicketRequest from(Ticket ticket) {
        return new TicketRequest(ticket.getId(), ticket.getFlightNumber(),
                ticket.getNumber(), ticket.getCreatedAt());
    }
}
