package com.streams.tracker.tracking.infrastructure.repository;


import com.streams.tracker.tracking.domain.aggregate.TrackingActivity;
import com.streams.tracker.tracking.domain.entity.BookingId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackingRepository extends JpaRepository<TrackingActivity, Long> {
    TrackingActivity findByBookingId(BookingId bookingId);

}
