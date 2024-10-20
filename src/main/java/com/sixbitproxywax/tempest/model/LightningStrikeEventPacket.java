package com.sixbitproxywax.tempest.model;

import java.time.Instant;

public record LightningStrikeEventPacket(String serialNumber, String hubSerialNumber, Instant timeEpoch, int distance,
                                         long energy)
        implements TempestPacket {
    public PacketType type() {
        return PacketType.LIGHTNING_STRIKE_EVENT;
    }
}
