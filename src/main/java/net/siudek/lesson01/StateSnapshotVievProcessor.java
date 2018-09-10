package net.siudek.lesson01;

import java.util.HashMap;
import java.util.Optional;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
class StateSnapshotVievProcessor {


    private HashMap<String, Integer> instances = new HashMap<>();

    @EventHandler
    void on(AggregateCreatedEvent evt) {
        instances.compute(evt.getName() , (k, v) -> Optional.ofNullable(v).orElse(0) + 1);
    }

    @QueryHandler
    StateSnapshotReply answer(StateSnapshotQuery q) {
        return new StateSnapshotReply(instances.values().size());
    }
}