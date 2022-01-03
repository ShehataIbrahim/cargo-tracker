package com.streams.tracker.handling.infrastructure.mq;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface EventSource {

    @Output("handlingChannel")
    MessageChannel handling();

}
