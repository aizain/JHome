package com.aizain.jhome.computer.data.interview;

import java.util.Arrays;
import java.util.List;

/**
 * CoinLcci_08_11
 * 面试题 08.11. 硬币
 * <p>
 * 硬币。给定数量不限的硬币，币值为25分、10分、5分和1分，编写代码计算n分有几种表示法。(结果可能会很大，你需要将结果模上1000000007)
 * <p>
 * 示例1:
 * <p>
 * 输入: n = 5
 * 输出：2
 * 解释: 有两种方式可以凑成总金额:
 * 5=5
 * 5=1+1+1+1+1
 * 示例2:
 * <p>
 * 输入: n = 10
 * 输出：4
 * 解释: 有四种方式可以凑成总金额:
 * 10=10
 * 10=5+5
 * 10=5+1+1+1+1+1
 * 10=1+1+1+1+1+1+1+1+1+1
 * 说明：
 * <p>
 * 注意:
 * <p>
 * 你可以假设：
 * <p>
 * 0 <= n (总金额) <= 1000000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/coin-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Zain
 * @date 2020/4/23
 */
public class CoinLcci_08_11 {

    /**
     * 审题：
     * 1 0 <= n (总金额) <= 1000000
     * 2 将结果模上1000000007
     * <p>
     * 思路：
     * 1 暴力dfs
     *
     * @param n
     * @return
     */
    public int waysToChange(int n) {
        return directlyDfsSolution(n);
    }

    /**
     * 暴力dfs
     *
     * @param n
     * @return
     */
    private int directlyDfsSolution(int n) {
        List<Integer> coins = Arrays.asList(25, 10, 5, 1);
        for (int i = 0; i < coins.size(); i++) {

        }
        return dfsHelper(n, 0);
    }

    private int dfsHelper(int n, int coinIndex) {
        // 1 边界
        if (n == 0) {
            return 1;
        }
        if (n < 0) {
            return 0;
        }
        // 2 当前
        // 3 子问题
        // 4 整理
        int solution = 0;
//        for (int i = coinIndex; i < coins.size(); i++) {
//            int coin = coins.get(i);
//            solution += dfsHelper(n - coin, i);
//        }
        // 5 清理
        return solution;
    }

}
