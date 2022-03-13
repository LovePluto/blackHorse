package com.wyh.dark_horse.infrastructure.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ErrorBody implements Serializable {
    public static final ErrorBody ERROR = new ErrorBody(5001, "服务器未知错误，请联系客服人员！");

    private Integer code;
    private String errorMessage;

}
