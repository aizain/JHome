package com.bheternal.jhome.computer.algo.hash;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ThreeSumTest {

    private final ThreeSum threeSum = new ThreeSum();

    @Test
    void threeSum() {
        assertEquals(
                Arrays.asList(
                        Arrays.asList(-1, 0, 1),
                        Arrays.asList(-1, -1, 2)
                ),
                threeSum.threeSum(new int[]{-1, 0, 1, 2, -1, -4})
        );
    }
}