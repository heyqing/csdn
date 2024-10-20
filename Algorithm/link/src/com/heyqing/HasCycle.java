package com.heyqing;

import java.util.*;

/**
 * ClassName:HasCycle
 * Package:com.heyqing
 * Description:
 * 环形链表
 *
 * @Date:2024/10/19
 * @Author:Heyqing
 */
public class HasCycle {

    /**
     * 判断成环 -集合
     *
     * @param head
     * @return
     */
    public static boolean hasCycleV1(ListNode head) {
        /*
        使用hashcode与set
         */
        Set<Integer> set = new HashSet<>();
        while (Objects.nonNull(head)) {
            if (!set.add(head.hashCode())) return true;
            head = head.next;
        }
        return false;
    }

    /**
     * 判断成环 -快慢指针
     *
     * @param head
     * @return
     */
    public static boolean hasCycleV2(ListNode head) {
        ListNode quick = head;
        ListNode slow = head;
        while (Objects.nonNull(quick) && Objects.nonNull(quick.next)) {
            quick = quick.next.next;
            slow = slow.next;
            if (Objects.equals(quick, slow)) return true;
        }
        return false;
    }

    /**
     * 获取入环节点
     *
     * @param head
     * @return
     */
    public static List<Integer> getEnterCycleNode(ListNode head) {
        /*
        获取入环节点有多种解，这里只说明快慢指针的方式
        快指针一次两步，慢指针一次一步
        相遇后，慢指针不动，快指针指向头部
        再次相遇节点为入环节点
         */
        if (Objects.isNull(head)) return null;
        List<Integer> list = new ArrayList<>(3);
        ListNode quick = head;
        ListNode slow = head;
        while (Objects.nonNull(quick) && Objects.nonNull(quick.next)) {
            quick = quick.next.next;
            slow = slow.next;
            if (Objects.equals(quick, slow)) break;
        }
        quick = head;
        int count = 1;
        while (Objects.nonNull(quick)) {
            count++;
            quick = quick.next;
            slow = slow.next;
            if (Objects.equals(quick, slow)) break;
        }
        list.add(count);
        list.add(quick.val);
        list.add(quick.hashCode());
        return list;
    }

    /**
     * 判断相交链表
     * <p>
     * 成环 - 相交 / 不相交
     * 不成环 - 相交 / 不相交
     *
     * @param head1
     * @param head2
     * @return
     */
    public static boolean intersectLinked(ListNode head1, ListNode head2) {
        //判断有无环
        boolean isCycleByHead1 = hasCycleV2(head1);
        boolean isCycleByHead2 = hasCycleV2(head2);
        if ((isCycleByHead1 && !isCycleByHead2) || (!isCycleByHead1 && isCycleByHead2)) {
            //一个成环一个不成环 - 必不相交
            return false;
        }
        //不成环
        if (!isCycleByHead1) {
            return intersectLinkedByNoCycle(head1, head2);
        }
        //成环
        return intersectLinkedByCycle(head1, head2);
    }

    /**
     * 判断相交 - 成环
     *
     * @param head1
     * @param head2
     * @return
     */
    private static boolean intersectLinkedByCycle(ListNode head1, ListNode head2) {
        /*
        获取其入环节点
        如果其入环节点相等必相交
        如果不等分各自成环（2个环），共成环（1个环）
        入环后一个不动，一个走一圈，若期间碰到相交反之不相交
         */
        ListNode cur2 = head2;
        List<Integer> enterCycleNodeByHead1 = getEnterCycleNode(head1);
        List<Integer> enterCycleNodeByHead2 = getEnterCycleNode(head2);
        Integer head1Point = enterCycleNodeByHead1.get(2);
        Integer head2Point = enterCycleNodeByHead2.get(2);
        //让 cur1不动
        while (cur2.hashCode() != head2Point) {
            cur2 = cur2.next;
        }
        cur2 = cur2.next;
        while (cur2.hashCode() != head2Point) {
            if (cur2.hashCode() == head1Point) return true;
            cur2 = cur2.next;
        }
        return false;
    }

    /**
     * 判断相交 - 无环
     *
     * @param head1
     * @param head2
     * @return
     */
    private static boolean intersectLinkedByNoCycle(ListNode head1, ListNode head2) {
        ListNode cur1 = head1, cur2 = head2;
        while (Objects.nonNull(cur1.next)) {
            cur1 = cur1.next;
        }
        while (Objects.nonNull(cur2.next)) {
            cur2 = cur2.next;
        }
        /*
        如果相交，最后必有相等部分，其中最后一个比相等
        若不等就不相交
         */
        return cur1.hashCode() == cur2.hashCode();
    }
}
