package com.bheternal.jhome.computer.algo.dp;

/**
 * NumArray_303
 * 303. 区域和检索 - 数组不可变
 * easy
 * <p>
 * 给定一个整数数组 nums，求出数组从索引 i 到 j（i ≤ j）范围内元素的总和，包含 i、j 两点。
 * <p>
 * 实现 NumArray 类：
 * NumArray(int[] nums) 使用数组 nums 初始化对象
 * int sumRange(int i, int j) 返回数组 nums 从索引 i 到 j（i ≤ j）范围内元素的总和，包含 i、j 两点（也就是 sum(nums[i], nums[i + 1], ... , nums[j])）
 * <p>
 * 示例：
 * 输入：
 * ["NumArray", "sumRange", "sumRange", "sumRange"]
 * [[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
 * 输出：
 * [null, 1, -1, -3]
 * <p>
 * 解释：
 * NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
 * numArray.sumRange(0, 2); // return 1 ((-2) + 0 + 3)
 * numArray.sumRange(2, 5); // return -1 (3 + (-5) + 2 + (-1))
 * numArray.sumRange(0, 5); // return -3 ((-2) + 0 + 3 + (-5) + 2 + (-1))
 * <p>
 * 提示：
 * <p>
 * 0 <= nums.length <= 104
 * -105 <= nums[i] <= 105
 * 0 <= i <= j < nums.length
 * 最多调用 104 次 sumRange 方法
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/range-sum-query-immutable
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Zain
 * @date 2020/11/30
 */
public class NumArray_303 {

    private final int[] dp;

    /**
     * 审题：
     * 1. nums长度可能为0
     *
     * @param nums
     */
    public NumArray_303(int[] nums) {
        dp = dpSolution(nums);
    }

    public int sumRange(int i, int j) {
        if (dp.length <= 0) {
            return 0;
        }
        return i == 0 ? dp[j] : dp[j] - dp[i - 1];
    }

    /**
     * 0,  1,  2,  3,  4,  5
     * -2,  0,  3, -5,  2, -1
     * -2, -2,  1, -4, -2, -3
     *
     * @param nums
     * @return
     */
    private int[] dpSolution(int[] nums) {
        if (nums.length <= 0) {
            return new int[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = dp[i - 1] + nums[i];
        }
        return dp;
    }


}
