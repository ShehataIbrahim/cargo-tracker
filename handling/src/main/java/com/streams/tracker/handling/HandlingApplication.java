package com.streams.tracker.handling;

import com.streams.tracker.shared.utils.SpringBootUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class HandlingApplication {
    public static void main(String[] args) {
        SpringBootUtil.setRandomPort();
        SpringApplication.run(HandlingApplication.class, args);
    }


}
