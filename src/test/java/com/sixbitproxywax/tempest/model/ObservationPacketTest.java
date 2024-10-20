package com.sixbitproxywax.tempest.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObservationPacketTest {
    @Test
    void parse() {
        for (final var precipitationType : PrecipitationType.values()) {
            Assertions.assertEquals(precipitationType, PrecipitationType.parse(precipitationType.rawRepresentation()));
        }
    }
}
