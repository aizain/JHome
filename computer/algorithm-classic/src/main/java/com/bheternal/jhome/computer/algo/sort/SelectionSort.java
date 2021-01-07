package com.bheternal.jhome.computer.algo.sort;

/**
 * SelectionSort
 * 选择排序
 *
 * @author Zain
 * @date 2021/1/7
 */
public class SelectionSort extends SortTool implements Sort {

    public static void main(String[] args) {
        SortTool.run(SelectionSort.class);
    }

    @Override
    public void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    swap(arr, i, j);
                }
            }
        }
    }


}
