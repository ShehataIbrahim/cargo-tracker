package com.streams.tracker.handling.internal.command;

import com.streams.tracker.handling.domain.agregate.HandlingActivity;
import com.streams.tracker.handling.domain.command.HandlingActivityRegistrationCommand;
import com.streams.tracker.handling.domain.valueobject.BookingId;
import com.streams.tracker.handling.domain.valueobject.Location;
import com.streams.tracker.handling.domain.valueobject.Type;
import com.streams.tracker.handling.domain.valueobject.VoyageNumber;
import com.streams.tracker.handling.infrastructure.repository.HandlingActivityRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class HandlingActivityRegistrationCommandService {


    private final HandlingActivityRepository handlingActivityRepository;

    public HandlingActivityRegistrationCommandService(HandlingActivityRepository handlingActivityRepository) {
        this.handlingActivityRepository = handlingActivityRepository;
    }

    @Transactional
    public void registerHandlingActivityService(HandlingActivityRegistrationCommand handlingActivityRegistrationCommand) {
        if (!handlingActivityRegistrationCommand.getVoyageNumber().equals("")) {
            HandlingActivity handlingActivity = new HandlingActivity(
                    new BookingId(handlingActivityRegistrationCommand.getBookingId()),
                    handlingActivityRegistrationCommand.getCompletionTime(),
                    Type.valueOf(handlingActivityRegistrationCommand.getHandlingType()),
                    new Location(handlingActivityRegistrationCommand.getUnLocode()),
                    new VoyageNumber(handlingActivityRegistrationCommand.getVoyageNumber()));
            handlingActivityRepository.save(handlingActivity);

        } else {
            HandlingActivity handlingActivity = new HandlingActivity(
                    new BookingId(handlingActivityRegistrationCommand.getBookingId()),
                    handlingActivityRegistrationCommand.getCompletionTime(),
                    Type.valueOf(handlingActivityRegistrationCommand.getHandlingType()),
                    new Location(handlingActivityRegistrationCommand.getUnLocode()));
            handlingActivityRepository.save(handlingActivity);
        }


    }
}
