package com.streams.tracker.handling.config;

import com.streams.tracker.handling.infrastructure.mq.EventSource;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class MockConfiguration {
    @MockBean
    EventSource eventSource;

    @Bean
    @Primary
    public EventSource getEventSource() {
        return eventSource;
    }
}
