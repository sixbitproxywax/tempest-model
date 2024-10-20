package com.sixbitproxywax.tempest.model;

public record RadioStats(
        int version,
        long rebootCount,
        long i2cBusErrorCount,
        RadioStatus radioStatus,
        long radioNetworkId) {
}
