package net.siudek.lesson01;

import lombok.Value;

@Value
class AggregatePublishedEvent {
    private String instanceId;
    private String name;
}
