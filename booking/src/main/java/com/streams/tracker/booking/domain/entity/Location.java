package com.streams.tracker.booking.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Location {

    @Column(name = "origin_id", insertable = false, updatable = false)
    private String unLocCode;
}
