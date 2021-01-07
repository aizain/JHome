package com.bheternal.jhome.computer.algo.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * SortTool
 *
 * @author Zain
 * @date 2021/1/7
 */
public abstract class SortTool implements Sort {

    public static void run(Class<? extends SortTool> sortClass, int[] arr) {
        try {
            SortTool sortTool = sortClass.newInstance();
            sortTool.run(Arrays.copyOf(arr, arr.length));
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void run(Class<? extends SortTool> sortClass) {
        try {
            SortTool sortTool = sortClass.newInstance();
            sortTool.run(sortTool.getArr());
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取初始化数组
     *
     * @return
     */
    public int[] getArr() {
        return getFixedArr();
    }

    public static int[] getFixedArr() {
        return new int[]{
                10, 1, 2, 1, 99, 12, 23, 33, 44, 19, 3, 111, 21
        };
    }

    public static int[] getRandomArr(int maxLen, int maxNum) {
        int[] arr = new int[maxLen];
        for (int i = 0; i < maxLen; i++) {
            arr[i] = new Random().nextInt(maxNum);
        }

        return arr;
    }

    public void run(int[] arr) {
        String simpleName = this.getClass().getSimpleName();
        String arrShow = arrFormat(arr);
        String numberShow = numberFormat(arr.length);

        System.out.printf("%s: arr[%s]: %s\n", simpleName, numberShow, arrShow);
        long begin = System.currentTimeMillis();
        sort(arr);
        long end = System.currentTimeMillis();

        arrShow = arrFormat(arr);
        System.out.printf("%s: sorted[%s]: %s\n", simpleName, timeFormat(end - begin), arrShow);
    }

    public void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public String arrFormat(int[] arr) {
        int maxLen = 20;
        int[] formatArr = new int[maxLen];
        for (int i = 0; i < arr.length && i < maxLen; i++) {
            formatArr[i] = arr[i];
        }

        return Arrays.toString(formatArr);
    }

    public String numberFormat(long number) {
        long wanLimit = 1000;
        StringBuilder builder = new StringBuilder();
        if (number > wanLimit) {
            long wan = number / 10000;
            number -= wan * 10000;
            builder.append(wan).append("万");
        }

        if (number != 0) {
            builder.append(number);
        }
        return builder.toString();
    }

    public String timeFormat(long time) {
        String unit = "ms";
        long msLimit = 1000;
        if (time > msLimit) {
            unit = "s";
            time = time / 1000;
        }

        return String.format("%s%s", time, unit);
    }

}
