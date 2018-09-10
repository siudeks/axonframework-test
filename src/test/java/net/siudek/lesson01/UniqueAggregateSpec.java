package net.siudek.lesson01;

import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.axonframework.commandhandling.callbacks.LoggingCallback;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.SneakyThrows;
import lombok.val;
import net.siudek.TestContext;

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
    public void firstTest() {
        val cmd = new CreateAggregateCommand(UUID.randomUUID(), "my unique name");
        commandGateway.send(cmd, LoggingCallback.INSTANCE);

        val reply = queryGateway
            .query(new StateSnapshotQuery(), StateSnapshotReply.class)
            .get();

        Assertions.assertThat(reply.getUniqueInstances()).isEqualTo(3);
    }
}