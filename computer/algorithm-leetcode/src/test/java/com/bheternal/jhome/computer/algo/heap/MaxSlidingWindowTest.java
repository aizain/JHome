package com.bheternal.jhome.computer.algo.heap;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MaxSlidingWindowTest {

    @Test
    void maxSlidingWindow() {
        int k = 3;
        int[] arr = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        MaxSlidingWindow maxSlidingWindow = new MaxSlidingWindow();
        assertEquals(
                Arrays.toString(new int[]{3, 3, 5, 5, 6, 7}),
                Arrays.toString(maxSlidingWindow.maxSlidingWindow(arr, k))
        );

        k = 1;
        arr = new int[]{1, -1};
        assertEquals(
                Arrays.toString(new int[]{1, -1}),
                Arrays.toString(maxSlidingWindow.maxSlidingWindow(arr, k))
        );
    }

    @Test
    void maxSlidingWindowFast() {
        int k = 3;
        int[] arr = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        MaxSlidingWindow maxSlidingWindow = new MaxSlidingWindow();
        assertEquals(
                Arrays.toString(new int[]{3, 3, 5, 5, 6, 7}),
                Arrays.toString(maxSlidingWindow.maxSlidingWindowFast(arr, k))
        );

        k = 1;
        arr = new int[]{1, -1};
        assertEquals(
                Arrays.toString(new int[]{1, -1}),
                Arrays.toString(maxSlidingWindow.maxSlidingWindowFast(arr, k))
        );
    }

    @Test
    void maxSlidingWindowFastAndSimple() {
        int k = 3;
        int[] arr = new int[]{1, 3, 1, 2, 0, 5};
        MaxSlidingWindow maxSlidingWindow = new MaxSlidingWindow();
        assertEquals(
                Arrays.toString(new int[]{3, 3, 2, 5}),
                Arrays.toString(maxSlidingWindow.maxSlidingWindowFastAndSimple(arr, k))
        );

        k = 1;
        arr = new int[]{1, -1};
        assertEquals(
                Arrays.toString(new int[]{1, -1}),
                Arrays.toString(maxSlidingWindow.maxSlidingWindowFastAndSimple(arr, k))
        );

        k = 2;
        arr = new int[]{7, 2, 4};
        assertEquals(
                Arrays.toString(new int[]{7, 4}),
                Arrays.toString(maxSlidingWindow.maxSlidingWindowFastAndSimple(arr, k))
        );

    }
}