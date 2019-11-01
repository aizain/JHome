package com.aizain.jhome.computer.data.list;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Slf4j
class ReverseListTest {
    ReverseList reverseList = new ReverseList();
    ReverseList.ListNode inputNode = new ReverseList.ListNode(1);

    @BeforeEach
    void setUp() {
        inputNode.next = new ReverseList.ListNode(2);
        inputNode.next.next = new ReverseList.ListNode(3);
        inputNode.next.next.next = new ReverseList.ListNode(4);
        inputNode.next.next.next.next = new ReverseList.ListNode(5);
        inputNode.next.next.next.next.next = null;
    }

    @Test
    void reverseList() {
        log.debug("reverseList begin: {}", getPrint(inputNode));
        ReverseList.ListNode endNode = reverseList.reverseList(inputNode);
        log.debug("reverseList end: {}", getPrint(endNode));
    }

    @Test
    void reverseListSimple() {
        log.debug("reverseList begin: {}", getPrint(inputNode));
        ReverseList.ListNode endNode = reverseList.reverseListSimple(inputNode);
        log.debug("reverseList end: {}", getPrint(endNode));
    }

    @Test
    void reverseListRecursive() {
        log.debug("reverseList begin: {}", getPrint(inputNode));
        ReverseList.ListNode endNode = reverseList.reverseListRecursive(inputNode);
        log.debug("reverseList end: {}", getPrint(endNode));
    }

    private String getPrint(ReverseList.ListNode nodeList) {
        StringBuilder stringBuilder = new StringBuilder("[");
        ReverseList.ListNode tmp = nodeList;
        while (tmp.next != null) {
            stringBuilder
                    .append(tmp.val)
                    .append(", ")
            ;
            tmp = tmp.next;
        }
        stringBuilder.append(tmp.val).append("]");
        return stringBuilder.toString();
    }

}