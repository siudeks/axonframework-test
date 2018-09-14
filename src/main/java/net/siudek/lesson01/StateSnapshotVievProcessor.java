package net.siudek.lesson01;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import lombok.extern.java.Log;

@Component
class StateSnapshotVievProcessor {

    private int registeredInstances = 0;

    @EventHandler
    void on(AggregateCreatedEvent evt) {
        registeredInstances++;
    }

    @QueryHandler
    StateSnapshotReply answer(StateSnapshotQuery q) {
        return new StateSnapshotReply(registeredInstances);
    }
}