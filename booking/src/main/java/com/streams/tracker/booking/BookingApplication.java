package com.streams.tracker.booking;

import com.streams.tracker.shared.utils.SpringBootUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookingApplication {
    public static void main(String[] args) {
        SpringBootUtil.setRandomPort();
        SpringApplication.run(BookingApplication.class, args);
    }


}
