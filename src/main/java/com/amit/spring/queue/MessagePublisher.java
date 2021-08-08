package com.amit.spring.queue;

public interface MessagePublisher {
    void publish(final String message);
}