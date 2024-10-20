package com.sixbitproxywax.tempest.model;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

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

  public Type type() {
    return Type.OBSERVATION_TEMPEST;
  }

  public enum PrecipitationType {
    NONE(0), RAIN(1), HAIL(2), RAIN_AND_HAIL(3);

    private static Map<Integer, PrecipitationType> lookup = new HashMap<>();

    static {
      for (final var precipitationType : PrecipitationType.values()) {
        lookup.put(precipitationType.rawRepresentation(), precipitationType);
      }
    }

    private int rawRepresentation;

    PrecipitationType(final int rawRepresentation) {
      this.rawRepresentation = rawRepresentation;
    }

    public int rawRepresentation() {
      return rawRepresentation;
    }

    public static PrecipitationType parse(final int rawRepresentation) {
      return lookup.get(rawRepresentation);
    }
  }
}
