package com.streams.tracker.booking.domain.dto;


import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ApiModel("")
public class LegDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private LocalDateTime loadTime;

    private LocalDateTime unloadTime;

    private String loadLocationId;

    private String unloadLocationId;

    private String voyageNumber;

    private Integer cargoId;

}
