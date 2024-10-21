package com.sixbitproxywax.tempest.model.parsing;

import java.time.Instant;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sixbitproxywax.tempest.model.HubStatusPacket;
import com.sixbitproxywax.tempest.model.PacketType;
import com.sixbitproxywax.tempest.model.RadioStats;
import com.sixbitproxywax.tempest.model.RadioStatus;
import com.sixbitproxywax.tempest.model.RapidWindPacket;
import com.sixbitproxywax.tempest.model.ResetFlag;
import com.sixbitproxywax.tempest.model.TempestPacket;

public class TempestPacketParser {
  private static final Logger LOGGER = LoggerFactory.getLogger(TempestPacketParser.class);

  public static TempestPacket parse(final String data) {
    LOGGER.info("Parsing Packet: " + data);
    final var json = JsonParser.parseString(data).getAsJsonObject();
    return parse(json);
  }

  public static TempestPacket parse(final JsonObject json) {
    final var type = PacketType.parse(json.get("type").getAsString());
    return switch (type) {
      case RAPID_WIND -> parseRapidWindEvent(json);
      case HUB_STATUS -> parseHubStatus(json);
      default -> throw new IllegalArgumentException("Unknown packet type: " + type);
    };
  }

  private static TempestPacket parseRapidWindEvent(final JsonObject json) {
    final var serialNumber = json.get("serial_number").getAsString();
    final var hubSerialNumber = json.get("hub_sn").getAsString();
    final var observation = json.get("ob").getAsJsonArray();
    final var timeEpoch = Instant.ofEpochSecond(observation.get(0).getAsLong());
    final var windSpeed = observation.get(1).getAsDouble();
    final var direction = observation.get(2).getAsInt();
    return new RapidWindPacket(serialNumber, hubSerialNumber, timeEpoch, windSpeed, direction);
  }

  private static TempestPacket parseHubStatus(final JsonObject json) {
    final var serialNumber = json.get("serial_number").getAsString();
    final var firmwareRevision = json.get("firmware_revision").getAsString();
    final var uptime = json.get("uptime").getAsLong();
    final var rssi = json.get("rssi").getAsLong();
    final var timestamp = Instant.ofEpochSecond(json.get("timestamp").getAsLong());
    final var resetFlags = json.get("reset_flags").getAsString();
    final var parsedResetFlags = Arrays.stream(resetFlags.split(",")).map(ResetFlag::valueOf).toList();
    final var seq = json.get("seq").getAsLong();
    final var radioStats = parseRadioStats(json);
    return new HubStatusPacket(serialNumber, firmwareRevision, uptime, rssi, timestamp, parsedResetFlags, seq,
        radioStats);
  }

  private static RadioStats parseRadioStats(final JsonObject json) {

    final var rawStats = json.get("radio_stats").getAsJsonArray();
    final var version = rawStats.get(0).getAsInt();
    final var rebootCount = rawStats.get(1).getAsLong();
    final var i2cBusErrorCount = rawStats.get(2).getAsLong();
    final var radioStatus = RadioStatus.parse(rawStats.get(3).getAsInt());
    final var radioNetworkId = rawStats.get(4).getAsLong();
    return new RadioStats(version, rebootCount, i2cBusErrorCount, radioStatus, radioNetworkId);
  }
}
