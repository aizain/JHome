package com.bheternal.jhome.computer.algo.list;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * NumPairsDivisibleBy60_1010
 * 1010. 总持续时间可被 60 整除的歌曲
 * <p>
 * 在歌曲列表中，第 i 首歌曲的持续时间为 time[i] 秒。
 * 返回其总持续时间（以秒为单位）可被 60 整除的歌曲对的数量。形式上，我们希望索引的数字 i 和 j 满足  i < j 且有 (time[i] + time[j]) % 60 == 0。
 * <p>
 * 示例 1：
 * 输入：[30,20,150,100,40]
 * 输出：3
 * 解释：这三对的总持续时间可被 60 整数：
 * (time[0] = 30, time[2] = 150): 总持续时间 180
 * (time[1] = 20, time[3] = 100): 总持续时间 120
 * (time[1] = 20, time[4] = 40): 总持续时间 60
 * <p>
 * 示例 2：
 * 输入：[60,60,60]
 * 输出：3
 * 解释：所有三对的总持续时间都是 120，可以被 60 整数。
 * <p>
 * 提示：
 * 1 <= time.length <= 60000
 * 1 <= time[i] <= 500
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/pairs-of-songs-with-total-durations-divisible-by-60
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Zain
 * @date 2020/5/8
 */
public class NumPairsDivisibleBy60_1010 {

    /**
     * 审题：
     * 1 time不为空
     * 2 只需要返回数量
     * <p>
     * 思路：
     * 1 两层循环
     * 2 hash
     * <p>
     * 反馈：
     * 1 余数是关键
     * 2 time值 小于 500
     *
     * @param time
     * @return
     */
    public int numPairsDivisibleBy60(int[] time) {
        // return directlySolution(time);
        // return hashSolution(time);
        // return miniHashSolution(time);
        return smartMiniHashSolution(time);
    }

    /**
     * 解决余数为0的逻辑
     *
     * @param time
     * @return
     */
    private int smartMiniHashSolution(int[] time) {
        int cp = 0;
        int[] map = new int[60];
        for (int value : time) {
            int remainder = value % 60;
            cp += map[remainder];

            int target = (600 - value) % 60;
            map[target]++;
        }
        return cp;
    }

    /**
     * hash存储余数，压缩hash空间
     *
     * @param time
     * @return
     */
    private int miniHashSolution(int[] time) {
        int cp = 0;
        int[] map = new int[60];
        for (int value : time) {
            int remainder = value % 60;
            cp += map[remainder];

            int target = remainder == 0 ? 0 : 60 - remainder;
            map[target]++;
        }
        return cp;
    }

    /**
     * hash存储余数
     * 使用余数，提高缓存命中率
     *
     * @param time
     * @return
     */
    private int hashSolution(int[] time) {
        int cp = 0;
        Map<Integer, Integer> map = new LinkedHashMap<>();
        for (int value : time) {
            int remainder = value % 60;
            if (map.containsKey(remainder)) {
                // 迭代累计方式
                // 找到第1个，后面再配对就是1种
                // 找到第2个，后面再配对就是2种
                cp += map.get(remainder);
            }
            if (remainder == 0) {
                // 特殊处理：余数为0的配对也是余数为0的
                map.put(0, map.getOrDefault(0, 0) + 1);
            } else {
                // 记录当前余数的配对余数
                int target = 60 - remainder;
                map.put(target, map.getOrDefault(target, 0) + 1);
            }
        }
        return cp;
    }

    /**
     * 两层循环，会超时
     *
     * @param time
     * @return
     */
    private int directlySolution(int[] time) {
        int cp = 0;
        for (int i = 0; i < time.length; i++) {
            for (int j = i + 1; j < time.length; j++) {
                if ((time[i] + time[j]) % 60 == 0) {
                    cp++;
                }
            }
        }
        return cp;
    }

}
