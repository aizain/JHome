package com.bhenternal.jhome.algorithm.array

/**
 * ValidPalindrome_680
 * 680. 验证回文字符串 Ⅱ
 * <p>
 * 给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。
 * <p>
 * 示例 1:
 * 输入: "aba"
 * 输出: True
 * <p>
 * 示例 2:
 * 输入: "abca"
 * 输出: True
 * 解释: 你可以删除c字符。
 * <p>
 * 注意:
 * 字符串只包含从 a-z 的小写字母。字符串的最大长度是50000。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-palindrome-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Zain
 * @date 2020/5/25
 */
object ValidPalindrome_680 {

  def main(args: Array[String]): Unit = {
    val s = "abca"
    println(s + ": " + pointerCleanSolution(s))
  }

  /**
   *
   * @param s
   * @return
   */
  def pointerCleanSolution(s: String): Boolean = {
    var i = 0
    var j = s.length - 1
    while (i < j) {
      if (s.charAt(i) != s.charAt(j)) {
        return isPalindrome(i + 1, j, s) || isPalindrome(i, j - 1, s)
      }
      i += 1
      j -= 1
    }
    true
  }

  def isPalindrome(i: Int, j: Int, s: String): Boolean = {
    var left = i
    var right = j
    while (left < right) {
      if (s.charAt(left) != s.charAt(right)) {
        return false
      }
      left += 1
      right -= 1
    }
    true
  }

  def validPalindrome(s: String): Boolean = {
    pointerCleanSolution(s)
  }

}
