package com.bheternal.jhome.computer.algo.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * ThreeSum
 * 15. 三数之和
 * <p>
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * <p>
 * 满足要求的三元组集合为：
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题：
 * 1 三层循环 O(n^3)
 * 2 两层循环，去set查询两数之差 O(n^2)
 * 3 排序找：数组排序，一层循环，两边夹找 O(n^2) 空间复杂度常数，缺点就是改了输入内容
 *
 * @author Zain
 * @date 2019/11/8
 */
public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        int target = 0;
        Map<Integer, List<List<Integer>>> map = new HashMap<>(nums.length);
        List<List<Integer>> ret = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            int first = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                int second = nums[j];
                int key = first + second;
                List<List<Integer>> arr = map.get(key);
                if (arr == null) {
                    arr = new LinkedList<>();
                }

                List<Integer> group = new ArrayList<>(3);
                group.add(first);
                group.add(second);
                arr.add(group);
                map.put(key, arr);
            }
        }

        for (int third : nums) {
            List<List<Integer>> arr = map.get(target - third);
            if (arr != null && arr.size() > 0 && arr.size() <= 2) {
                arr.forEach(v -> v.add(third));
                ret.addAll(arr);
            }

        }

        return ret;
    }

}
