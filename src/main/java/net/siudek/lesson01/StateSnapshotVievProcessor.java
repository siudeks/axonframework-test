package net.siudek.lesson01;

import java.util.concurrent.atomic.AtomicInteger;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
class StateSnapshotVievProcessor {

    private AtomicInteger registeredInstances = new AtomicInteger();

    @EventHandler
    void on(AggregatePublishedEvent evt) {
        registeredInstances.incrementAndGet();
    }

    @QueryHandler
    StateSnapshotReply answer(StateSnapshotQuery q) {
        return new StateSnapshotReply(registeredInstances.get());
    }
}