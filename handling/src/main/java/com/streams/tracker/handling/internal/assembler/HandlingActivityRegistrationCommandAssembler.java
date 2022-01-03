package com.streams.tracker.handling.internal.assembler;

import com.streams.tracker.handling.controller.request.HandlingActivityRegistrationRequest;
import com.streams.tracker.handling.domain.command.HandlingActivityRegistrationCommand;

public class HandlingActivityRegistrationCommandAssembler {

    public static HandlingActivityRegistrationCommand toCommand(HandlingActivityRegistrationRequest handlingActivityRegistrationRequest) {
        return new HandlingActivityRegistrationCommand(
                handlingActivityRegistrationRequest.getBookingId(),
                handlingActivityRegistrationRequest.getVoyageNumber(),
                handlingActivityRegistrationRequest.getUnLocode(),
                handlingActivityRegistrationRequest.getHandlingType(),
                handlingActivityRegistrationRequest.getCompletionTime());
    }
}
