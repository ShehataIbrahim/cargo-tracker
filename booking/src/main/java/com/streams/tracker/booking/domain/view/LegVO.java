package com.streams.tracker.booking.domain.view;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@ApiModel("Save ")
public class LegVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "id can not null")
    private Integer id;

    private LocalDateTime loadTime;

    private LocalDateTime unloadTime;

    private String loadLocationId;

    private String unloadLocationId;

    private String voyageNumber;

    private Integer cargoId;

}
