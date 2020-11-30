package com.bheternal.jhome.computer.algo.dp;

import java.util.Arrays;

/**
 * MaxEnvelopes_354
 * 354. 俄罗斯套娃信封问题
 * <p>
 * 给定一些标记了宽度和高度的信封，宽度和高度以整数对形式(w, h)出现。当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
 * <p>
 * 请计算最多能有多少个信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
 * <p>
 * 说明:
 * 不允许旋转信封。
 * <p>
 * 示例:
 * <p>
 * 输入: envelopes = [[5,4],[6,4],[6,7],[2,3]]
 * 输出: 3
 * 解释: 最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/russian-doll-envelopes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Zain
 * @date 2020/11/10
 */
public class MaxEnvelopes_354 {

    /**
     * 审题：
     * 1. w,h 必须都大于才能套娃
     * 2. 只输出最长的长度即可
     * <p>
     * 思路：
     * 1. 排序后动态规划，LIS
     *
     * @param envelopes
     * @return
     */
    public int maxEnvelopes(int[][] envelopes) {
        return dpSolution(envelopes);
    }

    /**
     * 动态规划 LIS
     * <p>
     * 1 先对数据排序, w ASC，w相同 h DESC, 选取 h 作为 LIS 计算字段
     * 2 计算 LIS 最长长度
     *
     * @param envelopes
     * @return
     */
    private int dpSolution(int[][] envelopes) {
        if (null == envelopes || envelopes.length <= 0) {
            return 0;
        }
        if (envelopes.length <= 1) {
            return envelopes.length;
        }
        int[] nums = sortAndTake(envelopes);
        int[] dp = new int[nums.length];
        int maxL = 1;

        Arrays.fill(dp, 1);
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxL = Math.max(maxL, dp[i]);
        }

        return maxL;
    }

    private int[] sortAndTake(int[][] envelopes) {
        Arrays.sort(envelopes, (int[] a, int[] b) -> {
            if (a[0] == b[0]) {
                // w相同h DESC
                return b[1] - a[1];
            } else {
                return a[0] - b[0];
            }
        });

        int[] nums = new int[envelopes.length];
        for (int i = 0; i < envelopes.length; i++) {
            nums[i] = envelopes[i][1];
        }
        return nums;
    }

}
