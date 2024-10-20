package com.sixbitproxywax.tempest.model;

import java.util.List;

public record HubStatusPacket(String serialNumber, String hubSerialNumber, int firmwareRevision, long uptime, long rssi,
    List<ResetFlag> resetFlags, long seq, String radioStats) implements TempestPacket {

  public Type type() {
    return Type.HUB_STATUS;
  }

  public enum ResetFlag {
    BOR, PIN, POR, SFT, WDG, WWD, LPW, HRDFLT;
  }
}
