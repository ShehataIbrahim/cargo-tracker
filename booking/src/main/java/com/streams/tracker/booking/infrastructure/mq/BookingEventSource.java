package com.streams.tracker.booking.infrastructure.mq;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface BookingEventSource {
    @Output("bookingChannel")
    MessageChannel bookingChannel();

    @Output("routingChannel")
    MessageChannel routingChannel();
}
