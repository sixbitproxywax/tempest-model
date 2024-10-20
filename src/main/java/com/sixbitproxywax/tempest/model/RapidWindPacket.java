package com.sixbitproxywax.tempest.model;

import java.time.Instant;

public record RapidWindPacket(String serialNumber, String hubSerialNumber, Instant timeEpoch, double windSpeed,
    int windDirection) implements TempestPacket {
  public Type type() {
    return Type.RAPID_WIND;
  }
}
