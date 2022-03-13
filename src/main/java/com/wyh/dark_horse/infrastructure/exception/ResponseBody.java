package com.wyh.dark_horse.infrastructure.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseBody {
    public static final ResponseBody ERROR = new ResponseBody(5001, "服务器未知错误，请联系客服人员！");

    private Integer code;
    private String errorMessage;

}
