package com.bheternal.jhome.computer.algo.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * IsAnagram
 * 242. 有效的字母异位词
 * <p>
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * <p>
 * 示例 1:
 * <p>
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: s = "rat", t = "car"
 * 输出: false
 * 说明:
 * 你可以假设字符串只包含小写字母。
 * <p>
 * 进阶:
 * 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-anagram
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * <p>
 * 解题：
 * 1 排序 O(logN)
 * 2 map计数 O(N)
 * 2.1 自带hash map
 * 2.2 数组实现hash
 *
 * @author Zain
 * @date 2019/11/8
 */
public class IsAnagram {

    public boolean isAnagram(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }
        Map<Character, Integer> map = new HashMap<>(s.length());
        for (Character sOne : s.toCharArray()) {
            Integer v = map.get(sOne);
            if (v == null) {
                v = 0;
            }
            map.put(sOne, ++v);
        }
        for (Character tOne : t.toCharArray()) {
            Integer v = map.get(tOne);
            if (v == null || v == 0) {
                return false;
            }
            map.put(tOne, --v);
        }
        return true;
    }

    public boolean isAnagramUseHash(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }

        int[] hash = new int[26];
        for (Character sOne : s.toCharArray()) {
            hash[sOne - 'a']++;
        }
        for (Character tOne : t.toCharArray()) {
            int v = hash[tOne - 'a'];
            if (v == 0) {
                return false;
            }
            hash[tOne - 'a']--;
        }

        return true;
    }

    public boolean isAnagramUseHashFast(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }

        int[] hash = new int[26];
        for (int i = 0; i < s.length(); i++) {
            hash[s.charAt(i) - 'a']++;
            hash[t.charAt(i) - 'a']--;
        }

        for (int value : hash) {
            if (value != 0) {
                return false;
            }
        }

        return true;
    }

}
