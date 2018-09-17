package net.siudek.lesson01;

import java.util.UUID;

import lombok.Value;

@Value
class AggregateCreatedEvent {
    private UUID instanceId;
    private String name;
}
