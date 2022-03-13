package com.wyh.dark_horse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DarkHorseApplication {

    public static void main(String[] args) {
        SpringApplication.run(DarkHorseApplication.class, args);
    }

}
