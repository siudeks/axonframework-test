package net.siudek.lesson01;

import java.util.UUID;

import lombok.Value;

@Value
class AggregateCreateCommand {
    private UUID instanceId;
    private String name;
}
