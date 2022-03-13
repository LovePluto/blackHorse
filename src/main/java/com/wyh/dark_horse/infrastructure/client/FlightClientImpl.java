package com.wyh.dark_horse.infrastructure.client;

import com.wyh.dark_horse.bookticket.client.FlightClient;
import com.wyh.dark_horse.bookticket.model.BookResult;
import com.wyh.dark_horse.bookticket.model.Ticket;
import com.wyh.dark_horse.infrastructure.client.feignclient.FlightFeignClient;
import com.wyh.dark_horse.infrastructure.client.request.TicketRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FlightClientImpl implements FlightClient {

    private final FlightFeignClient flightFeignClient;

    @Override
    public BookResult bookTicket(Ticket ticket) {
        return flightFeignClient.bookTicket(TicketRequest.from(ticket));
    }
}
