package com.bheternal.jhome.computer.algo.sort;

/**
 * SortBenchmark
 *
 * @author Zain
 * @date 2021/1/7
 */
public class SortBenchmark {

    public static void main(String[] args) {
        int[] arr = SortTool.getRandomArr(100000, 99999);
        SortTool.run(BubbleSort.class, arr);
        SortTool.run(SelectionSort.class, arr);
        SortTool.run(InsertionSort.class, arr);
        SortTool.run(ShellSort.class, arr);

    }

}
