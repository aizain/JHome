package com.bheternal.jhome.computer.algo.dp;

import java.util.Arrays;

/**
 * MaxSumSubmatrix_363
 * 363. 矩形区域不超过 K 的最大数值和
 * hard
 * <p>
 * 给定一个非空二维矩阵 matrix 和一个整数 k，找到这个矩阵内部不大于 k 的最大矩形和。
 * <p>
 * 示例:
 * 输入: matrix = [[1,0,1],[0,-2,3]], k = 2
 * 输出: 2
 * 解释: 矩形区域 [[0, 1], [-2, 3]] 的数值和是 2，且 2 是不超过 k 的最大数字（k = 2）。
 * <p>
 * 说明：
 * 矩阵内的矩形区域面积必须大于 0。
 * 如果行数远大于列数，你将如何解答呢？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/max-sum-of-rectangle-no-larger-than-k
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Zain
 * @date 2020/11/19
 */
public class MaxSumSubmatrix_363 {

    /**
     * 审题：
     * 1. 不超过 k，max <= k，这个很关键
     *
     * @param matrix
     * @param k
     * @return
     */
    public int maxSumSubmatrix(int[][] matrix, int k) {
        return dpSolution(matrix, k);
    }

    /**
     * 动态规划
     *
     * @param matrix
     * @param k
     * @return
     */
    private int dpSolution(int[][] matrix, int k) {
        int h = matrix.length;
        int w = matrix[0].length;
        int max = Integer.MIN_VALUE;

        for (int h1 = 0; h1 < h; h1++) {
            int[] dp = new int[w];
            Arrays.fill(dp, 0);
            for (int h2 = h1; h2 < h; h2++) {
                for (int w1 = 0; w1 < w; w1++) {
                    dp[w1] += matrix[h2][w1];
                }
                max = Math.max(max, dpmax(dp, k));
            }
        }

        return max;
    }

    /**
     * 不超过 k 的子序列和最大值
     * 1 暴力
     *
     * @param dp
     * @param k
     * @return
     */
    private int dpmax(int[] dp, int k) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < dp.length; i++) {
            int sum = 0;
            for (int j = i; j < dp.length; j++) {
                sum += dp[j];
                if (sum <= k && sum > max) {
                    max = sum;
                }
            }
        }
        return max;
    }


}
