package net.siudek.lesson01;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
class StateSnapshotVievProcessor {

    private int registeredInstances = 0;

    @EventHandler
    void on(AggregatePublishedEvent evt) {
        registeredInstances++;
    }

    @QueryHandler
    StateSnapshotReply answer(StateSnapshotQuery q) {
        return new StateSnapshotReply(registeredInstances);
    }
}