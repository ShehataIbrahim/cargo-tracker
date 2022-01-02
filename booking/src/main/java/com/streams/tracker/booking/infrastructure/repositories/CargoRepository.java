package com.streams.tracker.booking.infrastructure.repositories;

import com.streams.tracker.booking.domain.model.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CargoRepository extends JpaRepository<Cargo, Integer>, JpaSpecificationExecutor<Cargo> {
    Cargo findByBookingId(String bookingId);
}