package com.bheternal.jhome.computer.algo.list;

 import com.bheternal.jhome.computer.algo.entity.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * DetectCycle_142
 * 142. 环形链表 II
 * 难度：中等
 * <p>
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 * 说明：不允许修改给定的链表。
 * <p>
 * 示例 1：
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：tail connects to node index 1
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 * <p>
 * 示例 2：
 * 输入：head = [1,2], pos = 0
 * 输出：tail connects to node index 0
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 * <p>
 * 示例 3：
 * 输入：head = [1], pos = -1
 * 输出：no cycle
 * 解释：链表中没有环。
 * <p>
 * 进阶：
 * 你是否可以不用额外空间解决此题？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/linked-list-cycle-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Zain
 * @date 2020/3/7
 */
public class DetectCycle_142 {

    /**
     * 审题：
     * 1 不能修改链表
     * 2 不用额外空间也可以解决
     * 3 返回环位置节点
     * <p>
     * 思路：
     * 1 hash
     * 2 双指针：快慢指针
     * <p>
     * 反馈：
     * 1 快慢指针使用Floyd算法
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        // return hashSolution(head);
        return floydSolution(head);
    }

    private ListNode floydSolution(ListNode head) {
        ListNode tail = getFloydIntersection(head);
        if (tail == null) {
            return null;
        }

        while (head != tail) {
            head = head.next;
            tail = tail.next;
        }
        return head;
    }

    private ListNode getFloydIntersection(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        // floyd交点需要同时出发
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow.equals(fast)) {
                return slow;
            }
        }
        return null;
    }

    /**
     * hash
     *
     * @param head
     * @return
     */
    private ListNode hashSolution(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while (head != null && head.next != null) {
            if (set.contains(head)) {
                return head;
            }
            set.add(head);
            head = head.next;
        }
        return null;
    }

}
