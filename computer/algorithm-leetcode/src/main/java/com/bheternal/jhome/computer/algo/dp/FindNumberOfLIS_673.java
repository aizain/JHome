package com.bheternal.jhome.computer.algo.dp;

/**
 * FindNumberOfLIS_673
 * 673. 最长递增子序列的个数
 * <p>
 * 给定一个未排序的整数数组，找到最长递增子序列的个数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,3,5,4,7]
 * 输出: 2
 * 解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
 * 示例 2:
 * <p>
 * 输入: [2,2,2,2,2]
 * 输出: 5
 * 解释: 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。
 * 注意: 给定的数组长度不超过 2000 并且结果一定是32位有符号整数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-longest-increasing-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Zain
 * @date 2020/11/6
 */
public class FindNumberOfLIS_673 {

    /**
     * 审题：
     * 1. nums.length <= 2000
     * <p>
     * 思路：
     * 1. 动态规划暴力，每一步都记录之前的的递增序列，并且自己作为单独的序列
     * 2. 动态规划，dp记录最长序列长度，g记录最长的有几组
     *
     * @param nums
     * @return
     */
    public int findNumberOfLIS(int[] nums) {
        return dpSolution(nums);
    }

    /**
     * 动态规划
     * dp记录最长序列长度，g记录最长的有几组
     * <p>
     * dp和上升序列300一样的思路，每次找前面所有的长度的，然后对比+1看看还能更长不，取max记录在i位置
     * g比较有意思，多组说明在上升的过程中有下降，看看下降的位置或者下降后上升的长度+1，是否能等于i位置的长度，如果等于说明有一个新的组。
     * i位置如果是上升的值，就会继承之前上升最长的g
     * <p>
     * 结果是dp中全部最大的g的总和
     *
     * @param nums
     * @return
     */
    private int dpSolution(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return 0;
        }
        if (nums.length <= 1) {
            return 1;
        }
        int[] dp = new int[nums.length];
        int[] g = new int[nums.length];
        int maxDp = 0;

        // 填充1，保证最小是一个
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            g[i] = 1;
        }

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                // dp上升条件：数值上升 && 只记录最长的
                // 同时继承之前的g
                if (nums[j] < nums[i] && (dp[j] + 1) > dp[i]) {
                    dp[i]++;
                    g[i] = g[j];
                }
                // 最长组增加条件：数值上升 && 前面下降过，并且新的上升序列的长度也是最长的
                // 同时继承之前的g
                else if (nums[j] < nums[i] && (dp[j] + 1) == dp[i]) {
                    g[i] += g[j];
                }
            }
            // 记录maxDp
            maxDp = Math.max(maxDp, dp[i]);
        }

        // 结果集
        int mapG = 0;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] == maxDp) {
                mapG += g[i];
            }
        }

        return mapG;
    }


}
