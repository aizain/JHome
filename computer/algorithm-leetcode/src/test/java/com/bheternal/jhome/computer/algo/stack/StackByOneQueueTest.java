package com.bheternal.jhome.computer.algo.stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StackByOneQueueTest {

    private StackByOneQueue stackByOneQueue = new StackByOneQueue();

    @BeforeEach
    void setUp() {
        stackByOneQueue = new StackByOneQueue();
    }

    @Test
    void push() {
        stackByOneQueue.push(1);
        stackByOneQueue.push(2);
    }

    @Test
    void pop() {
        stackByOneQueue.push(1);
        stackByOneQueue.push(2);
        stackByOneQueue.push(3);
        assertEquals(3, stackByOneQueue.pop());
        assertEquals(2, stackByOneQueue.pop());
        assertEquals(1, stackByOneQueue.pop());
    }

    @Test
    void top() {
        stackByOneQueue.push(1);
        stackByOneQueue.push(2);
        stackByOneQueue.push(3);
        assertEquals(3, stackByOneQueue.top());
        assertEquals(3, stackByOneQueue.pop());
        assertEquals(2, stackByOneQueue.top());
        assertEquals(2, stackByOneQueue.pop());
        assertEquals(1, stackByOneQueue.top());
    }

    @Test
    void empty() {
        assertTrue(stackByOneQueue.empty());
        stackByOneQueue.push(1);
        assertFalse(stackByOneQueue.empty());
    }
}