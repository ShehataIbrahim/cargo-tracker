package com.streams.tracker.booking;

import com.streams.tracker.booking.controller.BookingController;
import com.streams.tracker.booking.controller.request.BookCargoRequest;
import com.streams.tracker.booking.domain.aggregate.BookingId;
import com.streams.tracker.booking.domain.aggregate.Cargo;
import com.streams.tracker.booking.infrastructure.mq.BookingEventSource;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.messaging.MessageChannel;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {BookingController.class})
@ActiveProfiles("test")
@AutoConfigureTestDatabase
@ComponentScan(basePackages = {"com.streams.tracker.booking"})
@RunWith(SpringRunner.class)
class BookingControllerTest {

    @Autowired
    BookingController controller;

    @Autowired
    BookingEventSource eventSource;


    @Mock
    MessageChannel channel;

    @Test
    void findByBookingId() throws Exception {
        Cargo result = controller.findByBookingId("C30F141C");
        assertNotNull(result);
        assertEquals("C30F141C", result.getBookingId().getBookingId());
        assertEquals(13, result.getBookingAmount().getBookingAmount().intValue());
        assertNull(result.getOrigin());
        assertEquals("PLKRK", result.getRouteSpecification().getOrigin().getUnLocCode());

    }

    @Test
    void book() {
        when(eventSource.bookingChannel()).thenReturn(channel);
        when(channel.send(any())).thenReturn(true);
        BookCargoRequest request = new BookCargoRequest();
        request.setBookingAmount(1);
        request.setDestLocation("UKLDN");
        request.setOriginLocation("EGCAI");
        request.setDestArrivalDeadline(new Date(new Date().getTime() + (1000 * 60 * 60 * 240)));
        BookingId result = controller.book(request);
        assertNotNull(result);
        Cargo cargo = controller.findByBookingId(result.getBookingId());
        assertNotNull(cargo);
        //verify channel received CargoBooked Command
        verify(channel, times(1)).send(any());
    }

    @TestConfiguration
    public static class BookingControllerTestConfig {
        @MockBean
        BookingEventSource eventSource;

        @Bean
        @Primary
        public BookingEventSource getMockedBean() {
            return eventSource;
        }
    }
}