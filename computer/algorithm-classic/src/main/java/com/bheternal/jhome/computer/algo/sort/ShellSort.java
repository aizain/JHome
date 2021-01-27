package com.bheternal.jhome.computer.algo.sort;

import java.util.Arrays;

/**
 * ShellSort
 * 希尔排序
 *
 * @author Zain
 * @date 2021/1/8
 */
public class ShellSort extends SortTool implements Sort {

    public static void main(String[] args) {
        SortTool.run(ShellSort.class);
    }

    @Override
    public void sort(int[] arr) {
        // TODO
        // // 4,3,9,5,1
        // // 先分组
        // for (int i = 1; i <= arr.length / 2; i *= 2) {
        //
        //     // 每组比较排序
        //     for (int j = i; j < arr.length-1; j += i) {
        //         for (int k = j+i; k < arr.length; k++) {
        //             if (arr[j] > arr[k]) {
        //                 swap(arr, j, k);
        //             }
        //         }
        //     }
        // }

    }

}
