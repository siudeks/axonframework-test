package net.siudek.lesson01;

import java.beans.Transient;
import java.io.Serializable;

import org.axonframework.commandhandling.callbacks.LoggingCallback;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.saga.SagaEventHandler;
import org.axonframework.eventhandling.saga.SagaLifecycle;
import org.axonframework.eventhandling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.ToString;
import lombok.val;
import lombok.extern.java.Log;

@Saga
@NoArgsConstructor
public class UniqueAggregateCreatedSaga {

    @Autowired
    private CommandGateway commandBus;
    private boolean started;

    @StartSaga()
    @SagaEventHandler(associationProperty = "name")
    @SneakyThrows
    public void on(AggregateDraftedEvent evt) {

        if (!started) {
            started = true;
            val confirmation = new AggregatePublishCommand(evt.getInstanceId());
            commandBus.send(confirmation, LoggingCallback.INSTANCE);
        } 
        else {
            //val confirmation = new AggregatePublishCommand(evt.getInstanceId());
            //commandBus.send(confirmation, LoggingCallback.INSTANCE);
        }
    }
}