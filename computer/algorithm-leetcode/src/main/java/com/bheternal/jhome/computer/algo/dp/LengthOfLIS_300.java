package com.bheternal.jhome.computer.algo.dp;

import java.util.Arrays;

/**
 * LengthOfLIS_300
 * 300. 最长上升子序列
 * <p>
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 * <p>
 * 示例:
 * <p>
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是[2,3,7,101]，它的长度是 4。
 * 说明:
 * <p>
 * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 * 你算法的时间复杂度应该为O(n2) 。
 * 进阶: 你能将算法的时间复杂度降低到O(n log n) 吗?
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Zain
 * @date 2020/11/5
 */
public class LengthOfLIS_300 {

    /**
     * 审题：
     * 1. 只返回最长的长度
     * 2. 最长可能有多个
     * <p>
     * 思路：
     * 1. 暴力，想不到
     * 2. 动态规划，无思路看题解
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        return dpSolution(nums);
    }

    /**
     * 动态规划
     * [10] => [10]
     * [10,9] => [10],[9]
     * [10,9,2] => [10],[9],[2]
     * [10,9,2,5] => [10],[9],[2,5]
     * [10,9,2,5,3] => [10],[9],[2,5],[2,3]
     * [10,9,2,5,3,7] => [10],[9],[2,5],[2,3,7]
     * [10,9,2,5,3,7,1] => [10],[9],[2,5],[2,3,7],[1]
     * [10,9,2,5,3,7,1,2] => [10],[9],[2,5],[2,3,7],[1,2]
     * [10,9,2,5,3,7,1,2,3] => [10],[9],[2,5],[2,3,7],[1,2,3]
     * [10,9,2,5,3,7,1,2,3,4] => [10],[9],[2,5],[2,3,7],[1,2,3,4]
     *
     * @param nums
     * @return
     */
    private int dpSolution(int[] nums) {
        if (nums == null) {
            return 0;
        }
        if (nums.length <= 1) {
            return nums.length;
        }

        int l = 0;
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            l = Math.max(l, dp[i]);
        }

        return l;
    }

}
