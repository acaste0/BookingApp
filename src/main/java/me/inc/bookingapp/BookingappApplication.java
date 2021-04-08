package me.inc.bookingapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
public class BookingappApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookingappApplication.class, args);
    }

}
