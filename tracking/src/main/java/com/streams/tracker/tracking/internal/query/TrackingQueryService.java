package com.streams.tracker.tracking.internal.query;

import com.streams.tracker.tracking.domain.aggregate.TrackingActivity;
import com.streams.tracker.tracking.domain.entity.BookingId;
import com.streams.tracker.tracking.infrastructure.repository.TrackingRepository;
import org.springframework.stereotype.Service;

@Service
public class TrackingQueryService {
    private final TrackingRepository trackingRepository;

    public TrackingQueryService(TrackingRepository trackingRepository) {
        this.trackingRepository = trackingRepository;
    }

    public TrackingActivity findTrackingActivities(BookingId bookingId) {
        TrackingActivity result = trackingRepository.findByBookingId(bookingId);
        if (result == null)
            throw new RuntimeException("No Tracking for this BookingId");
        return result;
    }
}
