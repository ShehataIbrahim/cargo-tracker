package com.streams.tracker.routing.domain.aggregate;

import com.streams.tracker.routing.domain.valueobject.Schedule;
import com.streams.tracker.routing.domain.valueobject.VoyageNumber;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Voyage voyage = (Voyage) o;
        return id != null && Objects.equals(id, voyage.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
