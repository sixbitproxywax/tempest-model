package com.sixbitproxywax.tempest.model;

import java.util.HashMap;
import java.util.Map;

public record RadioStats(int version, long rebootCount, long i2cBusErrorCount, RadioStatus radioStatus,
    long radioNetworkId) {
  public enum RadioStatus {
    OFF(0),
    ON(1),
    ACTIVE(3),
    BLE(7);

    private static final Map<Integer, RadioStatus> lookup = new HashMap<>();

    static {
      for (final var radioStatus : RadioStatus.values()) {
        lookup.put(radioStatus.rawRepresentation(), radioStatus);
      }
    }

    private final int rawRepresentation;

    RadioStatus(final int rawRepresentation) {
      this.rawRepresentation = rawRepresentation;
    }

    public int rawRepresentation() {
      return rawRepresentation;
    }

    public static RadioStatus parse(int rawRepresentation) {
      return lookup.get(rawRepresentation);
    }
  }
}
