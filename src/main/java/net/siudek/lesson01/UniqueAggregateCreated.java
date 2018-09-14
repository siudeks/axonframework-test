package net.siudek.lesson01;

import org.axonframework.eventhandling.saga.SagaEventHandler;
import org.axonframework.eventhandling.saga.SagaLifecycle;
import org.axonframework.eventhandling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;

import lombok.NoArgsConstructor;

@Saga
@NoArgsConstructor
public class UniqueAggregateCreated {

    @StartSaga()
    @SagaEventHandler(associationProperty = "name")
    public void on(AggregateCreatedEvent evt) {
        SagaLifecycle.end();
    }

}