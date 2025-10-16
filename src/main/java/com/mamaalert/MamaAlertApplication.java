package com.mamaalert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MamaAlertApplication {

    public static void main(String[] args) {
        SpringApplication.run(MamaAlertApplication.class, args);
    }

}
