package com.streams.tracker.routing.domain.aggregate;

import com.streams.tracker.routing.domain.valueobject.Schedule;
import com.streams.tracker.routing.domain.valueobject.VoyageNumber;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Voyage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    @NotNull
    private Schedule schedule;

    @Embedded
    private VoyageNumber voyageNumber;


}
