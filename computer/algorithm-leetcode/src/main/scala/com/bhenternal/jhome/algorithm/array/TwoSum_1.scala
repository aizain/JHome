package com.bhenternal.jhome.algorithm.array

/**
 * TwoSum
 * 1. 两数之和
 * <p>
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 * <p>
 * 示例:
 * 给定 nums = [2, 7, 11, 15], target = 9
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Zain
 * @date 2020/5/7
 */
object TwoSum_1 {

  def twoSum(nums: Array[Int], target: Int): Array[Int] = {
    // directlySolution(nums, target)
    hashSolution(nums, target)
  }

  /**
   * hash表
   *
   * @param nums
   * @param target
   * @return
   */
  def hashSolution(nums: Array[Int], target: Int): Array[Int] = {
    val map = new mutable.LinkedHashMap[Int, Int]
    for (i <- nums.indices) {
      if (map.contains(nums(i))) {
        return Array(map(nums(i)), i)
      }
      map.put(target - nums(i), i)
    }
    Array(0, 0)
  }

  /**
   * 两层循环
   *
   * @param nums
   * @param target
   * @return
   */
  def directlySolution(nums: Array[Int], target: Int): Array[Int] = {
    for (i <- nums.indices; j <- i + 1 until nums.length) {
      if (nums(i) + nums(j) == target) {
        return Array(i, j)
      }
    }
    null
  }

}
