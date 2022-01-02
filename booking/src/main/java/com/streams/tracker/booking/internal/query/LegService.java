package com.streams.tracker.booking.internal.query;

import com.streams.tracker.booking.domain.dto.LegDTO;
import com.streams.tracker.booking.domain.model.Leg;
import com.streams.tracker.booking.domain.view.LegQueryVO;
import com.streams.tracker.booking.domain.view.LegUpdateVO;
import com.streams.tracker.booking.domain.view.LegVO;
import com.streams.tracker.booking.infrastructure.repositories.LegRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class LegService {

    @Autowired
    private LegRepository legRepository;

    public Integer save(LegVO vO) {
        Leg bean = new Leg();
        BeanUtils.copyProperties(vO, bean);
        bean = legRepository.save(bean);
        return bean.getId();
    }

    public void delete(Integer id) {
        legRepository.deleteById(id);
    }

    public void update(Integer id, LegUpdateVO vO) {
        Leg bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        legRepository.save(bean);
    }

    public LegDTO getById(Integer id) {
        Leg original = requireOne(id);
        return toDTO(original);
    }

    public Page<LegDTO> query(LegQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private LegDTO toDTO(Leg original) {
        LegDTO bean = new LegDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Leg requireOne(Integer id) {
        return legRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
