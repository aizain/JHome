package com.bheternal.jhome.computer.algo.list;

/**
 * RemoveDuplicates
 * <p>
 * 26. 删除排序数组中的重复项
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 * <p>
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 * <p>
 * 示例 1:
 * <p>
 * 给定数组 nums = [1,1,2],
 * <p>
 * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
 * <p>
 * 你不需要考虑数组中超出新长度后面的元素。
 * 示例 2:
 * <p>
 * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
 * <p>
 * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
 * <p>
 * 你不需要考虑数组中超出新长度后面的元素。
 * 说明:
 * <p>
 * 为什么返回数值是整数，但输出的答案是数组呢?
 * <p>
 * 请注意，输入数组是以“引用”方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 * <p>
 * 你可以想象内部操作如下:
 * <p>
 * // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
 * int len = removeDuplicates(nums);
 * <p>
 * // 在函数里修改输入数组对于调用者是可见的。
 * // 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
 * for (int i = 0; i < len; i++) {
 *     print(nums[i]);
 * }
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Zain
 * @date 2020/2/16
 */
public class RemoveDuplicates {

    /**
     * 第一步：审题
     * 1、数组是有序的
     * 2、满足O(1)空间复杂度
     * 3、删除输入数组的重复元素
     * 4、去重后，可以不处理新长度的后续元素
     * <p>
     * 第二步：列解题方式
     * 1、正序遍历，用一个字段记录当前数字，如果不重复替换，重复删除元素
     * 补：2、基于1，记录当前字段使用数组前一个不重复元素
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return 0;
        }
        if (nums.length == 1) {
            return 1;
        }
        int maxSize = nums.length;
        int index = 0;
        int point = nums[index];

        index++;
        while (index < maxSize) {
            int num = nums[index];
            if (num != point) {
                point = num;
                index++;
            } else {
                deleteNum(nums, index, maxSize);
                maxSize--;
            }
        }

        return maxSize;
    }

    private void deleteNum(int[] nums, int index, int maxSize) {
        int delIndex = index;
        while (delIndex < maxSize - 1) {
            nums[delIndex] = nums[delIndex + 1];
            delIndex++;
        }
    }

}
