package net.siudek.lesson01;

import lombok.Value;

@Value
class AggregateDraftedEvent {
    private String instanceId;
    private String name;
}
