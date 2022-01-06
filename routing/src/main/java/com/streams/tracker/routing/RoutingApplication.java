package com.streams.tracker.routing;

import com.streams.tracker.shared.utils.SpringBootUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RoutingApplication {
    public static void main(String[] args) {
        SpringBootUtil.setRandomPort();
        SpringApplication.run(RoutingApplication.class, args);
    }


}
