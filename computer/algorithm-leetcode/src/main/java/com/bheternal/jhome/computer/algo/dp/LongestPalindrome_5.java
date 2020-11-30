package com.bheternal.jhome.computer.algo.dp;

/**
 * LongestPalindrome_5
 * 5. 最长回文子串
 * <p>
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * <p>
 * 示例 1：
 * <p>
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * <p>
 * 示例 2：
 * <p>
 * 输入: "cbbd"
 * 输出: "bb"
 * <p>
 * <p>
 * https://leetcode-cn.com/problems/longest-palindromic-substring/
 *
 * @author Zain
 * @date 2020/10/17
 */
public class LongestPalindrome_5 {

    /**
     * 审题：
     * 1 s.length <= 1000
     * 2 回文：aba bb bab
     * 3 length <= 1 返回 s
     * <p>
     * 思路：
     * 1 从第一位开始遍历，保存中间回文，最后取最长的
     * 2 动态规划，根据i,j整理规划的矩阵
     * 3 无思路看题解
     *
     * @param s
     */
    public String longestPalindrome(String s) {
        // return firstSolution(s);
        return dpSolution(s);
    }

    /**
     * 动态规划解法
     * <p>
     * 1. 初始状态，i <= j
     * i
     * j ? - - -
     * ? ? - -
     * ? ? ? -
     * ? ? ? ?
     * <p>
     * <p>
     * 2. 长度为0时，都为1
     * i
     * j 1 - - -
     * ? 1 - -
     * ? ? 1 -
     * ? ? ? 1
     * <p>
     * <p>
     * 3. 长度为1时，看看左右两边是否相等，P(i, i+1)=(Si == Sj)
     * i
     * j 1 - - -
     * 1 1 - -
     * ? 0 1 -
     * ? ? 1 1
     * <p>
     * 4. 长度>1时，P(i, j)=P(i+1, j−1)∧(Si == Sj)
     * i
     * j 1 - - -
     * 1 1 - -
     * 1 0 1 -
     * 0 1 1 1
     * <p>
     * <p>
     * 思路：
     * 1. 先刷完两个对角线铺底，然后循环按行刷，能保证取到之前的值（右上角）
     * <p>
     * 目前来看，这个二维数组只用到一半资源
     *
     * @param s
     * @return
     */
    private String dpSolution(String s) {
        // 参数边界
        if (s == null || s.length() <= 1) {
            return s;
        }
        int minL = 2;
        if (s.length() == minL) {
            return s.charAt(0) == s.charAt(1) ? s : s.substring(0, 1);
        }

        // 初始化
        int l = s.length();
        int ml = 0;
        int mi = 0;
        boolean[][] dp = new boolean[l][l];

        // 1. 先刷第一个对角线
        for (int i = 0; i < l; i++) {
            dp[i][i] = true;
        }

        // 2. 刷第二个对角线
        for (int i = 0; i < l - 1; i++) {
            int j = i + 1;
            dp[i][j] = s.charAt(i) == s.charAt(j);
            // 比较回文子串长度
            if (dp[i][j] && (j - i) > ml) {
                mi = i;
                ml = j - i;
            }
        }

        // 3. 开始循按行刷(j)，每行刷到第二个对角线就停(i<j-1)
        for (int j = 2; j < l; j++) {
            for (int i = 0; i < j - 1; i++) {
                // 赋值 P(i, j)=P(i+1, j−1)∧(Si == Sj)
                dp[i][j] = dp[i + 1][j - 1] && (s.charAt(i) == s.charAt(j));
                // 比较回文子串长度
                if (dp[i][j] && (j - i) > ml) {
                    mi = i;
                    ml = j - i;
                }
            }
        }

        return s.substring(mi, mi + ml + 1);
    }


    /**
     * 暴力解法
     *
     * @param s 字符串
     * @return
     */
    public String firstSolution(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }

        String result = s.substring(0, 1);

        // 从头开始找回文
        for (int i = 0; i < s.length() - 1; i++) {
            // 尝试检查每一组
            for (int j = i + 1; j < s.length(); j++) {
                // 当前组长度是否大于以及找到的，并且还是回文
                if (j + 1 - i > result.length() && validPalindrome(s, i, j)) {
                    result = s.substring(i, j + 1);
                }
            }
        }

        return result;
    }

    /**
     * 简单验证是否为回文
     *
     * @param s 字符串
     * @param i 开始位置
     * @param j 结束位置
     * @return
     */
    private boolean validPalindrome(String s, int i, int j) {
        for (; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }


}
