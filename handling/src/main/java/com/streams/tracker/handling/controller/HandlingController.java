package com.streams.tracker.handling.controller;

import com.streams.tracker.handling.controller.request.HandlingActivityRegistrationRequest;
import com.streams.tracker.handling.domain.valueobject.BookingId;
import com.streams.tracker.handling.internal.assembler.HandlingActivityRegistrationCommandAssembler;
import com.streams.tracker.handling.internal.command.ExternalTrackingService;
import com.streams.tracker.handling.internal.command.HandlingActivityRegistrationCommandService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/")
public class HandlingController {
    private final HandlingActivityRegistrationCommandService handlingActivityRegistrationCommandService;
    private final ExternalTrackingService externalTrackingService;

    public HandlingController(HandlingActivityRegistrationCommandService handlingActivityRegistrationCommandService, ExternalTrackingService externalTrackingService) {
        this.handlingActivityRegistrationCommandService = handlingActivityRegistrationCommandService;
        this.externalTrackingService = externalTrackingService;
    }

    @PostMapping
    @ResponseBody
    public Boolean registerHandlingActivity(@RequestBody HandlingActivityRegistrationRequest handlingActivityRegistrationRequest) {
        if (externalTrackingService.validateBookingTracking(new BookingId(handlingActivityRegistrationRequest.getBookingId()))) {
            handlingActivityRegistrationCommandService.registerHandlingActivityService(HandlingActivityRegistrationCommandAssembler.toCommand(handlingActivityRegistrationRequest));
            return true;
        }
        return false;
    }
}
