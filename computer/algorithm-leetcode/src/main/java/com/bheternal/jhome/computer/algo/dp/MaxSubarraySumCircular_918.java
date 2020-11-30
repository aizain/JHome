package com.bheternal.jhome.computer.algo.dp;

/**
 * MaxSubarraySumCircular_918
 * 918. 环形子数组的最大和
 * <p>
 * 给定一个由整数数组 A 表示的环形数组 C，求 C 的非空子数组的最大可能和。
 * <p>
 * 在此处，环形数组意味着数组的末端将会与开头相连呈环状。（形式上，当0 <= i < A.length时C[i] = A[i]，且当 i >= 0 时 C[i+A.length] = C[i]）
 * 此外，子数组最多只能包含固定缓冲区 A 中的每个元素一次。（形式上，对于子数组C[i], C[i+1], ..., C[j]，不存在 i <= k1, k2 <= j 其中 k1 % A.length = k2 % A.length）
 * <p>
 * <p>
 * 示例 1：
 * 输入：[1,-2,3,-2]
 * 输出：3
 * 解释：从子数组 [3] 得到最大和 3
 * <p>
 * 示例 2：
 * 输入：[5,-3,5]
 * 输出：10
 * 解释：从子数组 [5,5] 得到最大和 5 + 5 = 10
 * <p>
 * 示例 3：
 * 输入：[3,-1,2,-1]
 * 输出：4
 * 解释：从子数组 [2,-1,3] 得到最大和 2 + (-1) + 3 = 4
 * <p>
 * 示例 4：
 * 输入：[3,-2,2,-3]
 * 输出：3
 * 解释：从子数组 [3] 和 [3,-2,2] 都可以得到最大和 3
 * <p>
 * 示例 5：
 * 输入：[-2,-3,-1]
 * 输出：-1
 * 解释：从子数组 [-1] 得到最大和 -1
 * <p>
 * 提示：
 * -30000 <= A[i] <= 30000
 * 1 <= A.length <= 30000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-sum-circular-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Zain
 * @date 2020/11/13
 */
public class MaxSubarraySumCircular_918 {

    /**
     * 审题：
     * 1. 子数组是连续的，注意有环形
     *
     * @param A
     * @return
     */
    public int maxSubarraySumCircular(int[] A) {
        return dpAdjacentSolution(A);
    }

    /**
     * 动态规划 + 邻接数组
     * <p>
     * <p>
     * 实验1：
     * A     5, -1,  1,  2, -1,  5
     * dp    5,  5,  6,  8,  8, 13
     * max   5,  5,  6,  8,  8, 13
     * <p>
     * dpR  11,  6,  7,  6,  4,  5
     * dpRM 11,  7,  7,  6,  5,  5
     * l     5,  4,  5,  7,  6, 11
     * max  12, 12, 12, 12
     * (max[i] = dpL[i] + dpRM[i+2])
     * <p>
     * <p>
     * 实验2：
     * A     5, -3,  2
     * dp    5,  2,  4
     * max   5,  5,  5
     * <p>
     * dpR   4, -1,  2
     * dpRM  4,  2,  2
     * l     5,  2,  4
     * max   7
     * <p>
     * 好神奇，咋就可以了呢？
     *
     * @param a
     * @return
     */
    private int dpAdjacentSolution(int[] a) {
        if (a.length <= 1) {
            return a[0];
        }

        int[] dp = new int[a.length];
        int max = a[0];
        dp[0] = a[0];

        // dp[j+1]=A[j+1]+max(dp[j],0)
        for (int i = 1; i < a.length; i++) {
            dp[i] = a[i] + Math.max(dp[i - 1], 0);
            max = Math.max(max, dp[i]);
        }

        int[] dpR = new int[a.length];
        int[] dpRm = new int[a.length];

        dpR[a.length - 1] = a[a.length - 1];
        for (int i = a.length - 2; i >= 0; i--) {
            dpR[i] = dpR[i + 1] + a[i];
        }

        dpRm[a.length - 1] = a[a.length - 1];
        for (int i = a.length - 2; i >= 0; i--) {
            dpRm[i] = Math.max(dpRm[i + 1], dpR[i]);
        }

        int l = 0;
        for (int i = 0; i < a.length - 2; i++) {
            l += a[i];
            max = Math.max(max, l + dpRm[i + 2]);
        }


        return max;
    }

}
