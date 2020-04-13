package com.aizain.jhome.computer.data.list;

import com.aizain.jhome.computer.data.entity.ListNode;

/**
 * ReverseKGroup_25
 * 25. K 个一组翻转链表
 * 难度：困难
 * <p>
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * <p>
 * 示例：
 * 给你这个链表：1->2->3->4->5
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 *  
 * 说明：
 * 你的算法只能使用常数的额外空间。
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Zain
 * @date 2020/3/7
 */
public class ReverseKGroup_25 {

    /**
     * 审题：
     * 1 k大于0 k小于等于size k等于1不需要翻转
     * 2 可以使用O(1)的额外空间
     * 3 必须实际进行节点交换
     * <p>
     * 思路：
     * 1 无思路，看题解
     * 2 递归
     * <p>
     * 反馈：
     * 1 分组处理，判断每组k边界，翻转该组（使用206题目翻转）
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        return recursiveSolution(head, k);
    }

    private ListNode recursiveSolution(ListNode head, int k) {

        while (boundCheck(head, k)) {
            ListNode tail = reverseK(head);
            head = tail.next;
        }
        return head;
    }

    private boolean boundCheck(ListNode head, int k) {
        if (k <= 1 || head == null) {
            return false;
        }
        return false;
    }

    private ListNode reverseK(ListNode head) {
        // 1 终结
        if (head.next == null) {
            return head;
        }
        // 2 当前
        // 3 下探
        ListNode tail = reverseK(head.next);

        // 4 清理
        head.next.next = head;
        head.next = null;
        return tail;
    }

}
