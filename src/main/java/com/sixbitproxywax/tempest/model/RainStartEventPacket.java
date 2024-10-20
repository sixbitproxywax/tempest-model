package com.sixbitproxywax.tempest.model;

import java.time.Instant;

public record RainStartEventPacket(String serialNumber, String hubSerialNumber, Instant timeEpoch)
    implements TempestPacket {
  public Type type() {
    return Type.RAIN_START_EVENT;
  }
}
