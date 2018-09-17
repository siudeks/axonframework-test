package net.siudek.lesson01;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

import lombok.Value;

@Value
class AggregatePublishCommand {

    @TargetAggregateIdentifier
    private String instanceId;
}
