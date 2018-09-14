package net.siudek.lesson01;

import java.util.UUID;

import org.axonframework.commandhandling.model.AggregateIdentifier;

import lombok.Value;

@Value
class AggregateCreateCommand {

    @AggregateIdentifier
    private UUID instanceId;
    private String name;
}
