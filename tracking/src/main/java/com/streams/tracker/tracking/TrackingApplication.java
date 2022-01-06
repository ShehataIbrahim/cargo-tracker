package com.streams.tracker.tracking;

import com.streams.tracker.shared.utils.SpringBootUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TrackingApplication {

    public static void main(String[] args) {
        SpringBootUtil.setRandomPort();
        SpringApplication.run(TrackingApplication.class, args);
    }


}
