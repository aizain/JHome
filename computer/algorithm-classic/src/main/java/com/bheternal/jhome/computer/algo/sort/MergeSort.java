package com.bheternal.jhome.computer.algo.sort;

import java.util.Arrays;

/**
 * MergeSort
 * 归并排序
 *
 * @author Zain
 * @date 2021/1/13
 */
public class MergeSort extends SortTool implements Sort {

    public static void main(String[] args) {
        SortTool.run(MergeSort.class);
    }

    @Override
    public void sort(int[] arr) {
        // 1 边界
        if (arr.length <= 1) {
            return;
        }
        if (arr.length <= 2) {
            if (arr[1] > arr[0]) {
                swap(arr, 0, 1);
            }
            return;
        }

        // 2 预处理
        int len = arr.length;
        int slice = len/2;
        int[] arrLeft = Arrays.copyOfRange(arr, 0, slice);
        int[] arrRight = Arrays.copyOfRange(arr, slice, len);

        // 3 下探
        sort(arrLeft);
        sort(arrRight);

        // 4 合并
        for (int i = 0; i < len; i++) {
            arr[i] = i < slice ? arrLeft[i] : arrRight[i - slice];
        }
    }


}
