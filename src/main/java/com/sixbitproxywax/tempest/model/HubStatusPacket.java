package com.sixbitproxywax.tempest.model;

import java.time.Instant;
import java.util.List;

public record HubStatusPacket(
        String serialNumber,
        String firmwareRevision,
        long uptime, // todo(pwright) duration?
        long rssi,
        Instant timeStamp,
        List<ResetFlag> resetFlags,
        long seq,
        RadioStats radioStats) implements TempestPacket {

    public PacketType type() {
        return PacketType.HUB_STATUS;
    }

}
