package com.wyh.dark_horse.api;

import com.wyh.dark_horse.api.dto.BookTicketDto;
import com.wyh.dark_horse.bookticket.model.BookResult;
import com.wyh.dark_horse.infrastructure.client.feignclient.FlightFeignClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BookTicketControllerTest {
    @MockBean
    private FlightFeignClient flightFeignClient;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void should_book_tickets_successfully() throws Exception {

        BookTicketDto request = new BookTicketDto();
        request.setNumber(1);
        request.setFlightNumber("flightNumber");
        BookResult bookResult = BookResult.builder()
                .result(true)
                .id("id")
                .price("22")
                .build();
        when(flightFeignClient.bookTicket(any())).thenReturn(bookResult);

        mockMvc.perform(post("/book/tickets")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("id=id&price=22"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void should_book_tickets_failed_when_ticket_is_not_enough() throws Exception {
        BookTicketDto request = new BookTicketDto();
        request.setNumber(1);
        request.setFlightNumber("flightNumber");
        BookResult bookResult = BookResult.builder()
                .result(false)
                .errorMessage("预订机票失败")
                .build();
        when(flightFeignClient.bookTicket(any())).thenReturn(bookResult);

        mockMvc.perform(post("/book/tickets")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("id=id&price=22"))
                .andDo(print())
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.code").value("4001"))
                .andExpect(jsonPath("$.errorMessage").value("预订机票失败"));
    }

    @Test
    public void should_book_tickets_failed_when_third_system_is_unavailable() throws Exception {
        BookTicketDto request = new BookTicketDto();
        request.setNumber(1);
        request.setFlightNumber("flightNumber");

        when(flightFeignClient.bookTicket(any())).thenThrow(new NullPointerException());

        mockMvc.perform(post("/book/tickets")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("id=id&price=22"))
                .andDo(print())
                .andExpect(status().isServiceUnavailable())
                .andExpect(jsonPath("$.code").value("5002"))
                .andExpect(jsonPath("$.errorMessage").value("预订机票失败，请稍后重试或拨打电话 123456"));
    }
}
