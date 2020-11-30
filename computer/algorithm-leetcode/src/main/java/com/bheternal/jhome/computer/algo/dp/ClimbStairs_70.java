package com.bheternal.jhome.computer.algo.dp;

import com.aizain.jhome.computer.data.list.ClimbStairs;

/**
 * ClimbStairs_70
 * 70. 爬楼梯
 * <p>
 * 假设你正在爬楼梯。需要 n阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 注意：给定 n 是一个正整数。
 * <p>
 * 示例 1：
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * <p>
 * 示例 2：
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/climbing-stairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Zain
 * @date 2020/6/4
 * @see ClimbStairs
 */
public class ClimbStairs_70 {

    /**
     * 审题：
     * 1 n 是一个正整数 >= 1
     * 2 返回的是方法数量
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        // return dpSolution(n);
        return dpPointSolution(n);
    }

    /**
     * 动态规划
     * 压缩版
     *
     * @param n
     * @return
     */
    private int dpPointSolution(int n) {
        if (n <= 1) {
            return 1;
        }
        int first = 1;
        int second = 2;
        int result = second;
        for (int i = 2; i < n; i++) {
            result = first + second;
            first = second;
            second = result;
        }
        return result;
    }

    /**
     * 动态规划
     *
     * @param n
     * @return
     */
    private int dpSolution(int n) {
        if (n <= 1) {
            return 1;
        }
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i < n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n - 1];
    }

}
