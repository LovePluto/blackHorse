package com.wyh.dark_horse.api;

import com.wyh.dark_horse.api.dto.BookTicketDto;
import com.wyh.dark_horse.bookticket.BookTicketService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book/tickets")
@AllArgsConstructor
public class BookTicketController {
    private BookTicketService bookTicketService;

    @PostMapping
    public String bookTickets(BookTicketDto bookTicket) {
        return bookTicketService.create(bookTicket.toDomain());
    }
}
