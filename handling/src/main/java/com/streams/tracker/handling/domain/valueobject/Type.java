package com.streams.tracker.handling.domain.valueobject;

public enum Type {
    LOAD(true),
    UNLOAD(true),
    RECEIVE(false),
    CLAIM(false),
    CUSTOMS(false);
    private final boolean voyageRequired;

    Type(boolean voyageRequired) {
        this.voyageRequired = voyageRequired;
    }

    public boolean requiresVoyage() {
        return voyageRequired;
    }

    public boolean prohibitsVoyage() {
        return !requiresVoyage();
    }

}