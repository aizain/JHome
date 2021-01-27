package com.bheternal.jhome.computer.algo.sort;

/**
 * InsertionSort
 * 插入排序
 *
 * @author Zain
 * @date 2021/1/7
 */
public class InsertionSort extends SortTool implements Sort {

    public static void main(String[] args) {
        SortTool.run(InsertionSort.class);
    }

    @Override
    public void sort(int[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j > 0; j--) {
                // 比最近的数大，就不需要往前插入了
                if (arr[j] > arr[j-1]) {
                    break;
                }
                swap(arr, j, j-1);
            }
        }

    }
    
}
