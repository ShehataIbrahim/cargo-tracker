package com.streams.tracker.booking.infrastructure.mq;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * Interface depicting all output channels
 */
public interface BookingEventSource {
    @Output("bookingChannel")
    MessageChannel booking();

    @Output("routingChannel")
    MessageChannel routing();
}
