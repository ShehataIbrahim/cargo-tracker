package com.streams.tracker.booking.config;

import com.streams.tracker.booking.infrastructure.mq.BookingEventSource;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class MockConfiguration {
    @MockBean
    BookingEventSource eventSource;

    @Bean
    @Primary
    public BookingEventSource getBookingEventSource() {
        return eventSource;
    }
}
