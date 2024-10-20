package com.sixbitproxywax.tempest.model;

import java.time.Instant;

public record LightningStrikeEventPacket(String serialNumber, String hubSerialNumber, Instant timeEpoch, int distance,
    long energy)
    implements TempestPacket {
  public Type type() {
    return Type.LIGHTNING_STRIKE_EVENT;
  }
}
