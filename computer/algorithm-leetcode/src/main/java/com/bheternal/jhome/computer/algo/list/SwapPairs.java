package com.bheternal.jhome.computer.algo.list;

import com.aizain.jhome.computer.data.entity.ListNode;

/**
 * SwapPairs
 * 24. 两两交换链表中的节点
 * <p>
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *  
 * 示例:
 * <p>
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/swap-nodes-in-pairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Zain
 * @date 2019/11/1 create
 * @date 2020/03/02 update
 */
public class SwapPairs {

    /**
     * 审题:
     * 1 不能交换值，要节点交换
     * <p>
     * 思路：
     * 1 迭代，最近重复问题abc简化
     * 2 递归
     *
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        // return traverseSolution(head);
        return recursiveSolution(head);
    }

    /**
     * 递归
     * 0 ms	37.3 MB
     *
     * @param head
     * @return
     */
    private ListNode recursiveSolution(ListNode head) {
        // 1 终结
        if (head == null || head.next == null) {
            return head;
        }
        // 2 当前,abc简化
        ListNode first = head;
        ListNode second = head.next;
        // 3 下探
        ListNode third = recursiveSolution(head.next.next);
        // 4 清理，反转
        second.next = first;
        first.next = third;
        return second;
    }

    /**
     * 迭代
     * 0 ms	37.5 MB
     *
     * @param head
     * @return
     */
    private ListNode traverseSolution(ListNode head) {
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode ret = pre;
        while (head != null && head.next != null) {
            // abcd标记，简化
            ListNode first = pre;
            ListNode second = head;
            ListNode third = head.next;
            ListNode fourth = head.next.next;

            // 交换
            first.next = third;
            second.next = fourth;
            third.next = second;

            // 移动2位，注意节点交换了
            head = fourth;
            pre = second;
        }
        return ret.next;
    }

    public ListNode swapPairs201911011(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode end = head.next;

        ListNode pre = null;
        ListNode cur = head;
        ListNode curNext;
        while (cur != null && cur.next != null) {
            curNext = cur.next;
            ListNode tmp = curNext.next;
            curNext.next = cur;
            cur.next = tmp;
            if (pre != null) {
                pre.next = curNext;
            }

            pre = cur;
            cur = tmp;
        }

        return end;
    }

    public ListNode swapPairsRecursive(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode end = swapPairsRecursive(head.next.next);
        ListNode tmp = head.next;
        head.next = end;
        tmp.next = head;

        end = tmp;

        return end;
    }


}
