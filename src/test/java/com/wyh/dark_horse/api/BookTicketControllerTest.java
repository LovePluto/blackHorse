package com.wyh.dark_horse.api;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.wyh.dark_horse.DarkHorseApplication;
import com.wyh.dark_horse.api.dto.BookTicketDto;
import com.wyh.dark_horse.bookticket.model.BookResult;
import com.wyh.dark_horse.infrastructure.client.feignclient.FlightFeignClient;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.catalina.security.SecurityConfig;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import static io.restassured.RestAssured.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;


@RunWith(SpringRunner.class)
@Import({SecurityConfig.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class, TransactionDbUnitTestExecutionListener.class,
        MockitoTestExecutionListener.class})
@AutoConfigureTestDatabase(replace = NONE)
@SpringBootTest(classes = DarkHorseApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookTicketControllerTest {
    @MockBean
    private FlightFeignClient flightFeignClient;
    @LocalServerPort
    private int port;

    @Before
    public void setup() {
        RestAssured.port = port;
    }

    @Test
    public void should_book_tickets_successfully() {

        BookTicketDto request = new BookTicketDto();
        request.setNumber(1);
        request.setFlightNumber("flightNumber");
        BookResult bookResult = BookResult.builder()
                .result(true)
                .id("id")
                .price("22")
                .build();
        when(flightFeignClient.bookTicket(any())).thenReturn(bookResult);

        given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/book/tickets")
                .then()
                .statusCode(200);
    }
}
