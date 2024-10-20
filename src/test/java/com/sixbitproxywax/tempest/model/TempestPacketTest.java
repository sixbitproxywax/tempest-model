package com.sixbitproxywax.tempest.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.sixbitproxywax.tempest.model.TempestPacket.Type;

class TempestPacketTest {
  @Test
  void parse() {
    for (final var type : Type.values()) {
      final var rawRepresentation = type.rawRepresentation();
      Assertions.assertEquals(type, Type.parse(rawRepresentation));
    }
  }
}
