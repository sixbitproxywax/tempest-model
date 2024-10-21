package com.sixbitproxywax.tempest.model;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum RadioStatus {
  OFF(0),
  ON(1),
  ACTIVE(3),
  BLE(7),
  UNKNOWN(-1);

  private static final Logger LOGGER = LoggerFactory.getLogger(RadioStatus.class);
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

  public static RadioStatus parse(int rawRepresentation) {
    final var status = lookup.getOrDefault(rawRepresentation, UNKNOWN);
    if (status == UNKNOWN) {
      LOGGER.warn("Unknown Radio Status:" + rawRepresentation);
    }
    return status;
  }

  public int rawRepresentation() {
    return rawRepresentation;
  }
}
