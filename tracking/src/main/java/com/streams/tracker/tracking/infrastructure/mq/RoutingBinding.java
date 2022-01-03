package com.streams.tracker.tracking.infrastructure.mq;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface RoutingBinding {

    String ROUTING = "routingChannel";

    @Input(ROUTING)
    SubscribableChannel routing();
}
