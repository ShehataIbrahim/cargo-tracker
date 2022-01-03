package com.streams.tracker.booking.internal.integration;

import com.streams.tracker.booking.infrastructure.mq.BookingEventSource;
import com.streams.tracker.shared.event.CargoBookedEvent;
import com.streams.tracker.shared.event.CargoRoutedEvent;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

@Log4j2
@Service
@EnableBinding(BookingEventSource.class)
public class EventPublisherService {
    final
    BookingEventSource eventSource;

    public EventPublisherService(BookingEventSource eventSource) {
        this.eventSource = eventSource;
    }

    @TransactionalEventListener
    public void handleCargoBookedEvent(CargoBookedEvent cargoBookedEvent) {
        try {
            log.info("received event ->:{}", cargoBookedEvent);
            eventSource.bookingChannel().send(MessageBuilder.withPayload(cargoBookedEvent).build());
            log.info("message sent");
        } catch (Exception ex) {
            log.error("Error while processing CargoBookedEvent", ex);
        }
    }

    @TransactionalEventListener
    public void handleCargoRoutedEvent(CargoRoutedEvent cargoRoutedEvent) {
        eventSource.routingChannel().send(MessageBuilder.withPayload(cargoRoutedEvent).build());
    }
}
