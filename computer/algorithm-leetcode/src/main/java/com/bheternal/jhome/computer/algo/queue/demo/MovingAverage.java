package com.bheternal.jhome.computer.algo.queue.demo;

/**
 * MovingAverage
 * 数据流中的移动平均值
 * <p>
 * 给定一个整数数据流和一个窗口大小，根据该滑动窗口的大小，计算其所有整数的移动平均值。
 * <p>
 * 示例:
 * <p>
 * MovingAverage m = new MovingAverage(3);
 * m.next(1) = 1
 * m.next(10) = (1 + 10) / 2
 * m.next(3) = (1 + 10 + 3) / 3
 * m.next(5) = (10 + 3 + 5) / 3
 *
 * @author Zain
 * @date 2019/10/29
 */
class MovingAverage {

    private final SimpleCircularQueue queue;

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
