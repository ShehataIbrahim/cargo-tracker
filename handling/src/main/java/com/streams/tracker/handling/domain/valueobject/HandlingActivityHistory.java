package com.streams.tracker.handling.domain.valueobject;

import com.streams.tracker.handling.domain.agregate.HandlingActivity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HandlingActivityHistory {

    private final List<HandlingActivity> handlingEvents;

    public HandlingActivityHistory(Collection<HandlingActivity> handlingEvents) {

        this.handlingEvents = new ArrayList<>(handlingEvents);
    }

    private boolean sameValueAs(HandlingActivityHistory other) {
        return other != null
                && this.handlingEvents.equals(other.handlingEvents);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        HandlingActivityHistory other = (HandlingActivityHistory) o;
        return sameValueAs(other);
    }

    @Override
    public int hashCode() {
        return handlingEvents.hashCode();
    }

}
