package com.streams.tracker.handling.infrastructure.repository;

import com.streams.tracker.handling.domain.agregate.HandlingActivity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HandlingActivityRepository extends JpaRepository<HandlingActivity, Long> {
    HandlingActivity findByBookingId(String bookingId);
}
