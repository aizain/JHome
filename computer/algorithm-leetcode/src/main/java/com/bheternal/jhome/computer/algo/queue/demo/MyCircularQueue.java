package com.bheternal.jhome.computer.algo.queue.demo;

/**
 * MyCircularQueue
 *
 * @author Zain
 * @date 2019/10/28
 */
class MyCircularQueue {

    private static final int EMPTY = -1;
    private final int[] data;
    private int head;
    private int tail;

    /**
     * Initialize your data structure here. Set the size of the queue to be k.
     */
    public MyCircularQueue(int k) {
        data = new int[k];
        head = EMPTY;
        tail = EMPTY;
    }

    /**
     * Insert an element into the circular queue. Return true if the operation is successful.
     */
    public boolean enQueue(int value) {
        if (isEmpty()) {
            head = 0;
            tail = 0;
            data[tail] = value;
            return true;
        }
        if (isFull()) {
            return false;
        }

        tail++;
        if (tail >= data.length) {
            tail = 0;
        }

        data[tail] = value;
        return true;
    }

    /**
     * Delete an element from the circular queue. Return true if the operation is successful.
     */
    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        data[head] = EMPTY;
        if (head == tail) {
            head = EMPTY;
            tail = EMPTY;
            return true;
        }

        head++;
        if (head >= data.length) {
            head = 0;
        }

        return true;
    }

    /**
     * Get the front item from the queue.
     */
    public int Front() {
        if (isEmpty()) {
            return EMPTY;
        }
        return data[head];
    }

    /**
     * Get the last item from the queue.
     */
    public int Rear() {
        if (isEmpty()) {
            return EMPTY;
        }
        return data[tail];
    }

    /**
     * Checks whether the circular queue is empty or not.
     */
    public boolean isEmpty() {
        return head == EMPTY && tail == EMPTY;
    }

    /**
     * Checks whether the circular queue is full or not.
     */
    public boolean isFull() {
        return head - tail == 1 || tail - head == data.length - 1;
    }

}


