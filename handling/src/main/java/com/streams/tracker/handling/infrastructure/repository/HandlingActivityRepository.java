package com.streams.tracker.handling.infrastructure.repository;

import com.streams.tracker.handling.domain.agregate.HandlingActivity;
import com.streams.tracker.handling.domain.valueobject.BookingId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HandlingActivityRepository extends JpaRepository<HandlingActivity, Long> {
    HandlingActivity findByBookingId(BookingId cargoBookingId);
}
