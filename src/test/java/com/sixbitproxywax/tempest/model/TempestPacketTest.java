package com.sixbitproxywax.tempest.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TempestPacketTest {
    @Test
    void parse() {
        for (final var type : PacketType.values()) {
            final var rawRepresentation = type.rawRepresentation();
            Assertions.assertEquals(type, PacketType.parse(rawRepresentation));
        }
    }
}
