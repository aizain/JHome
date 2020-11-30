package com.bheternal.jhome.computer.algo.list.util;

import com.aizain.jhome.computer.data.entity.ListNode;
import org.slf4j.Logger;

/**
 * ListUtils
 *
 * @author Zain
 * @date 2019/11/2
 */
public final class ListUtils {

    private ListUtils() {
    }

    public static ListNode create(int[] val) {
        return create(val, -1);
    }

    public static ListNode create(int[] val, int cycle) {
        ListNode inputNode = null;
        ListNode tmpNode = new ListNode(-1);
        ListNode cycleNode = null;
        int i = 0;
        for (int v : val) {
            if (inputNode == null) {
                inputNode = new ListNode(v);
                tmpNode = inputNode;
            } else {
                ListNode listNode = new ListNode(v);
                tmpNode.next = listNode;
                tmpNode = listNode;
            }
            if (i == cycle) {
                cycleNode = tmpNode;
            }
            i++;
        }
        tmpNode.next = cycleNode;
        return inputNode;
    }

    public static ListNode create() {
        ListNode inputNode = new ListNode(1);
        inputNode.next = new ListNode(2);
        inputNode.next.next = new ListNode(3);
        inputNode.next.next.next = new ListNode(4);
        inputNode.next.next.next.next = new ListNode(5);
        inputNode.next.next.next.next.next = null;

        return inputNode;
    }

    public static void logListNode(Logger log, String msg, ListNode nodeList) {
        log.debug("{} {}", msg, getPrint(nodeList));
    }

    public static String getPrint(ListNode nodeList) {
        StringBuilder stringBuilder = new StringBuilder("[");
        ListNode tmp = nodeList;
        int deep = 10;
        while (tmp.next != null && deep >= 0) {
            stringBuilder
                    .append(tmp.val)
                    .append(", ")
            ;
            tmp = tmp.next;
            deep--;
        }
        stringBuilder.append(tmp.val).append("]");
        return stringBuilder.toString();
    }

}
