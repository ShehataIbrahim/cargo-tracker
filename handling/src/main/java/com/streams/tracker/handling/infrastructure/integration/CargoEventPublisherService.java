package com.streams.tracker.handling.infrastructure.integration;

import com.streams.tracker.handling.infrastructure.mq.EventSource;
import com.streams.tracker.shared.event.CargoHandledEvent;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
@EnableBinding(EventSource.class)
public class CargoEventPublisherService {

    private final EventSource eventSource;

    public CargoEventPublisherService(EventSource eventSource) {
        this.eventSource = eventSource;
    }

    @TransactionalEventListener
    public void handleCargoHandledEvent(CargoHandledEvent cargoHandledEvent) {
        eventSource.handling().send(MessageBuilder.withPayload(cargoHandledEvent).build());
    }
}
