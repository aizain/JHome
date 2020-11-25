package com.aizain.jhome.computer.data.dp;

import java.util.Arrays;

/**
 * GetMaxMatrix_M1724
 * 面试题 17.24. 最大子矩阵
 * 困难
 * <p>
 * 给定一个正整数、负整数和 0 组成的 N × M 矩阵，编写代码找出元素总和最大的子矩阵。
 * 返回一个数组 [r1, c1, r2, c2]，其中 r1, c1 分别代表子矩阵左上角的行号和列号，r2, c2 分别代表右下角的行号和列号。若有多个满足条件的子矩阵，返回任意一个均可。
 * <p>
 * 注意：本题相对书上原题稍作改动
 * <p>
 * 示例：
 * 输入：
 * [
 * [-1,0],
 * [0,-1]
 * ]
 * 输出：[0,1,0,1]
 * 解释：输入中标粗的元素即为输出所表示的矩阵
 * <p>
 * 说明：
 * 1 <= matrix.length, matrix[0].length <= 200
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/max-submatrix-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Zain
 * @date 2020/11/16
 */
public class GetMaxMatrix_M1724 {

    /**
     * 审题：
     * 1. 返回左上角、右下角下标
     *
     * @param matrix
     * @return
     */
    public int[] getMaxMatrix(int[][] matrix) {
        // return dp1Solution(matrix);
        return dp1FastSolution(matrix);
    }

    /**
     * 动态规划 + 最大子序和
     * <p>
     * NxM 矩阵可以转化为求 M 次最大子序和
     * 每次列求和，压缩成一维数组，找最大子序列，就是当前最大的矩阵
     *
     * @param matrix
     * @return
     */
    private int[] dp1FastSolution(int[][] matrix) {
        int j = matrix.length;
        int i = matrix[0].length;
        int max = Integer.MIN_VALUE;
        int[] maxMatrix = new int[4];

        // 处理j1子矩阵, [0,h], j1向下移动
        for (int j1 = 0; j1 < j; j1++) {
            // tmp替换掉多一层的循环
            int[] tmp = new int[i];
            Arrays.fill(tmp, 0);
            // j1子矩阵拆分j2各子矩阵
            for (int j2 = j1; j2 < j; j2++) {
                // 计算子矩阵最大值
                int[] dp = new int[i];
                Arrays.fill(dp, 0);
                // 暂时记录左上角li
                int li = 0;
                for (int i1 = 0; i1 < i; i1++) {
                    // 处理每行，计算当前行对应的列dp[i1]，对i1列求和
                    if (j2 > j1) {
                        tmp[i1] = tmp[i1] + matrix[j2][i1];
                    } else {
                        tmp[i1] = matrix[j2][i1];
                    }
                    dp[i1] += tmp[i1];

                    // 计算子序列和
                    if (i1 > 0 && dp[i1 - 1] + dp[i1] > dp[i1]) {
                        dp[i1] = dp[i1 - 1] + dp[i1];
                    } else {
                        // 子序列更新，替换左上角li
                        li = i1;
                    }

                    // 记录最大的左上角、右下角
                    if (dp[i1] > max) {
                        max = dp[i1];
                        maxMatrix[0] = j1;
                        maxMatrix[1] = li;
                        maxMatrix[2] = j2;
                        maxMatrix[3] = i1;
                    }
                }

            }
        }

        return maxMatrix;
    }

    private int[] dp1FastCleanSolution(int[][] matrix) {
        int h = matrix.length;
        int w = matrix[0].length;
        int max = Integer.MIN_VALUE;
        int[] maxMatrix = new int[4];
        for (int h1 = 0; h1 < h; h1++) {
            int[] hdp = new int[w];
            Arrays.fill(hdp, 0);
            for (int h2 = h1; h2 < h; h2++) {
                int[] dp = new int[w];
                int wTmp = 0;
                for (int w1 = 0; w1 < w; w1++) {
                    hdp[w1] += matrix[h2][w1];
                    dp[w1] = hdp[w1];
                    if (w1 > 0) {
                        int dpSum = dp[w1] + dp[w1 - 1];
                        if (dpSum > dp[w1]) {
                            dp[w1] = dpSum;
                        } else {
                            wTmp = w1;
                        }
                    }

                    if (dp[w1] > max) {
                        max = dp[w1];
                        maxMatrix[0] = h1;
                        maxMatrix[1] = wTmp;
                        maxMatrix[2] = h2;
                        maxMatrix[3] = w1;
                    }
                }
            }
        }

        return maxMatrix;
    }

    /**
     * 动态规划 + 最大子序和
     * <p>
     * NxM 矩阵可以转化为求 M 次最大子序和
     * 每次列求和，压缩成一维数组，找最大子序列，就是当前最大的矩阵
     *
     * @param matrix
     * @return
     */
    private int[] dp1Solution(int[][] matrix) {
        int j = matrix.length;
        int i = matrix[0].length;
        int max = Integer.MIN_VALUE;
        int[] maxMatrix = new int[4];

        // 处理j1子矩阵, [0,h], j1向下移动
        for (int j1 = 0; j1 < j; j1++) {
            // j1子矩阵拆分j2各子矩阵
            for (int j2 = j1; j2 < j; j2++) {
                // 计算子矩阵最大值
                int[] dp = new int[i];
                Arrays.fill(dp, 0);
                // 暂时记录左上角li
                int li = 0;
                for (int i1 = 0; i1 < i; i1++) {
                    // 处理每行，计算当前行对应的列dp[i1]，对i1列求和
                    for (int j3 = j1; j3 <= j2; j3++) {
                        dp[i1] += matrix[j3][i1];
                    }

                    // 计算子序列和
                    if (i1 > 0 && dp[i1 - 1] + dp[i1] > dp[i1]) {
                        dp[i1] = dp[i1 - 1] + dp[i1];
                    } else {
                        // 子序列更新，替换左上角li
                        li = i1;
                    }

                    // 记录最大的左上角、右下角
                    if (dp[i1] > max) {
                        max = dp[i1];
                        maxMatrix[0] = j1;
                        maxMatrix[1] = li;
                        maxMatrix[2] = j2;
                        maxMatrix[3] = i1;
                    }
                }

            }
        }

        return maxMatrix;
    }

}
