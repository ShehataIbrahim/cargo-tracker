package com.streams.tracker.booking.infrastructure.repository;

import com.streams.tracker.booking.domain.aggregate.BookingId;
import com.streams.tracker.booking.domain.aggregate.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CargoRepository extends JpaRepository<Cargo, Integer>, JpaSpecificationExecutor<Cargo> {
    Cargo findByBookingId(BookingId bookingId);
}