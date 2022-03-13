package com.wyh.dark_horse.bookticket.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookResult {
    private Boolean result;
    private String bookId;
    private String flightNumber;
    private String number;
    private String price;
    private String id;
    private String errorMessage;
}
