package com.sixbitproxywax.tempest.model;

import java.time.Instant;

public record DeviceStatusPacket(String serialNumber, String hubSerialNumber, Instant timeEpoch, long uptime,
                                 double voltage, int firmwareRevision, long rssi, long hubRssi, int sensorStatus,
                                 int debug)
        implements TempestPacket {
    public PacketType type() {
        return PacketType.DEVICE_STATUS;
    }
}
