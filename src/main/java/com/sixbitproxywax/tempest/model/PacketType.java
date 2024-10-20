package com.sixbitproxywax.tempest.model;

import java.util.HashMap;
import java.util.Map;

public enum PacketType {
    RAIN_START_EVENT("evt_precip"),
    LIGHTNING_STRIKE_EVENT("evt_strike"),
    RAPID_WIND("rapid_wind"),
    OBSERVATION_TEMPEST("obs_st"),
    DEVICE_STATUS("device_status"),
    HUB_STATUS("hub_status");

    private static final Map<String, PacketType> lookup = new HashMap<>();

    static {
        for (final var type : PacketType.values()) {
            lookup.put(type.rawRepresentation, type);
        }
    }

    private final String rawRepresentation;

    PacketType(final String rawRepresentation) {
        this.rawRepresentation = rawRepresentation;
    }

    public static PacketType parse(String rawRepresentation) {
        return lookup.get(rawRepresentation.strip().toLowerCase());
    }

    public String rawRepresentation() {
        return rawRepresentation;
    }
}
