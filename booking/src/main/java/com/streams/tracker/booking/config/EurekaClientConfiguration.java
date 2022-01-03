package com.streams.tracker.booking.config;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("!test")
@Configuration
@EnableDiscoveryClient
public class EurekaClientConfiguration {
}
