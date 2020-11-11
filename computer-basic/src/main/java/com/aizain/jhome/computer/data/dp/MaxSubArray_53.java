package com.aizain.jhome.computer.data.dp;

/**
 * MaxSubArray_53
 * 53. 最大子序和
 * <p>
 * 给定一个整数数组 nums，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 示例:
 * <p>
 * 输入: [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释:连续子数组[4,-1,2,1] 的和最大，为6。
 * 进阶:
 * <p>
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Zain
 * @date 2020/11/11
 */
public class MaxSubArray_53 {

    /**
     * 审题：
     * 1. 只返回最大的和
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        // return dpSolution(nums);
        return simpleSolution(nums);
    }

    private int simpleSolution(int[] nums) {
        if (nums.length <= 1) {
            return nums[0];
        }
        int max = nums[0];
        int preSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            preSum = Math.max(nums[i], preSum + nums[i]);
            max = Math.max(max, preSum);
        }

        return max;
    }

    private int dpSolution(int[] nums) {
        if (nums.length <= 1) {
            return nums[0];
        }

        int[] dp = new int[nums.length];
        int max = nums[0];
        dp[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
            max = Math.max(max, dp[i]);
        }

        return max;
    }


}
