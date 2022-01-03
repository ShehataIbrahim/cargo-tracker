package com.streams.tracker.tracking.infrastructure.mq;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface HandlingBinding {

    String HANDLING = "handlingChannel";

    @Input(HANDLING)
    SubscribableChannel cargoHandling();
}
