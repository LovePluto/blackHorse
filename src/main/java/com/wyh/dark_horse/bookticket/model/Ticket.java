package com.wyh.dark_horse.bookticket.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    private String id;
    private String flightNumber;
    private Integer number;
    private BigDecimal price;
    private String confirmId;
    private LocalDateTime createdAt;
    private TicketStatus status;

    public void init() {
        this.id = UUID.randomUUID().toString().replaceAll("-", "");
        this.createdAt = LocalDateTime.now();
        this.status = TicketStatus.WAITING_CONFIRM;
    }

    public void update(BookResult bookResult) {
        if (bookResult.getResult()) {
            this.price = new BigDecimal(bookResult.getPrice());
            this.status = TicketStatus.CONFIRM;
            this.confirmId = bookResult.getId();
        }
    }
}
