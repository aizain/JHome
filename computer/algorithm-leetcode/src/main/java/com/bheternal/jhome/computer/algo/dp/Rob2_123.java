package com.bheternal.jhome.computer.algo.dp;

/**
 * Rob2_123
 * 213. 打家劫舍 II
 * <p>
 * mid
 * <p>
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。
 * 这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。
 * 同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，能够偷窃到的最高金额。
 * <p>
 * <p>
 * 示例1：
 * 输入：nums = [2,3,2]
 * 输出：3
 * 解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 * <p>
 * 示例 2：
 * 输入：nums = [1,2,3,1]
 * 输出：4
 * 解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
 * 偷窃到的最高金额 = 1 + 3 = 4 。
 * <p>
 * 示例 3：
 * 输入：nums = [0]
 * 输出：0
 * <p>
 * 提示：
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 1000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/house-robber-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Zain
 * @date 2020/11/25
 */
public class Rob2_123 {

    /**
     * 审题：
     * 1. 1 <= nums.length <= 100，0 <= nums[i] <= 1000
     * 2. 间隔最小是 1
     * 3. 收尾相连
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        return dpSolution(nums);
    }

    private int dpSolution(int[] nums) {
        if (nums.length <= 1) {
            return nums[0];
        }

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(dp[0], nums[1]);
        for (int i = 2; i < nums.length - 1; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }

        int[] dpR = new int[nums.length];
        dpR[nums.length - 1] = nums[nums.length - 1];
        dpR[nums.length - 2] = Math.max(dpR[nums.length - 1], nums[nums.length - 2]);
        for (int i = nums.length - 3; i > 0; i--) {
            dpR[i] = Math.max(dpR[i + 2] + nums[i], dpR[i + 1]);
        }

        return Math.max(dp[nums.length - 2], dpR[1]);
    }

}
