package net.siudek.lesson01;

import org.axonframework.eventhandling.saga.SagaEventHandler;
import org.axonframework.eventhandling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

@Saga
@NoArgsConstructor
public class UniqueAggregateCreated {

    @StartSaga
    @SagaEventHandler(associationProperty = "name")
    @SneakyThrows
    public void on(AggregateCreatedEvent evt) {
        //Thread.sleep(5000);
    }

}