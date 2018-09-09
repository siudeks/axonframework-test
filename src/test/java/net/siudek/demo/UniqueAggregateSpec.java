package net.siudek.demo;

import org.assertj.core.api.Assertions;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

final class SingleAggregateSpec {

    @Autowired
    private CommandGateway commandGateway;

    @Test
    public void firstTest() {
        Assertions.assertThat(commandGateway).isNotNull();
    }
}