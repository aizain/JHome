package com.bheternal.jhome.computer.algo.sort;

import java.util.Arrays;

/**
 * BubbleSort
 * 冒泡排序
 *
 * @author Zain
 * @date 2020/12/11
 */
public class BubbleSort extends SortTool implements Sort {

    public static void main(String[] args) {
        SortTool.run(BubbleSort.class);
    }

    @Override
    public void sort(int[] arr) {
        // 外层只需要遍历 n-1 此就行
        // 外层 O(n-1) = O(n)
        // 外层+内层 O(n*(n+1)/2) = O(n^2)
        for (int i = 0; i < arr.length - 1; i++) {
            // 内层已经排好的无序再次排序
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

}
