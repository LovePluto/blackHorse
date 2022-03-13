package com.wyh.dark_horse.infrastructure.client.feignclient;

import com.wyh.dark_horse.bookticket.model.BookResult;
import com.wyh.dark_horse.infrastructure.client.request.TicketRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(name = "flight", url = "https://tickets.com/book")
public interface FlightFeignClient {

    @RequestMapping(method = RequestMethod.POST, value = "/tickets")
    BookResult bookTicket(TicketRequest request);
}
