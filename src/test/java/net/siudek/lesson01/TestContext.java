package net.siudek.lesson01;

import org.axonframework.spring.config.EnableAxon;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAxon
@ComponentScan("net.siudek.lesson01")
public class TestContext {
}