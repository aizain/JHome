package com.bheternal.jhome.computer.algo.daily;

import java.util.Arrays;

/**
 * MaximumProduct_628
 * 628. 三个数的最大乘积
 *
 * 给定一个整型数组，在数组中找出由三个数组成的最大乘积，并输出这个乘积。
 *
 * 示例 1:
 *
 * 输入: [1,2,3]
 * 输出: 6
 *
 *
 * 示例 2:
 *
 * 输入: [1,2,3,4]
 * 输出: 24
 * 注意:
 *
 * 给定的整型数组长度范围是[3,104]，数组中所有的元素范围是[-1000, 1000]。
 * 输入的数组中任意三个数的乘积不会超出32位有符号整数的范围。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-product-of-three-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Zain
 * @date 2021/1/20
 */
public class MaximumProduct_628 {


    /**
     * 审题：
     * 1 都是整数，有负数
     *
     * 思路：
     * 1 排序，取后三个/前两加后一个，里面最大的
     * 2 暴力三层循环
     *
     *
     * @param nums
     * @return
     */
    public int maximumProduct(int[] nums) {
        return sortedSolution(nums);
        // return iterSolution(nums);
    }


    /**
     * 排序，取后三个/前两加后一个，里面最大的
     *
     * @param nums
     * @return
     */
    private int sortedSolution(int[] nums) {

        int len = nums.length;
        Arrays.sort(nums);

        return Math.max(nums[len - 1] * nums[len - 2] * nums[len - 3], nums[len - 1] * nums[0] * nums[1]);
    }


    /**
     * 三层循环，会超时
     *
     * @param nums
     * @return
     */
    private int iterSolution(int[] nums) {
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                for (int k = j+1; k < nums.length; k++) {
                    max = Math.max(max, nums[i] * nums[j] * nums[k]);
                }
            }
        }

        return max;
    }

}
