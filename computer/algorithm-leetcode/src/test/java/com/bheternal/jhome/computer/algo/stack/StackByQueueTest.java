package com.bheternal.jhome.computer.algo.stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StackByQueueTest {

    private StackByQueue stackByQueue = new StackByQueue();

    @BeforeEach
    void setUp() {
        stackByQueue = new StackByQueue();
    }

    @Test
    void push() {
        stackByQueue.push(1);
        stackByQueue.push(2);
    }

    @Test
    void pop() {
        stackByQueue.push(1);
        stackByQueue.push(2);
        assertEquals(2, stackByQueue.pop());
        assertEquals(1, stackByQueue.pop());
    }

    @Test
    void top() {
        stackByQueue.push(1);
        stackByQueue.push(2);
        assertEquals(2, stackByQueue.top());
        assertEquals(2, stackByQueue.top());
        assertEquals(2, stackByQueue.pop());
        assertEquals(1, stackByQueue.top());
    }

    @Test
    void empty() {
        assertTrue(stackByQueue.empty());
        stackByQueue.push(1);
        assertFalse(stackByQueue.empty());
    }
}