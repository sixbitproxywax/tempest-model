package com.sixbitproxywax.tempest.model;

import java.util.HashMap;
import java.util.Map;

public enum PrecipitationType {
    NONE(0),
    RAIN(1),
    HAIL(2),
    RAIN_AND_HAIL(3);

    private static final Map<Integer, PrecipitationType> lookup = new HashMap<>();

    static {
        for (final var precipitationType : PrecipitationType.values()) {
            lookup.put(precipitationType.rawRepresentation(), precipitationType);
        }
    }

    private final int rawRepresentation;

    PrecipitationType(final int rawRepresentation) {
        this.rawRepresentation = rawRepresentation;
    }

    public static PrecipitationType parse(final int rawRepresentation) {
        return lookup.get(rawRepresentation);
    }

    public int rawRepresentation() {
        return rawRepresentation;
    }
}
