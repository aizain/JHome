package com.aizain.jhome.computer.data.list;

import java.util.Arrays;

/**
 * ShipWithinDays_1011
 * 1011. 在 D 天内送达包裹的能力
 * <p>
 * 传送带上的包裹必须在 D 天内从一个港口运送到另一个港口。
 * 传送带上的第 i 个包裹的重量为 weights[i]。每一天，我们都会按给出重量的顺序往传送带上装载包裹。我们装载的重量不会超过船的最大运载重量。
 * 返回能在 D 天内将传送带上的所有包裹送达的船的最低运载能力。
 * <p>
 * 示例 1：
 * <p>
 * 输入：weights = [1,2,3,4,5,6,7,8,9,10], D = 5
 * 输出：15
 * 解释：
 * 船舶最低载重 15 就能够在 5 天内送达所有包裹，如下所示：
 * 第 1 天：1, 2, 3, 4, 5
 * 第 2 天：6, 7
 * 第 3 天：8
 * 第 4 天：9
 * 第 5 天：10
 * <p>
 * 请注意，货物必须按照给定的顺序装运，因此使用载重能力为 14 的船舶并将包装分成 (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) 是不允许的。
 * <p>
 * 示例 2：
 * <p>
 * 输入：weights = [3,2,2,4,1,4], D = 3
 * 输出：6
 * 解释：
 * 船舶最低载重 6 就能够在 3 天内送达所有包裹，如下所示：
 * 第 1 天：3, 2
 * 第 2 天：2, 4
 * 第 3 天：1, 4
 * 示例 3：
 * <p>
 * 输入：weights = [1,2,3,1,1], D = 4
 * 输出：3
 * 解释：
 * 第 1 天：1
 * 第 2 天：2
 * 第 3 天：3
 * 第 4 天：1, 1
 *  
 * <p>
 * 提示：
 * 1 <= D <= weights.length <= 50000
 * 1 <= weights[i] <= 500
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/capacity-to-ship-packages-within-d-days
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Zain
 * @date 2020/5/25
 */
public class ShipWithinDays_1011 {

    public static void main(String[] args) {
        ShipWithinDays_1011 topic = new ShipWithinDays_1011();
        System.out.println("shipWithinDays: " + topic.shipWithinDays(
                new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 5
        ));
    }

    /**
     * 审题：
     * 1 顺序承载
     * <p>
     * 思路：
     * 1 从载重x开始循环，x为数组最大的
     * 2 倒序循环
     * <p>
     * 反馈：
     * 1 二分查询来优化x的逐步递增
     *
     * @param weights
     * @param D
     * @return
     */
    public int shipWithinDays(int[] weights, int D) {
        // return traverseSolution(weights, D);
        // return traverseReserveSolution(weights, D);
        // return binaryTraverseSolution(weights, D);
        return binaryTraverseFastSolution(weights, D);
    }

    /**
     * 二分查找快速
     *
     * @param weights
     * @param d
     * @return
     */
    private int binaryTraverseFastSolution(int[] weights, int d) {
        int leftLoad = 0;
        int rightLoad = 0;
        for (int weight : weights) {
            leftLoad = Math.max(weight, leftLoad);
            rightLoad += weight;
        }

        while (leftLoad < rightLoad) {
            int midLoad = leftLoad + (rightLoad - leftLoad) / 2;
            if (greaterThenD(midLoad, weights, d)) {
                leftLoad = midLoad + 1;
            } else {
                rightLoad = midLoad;
            }
        }
        return leftLoad;
    }

    /**
     * 二分查询来优化x的逐步递增
     *
     * @param weights
     * @param d
     * @return
     */
    private int binaryTraverseSolution(int[] weights, int d) {
        int leftLoad = findMaxWeight(weights);
        int rightLoad = 500 * 5000;

        while (leftLoad < rightLoad) {
            int midLoad = leftLoad + (rightLoad - leftLoad) / 2;
            if (greaterThenD(midLoad, weights, d)) {
                leftLoad = midLoad + 1;
            } else {
                rightLoad = midLoad;
            }
        }
        return leftLoad;
    }

    /**
     * 倒序循环
     *
     * @param weights
     * @param d
     * @return
     */
    private int traverseReserveSolution(int[] weights, int d) {
        int maxLoad = findMaxWeight(weights);
        while (reserveGreaterThenD(maxLoad, weights, d)) {
            maxLoad++;
        }
        return maxLoad;
    }

    private boolean reserveGreaterThenD(int maxLoad, int[] weights, int d) {
        int curLoad = maxLoad;
        for (int i = weights.length - 1; i >= 0; i--) {
            int weight = weights[i];
            if (d <= 0) {
                return true;
            }

            curLoad -= weight;
            while (curLoad < 0) {
                curLoad = maxLoad - weight;
                d--;
            }
        }
        return d <= 0;
    }

    /**
     * 从载重x开始循环，x为数组最大的
     *
     * @param weights
     * @param d
     * @return
     */
    private int traverseSolution(int[] weights, int d) {
        int maxLoad = findMaxWeight(weights);
        while (greaterThenD(maxLoad, weights, d)) {
            maxLoad++;
        }
        return maxLoad;
    }

    private boolean greaterThenD(int maxLoad, int[] weights, int d) {
        int curLoad = maxLoad;
        for (int weight : weights) {
            if (d <= 0) {
                return true;
            }

            curLoad -= weight;
            while (curLoad < 0) {
                curLoad = maxLoad - weight;
                d--;
            }
        }
        return d <= 0;
    }

    private int findMaxWeight(int[] weights) {
        return Arrays.stream(weights).max().orElse(1);
    }

}
