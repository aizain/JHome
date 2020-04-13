package com.aizain.jhome.computer.data.list;

import com.aizain.jhome.computer.data.entity.ListNode;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * HasCycle_161
 * 141. 环形链表
 * <p>
 * 给定一个链表，判断链表中是否有环。
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 * <p>
 * 示例 1：
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 * <p>
 * 示例 2：
 * 输入：head = [1,2], pos = 0
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 * <p>
 * 示例 3：
 * 输入：head = [1], pos = -1
 * 输出：false
 * 解释：链表中没有环。
 * <p>
 * 进阶：
 * <p>
 * 你能用 O(1)（即，常量）内存解决此问题吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/linked-list-cycle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Zain
 * @date 2019/11/2 create
 * @date 2020/03/02 update
 */
public class HasCycle_161 {

    /**
     * 审题：
     * 1 只输出是否有环
     * 2 可以用 O(1)（即，常量）内存
     * 3 题目并没有说是否有重复元素
     * <p>
     * 思路：
     * 1 hash表，重复则有环
     * 2 双指针：快慢指针
     * <p>
     * 反馈：
     * 1 会有重复数据
     * 2 不能用值判重，但可以用节点判重
     * 3 异常方式
     * 4 递归，可以不管原对象
     *
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        // return hashSolution(head);
        // return fastAndSlowSolution(head);
        // return fastAndSlowExceptionSolution(head);
        // return traverseBreakSolution(head);
        return traverseMarkSolution(head);
    }

    /**
     * 递归：标记法
     *
     * @param head
     * @return
     */
    private boolean traverseMarkSolution(ListNode head) {
        if (head == null) {
            return false;
        }
        if (head.val == 0xcafebabe) {
            return true;
        }
        head.val = 0xcafebabe;
        return traverseMarkSolution(head.next);
    }

    /**
     * 递归：破坏链表结构
     *
     * @param head
     * @return
     */
    private boolean traverseBreakSolution(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        // 判断是否是自环状态
        if (head == head.next) {
            return true;
        }
        // 让遍历过的节点自环
        ListNode breaker = head.next;
        head.next = head;
        return traverseBreakSolution(breaker);
    }

    /**
     * 快慢指针异常版
     *
     * @param head
     * @return
     */
    private boolean fastAndSlowExceptionSolution(ListNode head) {
        try {
            ListNode slow = head;
            ListNode fast = head.next;
            while (!slow.equals(fast)) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 双指针：快慢指针
     *
     * @param head
     * @return
     */
    private boolean fastAndSlowSolution(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            if (slow.equals(fast)) {
                return true;
            }
            slow = slow.next;
            fast = fast.next.next;
        }

        return false;
    }

    /**
     * hash表
     *
     * @param head
     * @return
     */
    private boolean hashSolution(ListNode head) {
        Set<ListNode> set = new LinkedHashSet<>();
        while (head != null) {
            if (set.contains(head)) {
                return true;
            }
            set.add(head);
            head = head.next;
        }
        return false;
    }


    public boolean hasCycle20191102(ListNode head) {
        Set<ListNode> visited = new HashSet<>();
        boolean contains = false;
        while (head != null && !contains) {
            contains = visited.contains(head);
            visited.add(head);
            head = head.next;
        }

        return contains;
    }

    public boolean hasCycleFastAntSlow(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null && slow != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }

        return false;
    }

}
