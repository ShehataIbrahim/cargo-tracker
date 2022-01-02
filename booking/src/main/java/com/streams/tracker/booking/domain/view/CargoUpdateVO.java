package com.streams.tracker.booking.domain.view;


import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@ApiModel("Update ")
@EqualsAndHashCode(callSuper = false)
public class CargoUpdateVO extends CargoVO implements Serializable {
    private static final long serialVersionUID = 1L;

}
