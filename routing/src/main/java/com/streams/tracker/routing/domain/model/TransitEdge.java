package com.streams.tracker.routing.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransitEdge implements Serializable {

    private String voyageNumber;
    private String fromUnLoCode;
    private String toUnLoCode;
    private Date fromDate;
    private Date toDate;
}