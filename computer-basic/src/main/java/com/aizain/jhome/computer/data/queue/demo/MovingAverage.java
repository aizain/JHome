package com.aizain.jhome.computer.data.queue.demo;

/**
 * MovingAverage
 *
 * @author Zain
 * @date 2019/10/29
 */
class MovingAverage {

    private SimpleCircularQueue queue;

    /**
     * Initialize your data structure here.
     */
    public MovingAverage(int size) {
        this.queue = new SimpleCircularQueue(size);
    }

    public double next(int val) {
        if (queue.isFull()) {
            queue.deQueue();
        }
        queue.enQueue(val);
        int[] array = queue.getArray();
        double sum = 0;
        for (int i : array) {
            sum += i;
        }

        return sum / queue.getSize();
    }
}
