package com.aizain.jhome.computer.data.list;

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
 * @date 2020/5/19
 */
public class ValidPalindrome_680 {

    /**
     * 审题：
     * 1. 输入非空
     * 2. a-z小写
     * <p>
     * 思路：
     * 1. 循环删除+字符串取反比较
     * 2. 循环删除+字符串取反双指针比较
     * 3. 双指针比较，跳过一个错误
     *
     * @param s
     * @return
     */
    public boolean validPalindrome(String s) {
        // return recursiveSolution(s);
        // return recursivePointerSolution(s);
        return pointerSkipSolution(s);
    }

    /**
     * 双指针比较，跳过一个错误
     *
     * @param s
     * @return
     */
    private boolean pointerSkipSolution(String s) {
        int skip = 0;
        for (int i = 0, j = s.length() - 1; i <= j; i++, j--) {
            if (skip > 1) {
                break;
            }

            if (s.charAt(i) == s.charAt(j)) {
                continue;
            }

            if (s.charAt(i + 1) == s.charAt(j)) {
                i++;
                skip++;
            } else if (s.charAt(i) == s.charAt(j - 1)) {
                j--;
                skip++;
            } else {
                skip += 2;
            }
        }

        if (skip <= 1) {
            return true;
        }

        skip = 0;
        for (int i = 0, j = s.length() - 1; i <= j; i++, j--) {
            if (skip > 1) {
                break;
            }

            if (s.charAt(i) == s.charAt(j)) {
                continue;
            }

            if (s.charAt(i) == s.charAt(j - 1)) {
                j--;
                skip++;
            } else if (s.charAt(i + 1) == s.charAt(j)) {
                i++;
                skip++;
            } else {
                skip += 2;
            }
        }

        return skip <= 1;
    }

    /**
     * 循环删除+字符串取反双指针比较
     * 超时
     *
     * @param s
     * @return
     */
    private boolean recursivePointerSolution(String s) {
        s = "$" + s + "$";
        for (int i = 1; i < s.length(); i++) {
            String newS = s.substring(0, i) + s.substring(i + 1);
            for (int j = 0, k = newS.length() - 1; j <= k; j++, k--) {
                if (newS.charAt(j) != newS.charAt(k)) {
                    break;
                } else if (k - j <= 1) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 循环删除+字符串取反比较
     * 超时
     *
     * @param s
     * @return
     */
    private boolean recursiveSolution(String s) {
        s = "$" + s + "$";
        for (int i = 1; i < s.length(); i++) {
            String newS = s.substring(0, i) + s.substring(i + 1);
            StringBuilder reverseS = new StringBuilder();
            for (int j = newS.length() - 1; j >= 0; j--) {
                reverseS.append(newS.charAt(j));
            }

            if (newS.equals(reverseS.toString())) {
                return true;
            }
        }

        return false;
    }

}
