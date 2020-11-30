package com.bheternal.jhome.computer.algo.queue.demo;

import org.junit.jupiter.api.Test;

class MyCircularQueueTest {

    /**
     * 所有的值都在 0 至 1000 的范围内；
     * 操作数将在 1 至 1000 的范围内；
     * 请不要使用内置的队列库。
     * <p>
     * Your MyCircularQueue object will be instantiated and called as such:
     * MyCircularQueue obj = new MyCircularQueue(k);
     * boolean param_1 = obj.enQueue(value);
     * boolean param_2 = obj.deQueue();
     * int param_3 = obj.Front();
     * int param_4 = obj.Rear();
     * boolean param_5 = obj.isEmpty();
     * boolean param_6 = obj.isFull();
     */
    @Test
    void test() {
        int k = 64;
        int value = 1;
        MyCircularQueue obj = new MyCircularQueue(k);
        boolean param_1 = obj.enQueue(value);
        boolean param_2 = obj.deQueue();
        int param_3 = obj.Front();
        int param_4 = obj.Rear();
        boolean param_5 = obj.isEmpty();
        boolean param_6 = obj.isFull();


        MyCircularQueue circularQueue = new MyCircularQueue(3); // 设置长度为 3

        circularQueue.enQueue(1);  // 返回 true
        circularQueue.enQueue(2);  // 返回 true
        circularQueue.enQueue(3);  // 返回 true
        circularQueue.enQueue(4);  // 返回 false，队列已满
        circularQueue.Rear();  // 返回 3
        circularQueue.isFull();  // 返回 true
        circularQueue.deQueue();  // 返回 true
        circularQueue.enQueue(4);  // 返回 true
        circularQueue.Rear();  // 返回 4
    }

    @Test
    void testSimpleCircularQueue() {

        SimpleCircularQueue circularQueue = new SimpleCircularQueue(3); // 设置长度为 3

        circularQueue.enQueue(1);  // 返回 true
        circularQueue.enQueue(2);  // 返回 true
        circularQueue.enQueue(3);  // 返回 true
        circularQueue.enQueue(4);  // 返回 false，队列已满
        circularQueue.Rear();  // 返回 3
        circularQueue.isFull();  // 返回 true
        circularQueue.deQueue();  // 返回 true
        circularQueue.enQueue(4);  // 返回 true
        circularQueue.Rear();  // 返回 4
    }

}