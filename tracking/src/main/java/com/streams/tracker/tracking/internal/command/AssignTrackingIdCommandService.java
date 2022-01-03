package com.streams.tracker.tracking.internal.command;

import com.streams.tracker.tracking.domain.aggregate.TrackingActivity;
import com.streams.tracker.tracking.domain.aggregate.TrackingNumber;
import com.streams.tracker.tracking.domain.command.AddTrackingEventCommand;
import com.streams.tracker.tracking.domain.command.AssignTrackingNumberCommand;
import com.streams.tracker.tracking.domain.entity.BookingId;
import com.streams.tracker.tracking.infrastructure.repository.TrackingRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;


@Service
public class AssignTrackingIdCommandService {


    private TrackingRepository trackingRepository;


    public AssignTrackingIdCommandService(TrackingRepository trackingRepository) {
        this.trackingRepository = trackingRepository;
    }

    public TrackingNumber assignTrackingNumberToCargo(AssignTrackingNumberCommand assignTrackingNumberCommand) {
        String random = UUID.randomUUID().toString().toUpperCase();
        String trackingNumber = random.substring(0, random.indexOf("-"));
        assignTrackingNumberCommand.setTrackingNumber(trackingNumber);
        TrackingActivity activity = new TrackingActivity(assignTrackingNumberCommand);

        trackingRepository.save(activity);
        return new TrackingNumber(trackingNumber);
    }

    @Transactional
    public void addTrackingEvent(AddTrackingEventCommand addTrackingEventCommand) {
        TrackingActivity trackingActivity = trackingRepository.findByBookingId(
                new BookingId(addTrackingEventCommand.getBookingId()));

        trackingActivity.addTrackingEvent(addTrackingEventCommand);
        trackingRepository.save(trackingActivity);
    }


}
