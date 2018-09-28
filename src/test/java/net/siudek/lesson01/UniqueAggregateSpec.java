package net.siudek.lesson01;

import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.axonframework.commandhandling.callbacks.LoggingCallback;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.saga.SagaRepository;
import org.axonframework.eventhandling.saga.repository.AnnotatedSagaRepository;
import org.axonframework.eventhandling.saga.repository.SagaStore;
import org.axonframework.eventhandling.saga.repository.inmemory.InMemorySagaStore;
import org.axonframework.queryhandling.QueryGateway;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.SneakyThrows;
import lombok.val;
import net.siudek.lesson01.TestContext;

/**
 * I found a practical problem how to guarantee unique aggregates in Axon
 * where you can simply create two Aggregates for the same Entity. It is technically possible
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestContext.class)
public class UniqueAggregateSpec {

    @Autowired
    private CommandGateway commandGateway;

    @Autowired
    private QueryGateway queryGateway;
    
    @Test
    @SneakyThrows
    public void shouldNotCalcDuplicatedState() {

        val uniqueName = "my unique name";
        val cmd1 = new AggregateCreateCommand(UUID.randomUUID(), uniqueName);
        commandGateway.send(cmd1, LoggingCallback.INSTANCE);

        Thread.sleep(300);

        val cmd2 = new AggregateCreateCommand(UUID.randomUUID(), uniqueName);
        commandGateway.send(cmd2, LoggingCallback.INSTANCE);

        Thread.sleep(300);

        val reply = queryGateway
            .query(new StateSnapshotQuery(), StateSnapshotReply.class)
            .get();

        Assertions.assertThat(reply.getUniqueInstances()).isEqualTo(1);
    }
}