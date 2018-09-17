package net.siudek.lesson01;

import java.beans.Transient;

import org.axonframework.commandhandling.callbacks.LoggingCallback;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.saga.SagaEventHandler;
import org.axonframework.eventhandling.saga.SagaLifecycle;
import org.axonframework.eventhandling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.val;
import lombok.extern.java.Log;

@Saga
@NoArgsConstructor
@Log
public class UniqueAggregateCreatedSaga {

    @Autowired
    private CommandGateway commandBus;

    @StartSaga()
    @SagaEventHandler(associationProperty = "name")
    @SneakyThrows
    public void on(AggregateDraftedEvent evt) {
        log.info("Start");

        val confirmation = new AggregatePublishCommand(evt.getInstanceId());
        log.info("Siudek1: " + confirmation.toString());
        commandBus.send(confirmation, LoggingCallback.INSTANCE);

        Thread.sleep(1000);

        log.info("Stop");

        //SagaLifecycle.end();
    }

}