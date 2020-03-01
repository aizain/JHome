package com.aizain.jhome.computer.data.recursive;

import java.util.LinkedList;
import java.util.List;

/**
 * Template
 *
 * @author Zain
 * @date 2020/3/1
 */
public class Template {

    public static void main(String[] args) {
        // 1 判断退出条件
        // 2 处理当前层
        // 3 处理下一层
        // 4 清理当前层

        // 8.1 递归终结条件 recursion terminator
        // 8.2 处理当前层逻辑 process logic in current level
        // 8.3 下探到下一层 drill down
        // 8.4 清理当前层 reverse the current level status if needed

        // 1 递归终结条件
        // 2 整理数据，处理当前层
        // 3 下探到子问题处理
        // 4 合并子问题结果
        // 5 清理当前层

        // 10.1 递归终结条件 recursion terminator
        // 10.2 准备数据 prepare data
        // 10.3 处理子问题 conquer subproblems
        // 10.4 处理生成最终结果 process and generate the final result
        // 10.5 清理当前层 reverse the current level status if needed

        String ret = upperCase("abcdgadfeqf");
        System.out.println(ret);
    }

    private static String upperCase(String str) {
        // 1 递归终结条件
        if (str == null || str.length() < 1) {
            return str;
        }
        if (str.length() == 1) {
            return str.toUpperCase();
        }
        // 2 准备数据，处理当前层
        String[] splitStr = str.split("");
        // 3 下探处理子问题
        List<String> results = new LinkedList<>();
        for (String one : splitStr) {
            results.add(upperCase(one));
        }
        // 4 合并子问题结果
        String ret = String.join("", results);
        // 5 清理当前层
        return ret;
    }

}
