package com.aizain.jhome.computer.data.queue;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
class QueueByStackTest {

    private QueueByStack queueByStack = new QueueByStack();

    @BeforeEach
    void setUp() {
        queueByStack = new QueueByStack();
    }

    @Test
    void push() {
        queueByStack.push(1);
        queueByStack.push(2);
    }

    @Test
    void pop() {
        queueByStack.push(1);
        queueByStack.push(2);
        assertEquals(1, queueByStack.pop());
        assertEquals(2, queueByStack.pop());
    }

    @Test
    void peek() {
        queueByStack.push(1);
        queueByStack.push(2);
        assertEquals(1, queueByStack.peek());
        assertEquals(1, queueByStack.peek());
        assertEquals(1, queueByStack.pop());
        assertEquals(2, queueByStack.peek());
    }

    @Test
    void empty() {
        assertTrue(queueByStack.empty());
        queueByStack.push(1);
        assertFalse(queueByStack.empty());
    }
}