package com.aizain.jhome.computer.data.dp;

/**
 * MaxProduct_152
 * 152. 乘积最大子数组
 * <p>
 * 给你一个整数数组 nums，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），
 * 并返回该子数组所对应的乘积。
 * <p>
 * 示例 1:
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释:子数组 [2,3] 有最大乘积 6。
 * <p>
 * 示例 2:
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释:结果不能为 2, 因为 [-2,-1] 不是子数组。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-product-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Zain
 * @date 2020/11/11
 */
public class MaxProduct_152 {

    public int maxProduct(int[] nums) {
        return dpSolution(nums);
    }

    private int dpSolution(int[] nums) {
        if (nums.length <= 1) {
            return nums[0];
        }

        int max = nums[0];
        int[] maxDp = new int[nums.length];
        int[] minDp = new int[nums.length];
        maxDp[0] = nums[0];
        minDp[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            maxDp[i] = Math.max(
                    Math.max(maxDp[i - 1] * nums[i], nums[i]),
                    Math.max(minDp[i - 1] * nums[i], nums[i])
            );
            minDp[i] = Math.min(
                    Math.min(minDp[i - 1] * nums[i], nums[i]),
                    Math.min(minDp[i - 1] * nums[i], nums[i])
            );
            max = Math.max(maxDp[i], max);
        }

        return max;
    }

}
