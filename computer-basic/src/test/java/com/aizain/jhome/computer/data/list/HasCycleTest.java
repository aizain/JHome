package com.aizain.jhome.computer.data.list;

import com.aizain.jhome.computer.data.entity.ListNode;
import com.aizain.jhome.computer.data.list.util.ListUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Slf4j
class HasCycleTest {

    private HasCycle hasCycle = new HasCycle();
    private ListNode inputNode;

    @BeforeEach
    void setUp() {
        inputNode = ListUtils.create(new int[]{3, 2, 0, -4}, 1);
    }

    @Test
    void hasCycle() {
        ListUtils.logListNode(log, "hasCycle begin", inputNode);
        boolean endNode = hasCycle.hasCycle(inputNode);
        ListUtils.logListNode(log, "hasCycle end " + endNode, inputNode);
    }

    @Test
    void hasCycleFastAntSlow() {
        ListUtils.logListNode(log, "hasCycleFastAntSlow begin", inputNode);
        boolean endNode = hasCycle.hasCycleFastAntSlow(inputNode);
        ListUtils.logListNode(log, "hasCycleFastAntSlow end " + endNode, inputNode);
    }
}