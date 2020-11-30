package com.bheternal.jhome.computer.algo.list;

import com.aizain.jhome.computer.data.entity.ListNode;
import com.aizain.jhome.computer.data.list.util.ListUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Slf4j
class DeleteNodeTest {

    private final DeleteNode deleteNode = new DeleteNode();
    private ListNode inputNode;

    private

    @BeforeEach
    void setUp() {
        inputNode = ListUtils.create(new int[]{3, 2, 0, 1, 5, 100});
    }

    @Test
    void deleteNode() {
        ListUtils.logListNode(log, "deleteNode begin", inputNode);
        ListNode tmpNode = inputNode.next.next;
        deleteNode.deleteNode(tmpNode);
        ListUtils.logListNode(log, "deleteNode end ", inputNode);
    }
}