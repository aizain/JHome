package com.bheternal.jhome.computer.algo.heap;

import java.util.PriorityQueue;

/**
 * KthLargest
 * 703. 数据流中的第K大元素
 * <p>
 * 设计一个找到数据流中第K大元素的类（class）。注意是排序后的第K大元素，不是第K个不同的元素。
 * 你的 KthLargest 类需要一个同时接收整数 k 和整数数组nums 的构造器，它包含数据流中的初始元素。每次调用 KthLargest.add，返回当前数据流中第K大的元素。
 * <p>
 * 示例:
 * <p>
 * int k = 3;
 * int[] arr = [4,5,8,2];
 * KthLargest kthLargest = new KthLargest(3, arr);
 * kthLargest.add(3);   // returns 4
 * kthLargest.add(5);   // returns 5
 * kthLargest.add(10);  // returns 5
 * kthLargest.add(9);   // returns 8
 * kthLargest.add(4);   // returns 8
 * 说明:
 * 你可以假设 nums 的长度≥ k-1 且k ≥ 1。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/kth-largest-element-in-a-stream
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * <p>
 * 解题：
 * 关于 Java 的 PriorityQueue 优先级队列
 * 1 是线程不安全的队列
 * 2 存储使用数组实现
 * 3 利用比较器做优先级比较
 * <p>
 * 实现说明
 * 1 最小堆的特性就是最小的值在最上面，每次取O(1)，插入O(n)
 * 2 初始化的时候，注意如何添加元素，并给队列一个合适大小的初值
 * 3 每次添加元素，能添加到队列的有两种情况，一种未到k个，另一种比堆顶大
 * <p>
 * 作者：wang-zi-hao-zain
 * 链接：https://leetcode-cn.com/problems/kth-largest-element-in-a-stream/solution/javashi-yong-zui-xiao-dui-lai-shi-xian-shi-yong-ne/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author Zain
 * @date 2019/11/7
 */
public class KthLargest {

    private final PriorityQueue<Integer> queue;
    private final int limit;

    public KthLargest(int k, int[] nums) {
        limit = k;
        queue = new PriorityQueue<>(k);
        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        if (queue.size() < limit) {
            queue.add(val);
        } else if (val > queue.peek()) {
            queue.poll();
            queue.add(val);
        }

        return queue.peek();
    }

}
