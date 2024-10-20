package com.sixbitproxywax.tempest.model.parsing;

import com.sixbitproxywax.tempest.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;

class TempestPacketParserTest {
    @Test
    void parseRapidWindEvent() {
        final var rawJson =
                """
                        {
                          "serial_number": "SK-00008453",
                          "type":"rapid_wind",
                          "hub_sn": "HB-00000001",
                          "ob":[1493322445,2.3,128]
                        }
                        """;
        final var expected = new RapidWindPacket(
                "SK-00008453",
                "HB-00000001",
                Instant.ofEpochSecond(1493322445),
                2.3,
                128
        );

        final var parsedPacket = TempestPacketParser.parse(rawJson);

        Assertions.assertEquals(expected, parsedPacket);
    }

    @Test
    void parseHubStatus() {
        final var rawJson =
                """
                        {
                          "serial_number":"HB-00000001",
                          "type":"hub_status",
                          "firmware_revision":"35",
                          "uptime":1670133,
                          "rssi":-62,
                          "timestamp":1495724691,
                          "reset_flags": "BOR,PIN,POR",
                          "seq": 48,
                          "fs": [1, 0, 15675411, 524288],
                          "radio_stats": [2, 1, 0, 3, 2839],
                          "mqtt_stats": [1, 0]
                        }
                        """;
        final var expected = new HubStatusPacket(
                "HB-00000001",
                "35",
                1670133,
                -62,
                Instant.ofEpochSecond(1495724691),
                List.of(ResetFlag.BOR, ResetFlag.PIN, ResetFlag.POR),
                48,
                new RadioStats(2, 1, 0, RadioStatus.ACTIVE, 2839)
        );

        final var parsedPacket = TempestPacketParser.parse(rawJson);
        Assertions.assertEquals(expected, parsedPacket);
    }
}
