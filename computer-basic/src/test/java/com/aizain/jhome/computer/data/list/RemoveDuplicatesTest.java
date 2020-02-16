package com.aizain.jhome.computer.data.list;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Slf4j
class RemoveDuplicatesTest {

    private RemoveDuplicates solution = new RemoveDuplicates();
    private int[] input;

    @BeforeEach
    void setUp() {
        input = new int[]{};
    }

    @Test
    void solution() {
        input = new int[]{1, 1, 2, 3, 3, 4, 4, 5};
        int size = solution.removeDuplicates(input);
        log.debug("swapPairsRecursive begin: {}:{}", size, input);

        input = new int[]{1, 1, 2, 3, 4, 5, 5};
        size = solution.removeDuplicates(input);
        log.debug("swapPairsRecursive begin: {}:{}", size, input);
    }

}