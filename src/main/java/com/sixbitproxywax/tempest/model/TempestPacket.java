package com.sixbitproxywax.tempest.model;

import java.util.HashMap;
import java.util.Map;

public interface TempestPacket {
  String serialNumber();

  Type type();

  String hubSerialNumber();

  public enum Type {
    RAIN_START_EVENT("evt_precip"),
    LIGHTNING_STRIKE_EVENT("evt_strike"),
    RAPID_WIND("rapid_wind"),
    OBSERVATION_TEMPEST("obs_st"),
    DEVICE_STATUS("device_status"),
    HUB_STATUS("hub_status");

    private static final Map<String, Type> lookup = new HashMap<>();

    static {
      for (final var type : Type.values()) {
        lookup.put(type.rawRepresentation, type);
      }
    }

    private final String rawRepresentation;

    Type(final String rawRepresentation) {
      this.rawRepresentation = rawRepresentation;
    }

    public String rawRepresentation() {
      return rawRepresentation;
    }

    public static Type parse(String rawRepresentation) {
      return lookup.get(rawRepresentation.strip().toLowerCase());
    }
  }
}
