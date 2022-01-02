package com.streams.tracker.booking.internal.query;

import com.streams.tracker.booking.domain.dto.CargoDTO;
import com.streams.tracker.booking.domain.model.Cargo;
import com.streams.tracker.booking.infrastructure.repositories.CargoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class CargoQueryService {

    private final CargoRepository cargoRepository;

    public CargoQueryService(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    public CargoDTO getById(Integer id) {
        Cargo original = requireOne(id);
        return toDTO(original);
    }

    public CargoDTO find(String bookingId) {
        Cargo original = cargoRepository.findByBookingId(bookingId);
        return toDTO(original);
    }

    private CargoDTO toDTO(Cargo original) {
        CargoDTO bean = new CargoDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Cargo requireOne(Integer id) {
        return cargoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
