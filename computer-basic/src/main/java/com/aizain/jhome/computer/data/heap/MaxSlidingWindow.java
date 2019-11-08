package com.aizain.jhome.computer.data.heap;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

/**
 * MaxSlidingWindow
 * 239. 滑动窗口最大值
 * <p>
 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * 返回滑动窗口中的最大值。
 * <p>
 * 示例:
 * <p>
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 * <p>
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 *  
 * 提示：
 * 你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。
 * <p>
 * 进阶：
 * 你能在线性时间复杂度内解决此题吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sliding-window-maximum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Zain
 * @date 2019/11/8
 */
public class MaxSlidingWindow {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length <= 0) {
            return new int[0];
        }
        int[] ret = new int[nums.length - k + 1];
        int index = 0;
        for (int i = k; i <= nums.length; i++) {
            PriorityQueue<Integer> queue = new PriorityQueue<>(k, (a, b) -> b - a);
            for (int j = i - k; j < i; j++) {
                queue.add(nums[j]);
            }
            ret[index++] = queue.peek();
        }

        return ret;
    }

    public int[] maxSlidingWindowFast(int[] nums, int k) {
        if (nums.length <= 0) {
            return new int[0];
        }
        int[] ret = new int[nums.length - k + 1];
        PriorityQueue<Integer> queue = new PriorityQueue<>(k, (a, b) -> b - a);
        int index = 0;
        for (int i = 0; i < k; i++) {
            queue.add(nums[i]);
        }
        ret[index++] = queue.peek();

        int pre;
        for (int i = k; i < nums.length; i++) {
            pre = nums[i - k];
            queue.remove(pre);
            queue.add(nums[i]);
            ret[index++] = queue.peek();
        }

        return ret;
    }

    public int[] maxSlidingWindowFastAndSimple(int[] nums, int k) {
        if (nums.length <= 0) {
            return new int[0];
        }
        int[] ret = new int[nums.length - k + 1];
        Deque<Integer> window = new ArrayDeque<>(k);
        for (int i = 0; i < nums.length; i++) {
            int firstIndex = i - k + 1;
            int num = nums[i];
            if (i >= k && firstIndex > window.peek()) {
                window.remove();
            }
            while (!window.isEmpty() && nums[window.peekLast()] < num) {
                window.removeLast();
            }
            window.add(i);

            if (firstIndex >= 0) {
                ret[firstIndex] = nums[window.peek()];
            }
        }

        return ret;
    }
}
