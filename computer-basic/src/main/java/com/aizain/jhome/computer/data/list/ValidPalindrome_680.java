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
        // return traverseSolution(s);
        // return traversePointerSolution(s);
        // return pointerSkipSolution(s);
        // return divideSolution(s);
        return pointerCleanSolution(s);
    }

    /**
     * 双指针简洁版
     *
     * @param s
     * @return
     */
    private boolean pointerCleanSolution(String s) {
        for (int left = 0, right = s.length() - 1; left < right; left++, right--) {
            if (s.charAt(left) != s.charAt(right)) {
                return isPalindrome(left + 1, right, s) || isPalindrome(left, right - 1, s);
            }
        }
        return true;
    }

    private boolean isPalindrome(int left, int right, String s) {
        for (; left < right; left++, right--) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 使用递归分治思想
     *
     * @param s
     * @return
     */
    private boolean divideSolution(String s) {
        return divideHelper(0, s.length() - 1, 0, s) <= 1;
    }

    private int divideHelper(int first, int end, int del, String s) {
        // 1 终结
        if (first >= end || del > 1) {
            return del;
        }

        // 2 本层
        char firstS = s.charAt(first);
        char endS = s.charAt(end);
        // 3 下探

        // 相等无需处理
        if (firstS == endS) {
            return divideHelper(first + 1, end - 1, del, s);
        }
        // 删除超过1个不用往下走了
        if (del >= 1) {
            return del + 1;
        }
        // 左边-1寻找
        int firstDel = divideHelper(first + 1, end, del + 1, s);
        if (firstDel <= 1) {
            return firstDel;
        }

        // 右边-1寻找
        return divideHelper(first, end - 1, del + 1, s);
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
    private boolean traversePointerSolution(String s) {
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
    private boolean traverseSolution(String s) {
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
