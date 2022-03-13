package com.wyh.dark_horse.infrastructure.db.entity;

import com.wyh.dark_horse.bookticket.model.Ticket;
import com.wyh.dark_horse.bookticket.model.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_ticket")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TicketEntity {
    @Id
    private String id;
    private String flightNumber;
    private Integer number;
    private BigDecimal price;
    private String confirmId;
    private LocalDateTime createdAt;
    private TicketStatus status;

    public static TicketEntity from(Ticket ticket) {
        return new TicketEntity(ticket.getId(), ticket.getFlightNumber(), ticket.getNumber(),
                ticket.getPrice(), ticket.getConfirmId(), ticket.getCreatedAt(), ticket.getStatus());
    }
}
