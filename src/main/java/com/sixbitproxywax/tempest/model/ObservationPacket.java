package com.sixbitproxywax.tempest.model;

import java.time.Instant;

public record ObservationPacket(
        String serialNumber, String hubSerialNumber,
        Instant timeEpoch,
        double windLull,
        double windAverage,
        double windGust,
        int windDirection,
        int windSampleInterval,
        double stationPressure,
        double airTemperature,
        double relativeHumidity,
        long illuminance,
        double uvIndex,
        long solarRadiation,
        double rainAmount,
        PrecipitationType precipitationType,
        double lightningStrikeAverageDistance,
        int lightningStrikeCount,
        double battery,
        int reportInterval,
        int firmwareRevision) implements TempestPacket {

    public PacketType type() {
        return PacketType.OBSERVATION_TEMPEST;
    }

}
