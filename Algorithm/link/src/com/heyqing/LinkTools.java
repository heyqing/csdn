package com.heyqing;

import java.util.*;

import static com.heyqing.HasCycle.getEnterCycleNode;

/**
 * ClassName:LinkTools
 * Package:com.heyqing
 * Description:
 * 链表常用操作类
 *
 * @Date:2024/10/19
 * @Author:Heyqing
 */
public class LinkTools {


    /**
     * 反转链表
     *
     * @param head
     * @return
     */
    public static ListNode reverseLinked(ListNode head) {
        return achieveFuncReverse(head, null);
    }

    /**
     * 反转链表实现函数
     *
     * @param head
     * @param last
     * @return
     */
    private static ListNode achieveFuncReverse(ListNode head, ListNode last) {
        if (Objects.isNull(head)) return last;
        ListNode cur = new ListNode(head.val, last);
        return achieveFuncReverse(head.next, cur);
    }


    /**
     * 创建环形相交链表
     *
     * @param length
     * @return
     */
    public static List<ListNode> createIntersectCycleLinked(int length) {
        if (length <= 1) {
            return null;
        }
        List<ListNode> result = new ArrayList<>(2);
        ListNode head1;
        ListNode head2 = createCycleLinked(length);
        ListNode cur2 = head2;
        List<Integer> enterCycleNode2 = getEnterCycleNode(head2);
        Random r = new Random();
        int point2 = enterCycleNode2.get(0);
        int point1 = r.nextInt(length - point2 + 1);
        System.out.printf("相交环形链表，从入环节点开始，第 %d 个为另一条链表入环节点\n", point1);
        int hashcode2 = enterCycleNode2.get(2);
        while (Objects.nonNull(cur2) && cur2.hashCode() != hashcode2) {
            cur2 = cur2.next;
        }
        for (int i = 0; i < point1; i++) {
            cur2 = cur2.next;
        }
        head1 = createRandomLinked(length - point2);
        ListNode cur1 = head1;
        while (Objects.nonNull(cur1) && Objects.nonNull(cur1.next)) {
            cur1 = cur1.next;
        }
        cur1.next = cur2;
        result.add(head1);
        result.add(head2);
        return result;
    }

    /**
     * 创建相交链表
     *
     * @param length
     * @return
     */
    public static List<ListNode> createIntersectLinked(int length) {
        List<ListNode> result = new ArrayList<>(2);
        ListNode head1 = createRandomLinked(length);
        Random r = new Random();
        int point = r.nextInt(length - 1) + 1;
        ListNode head2 = createRandomLinked(length - point);
        ListNode cur1 = head1, cur2 = head2;
        for (int i = 0; i < point; i++) {
            cur1 = cur1.next;
        }
        while (Objects.nonNull(cur2.next)) {
            cur2 = cur2.next;
        }
        cur2.next = cur1;
        result.add(head1);
        result.add(head2);
        return result;
    }

    /**
     * 创建环形链表
     *
     * @param length
     * @return
     */
    public static ListNode createCycleLinked(int length) {
        if (length <= 2) {
            return null;
        }
        Random r = new Random();
        int point = r.nextInt(length) + 1;
        ListNode head = new ListNode();
        ListNode cur = head, pointNode = null;
        for (int i = 0; i < length; i++) {
            cur.next = new ListNode(r.nextInt(50));
            cur = cur.next;
            if ((i + 1) == point) pointNode = cur;
        }
        cur.next = pointNode;
        printCycleLinked(head.next, point);
        return head.next;
    }

    /**
     * 内置环形链表打印
     *
     * @param head
     * @param index
     */
    private static void printCycleLinked(ListNode head, int index) {
        System.out.printf("环形链表（最后一个与第 %d 成环）：", index);
        Set<Integer> set = new HashSet<>();
        while (set.add(head.hashCode())) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    /**
     * 创建随机数组
     *
     * @param length
     * @return
     */
    public static ListNode createRandomLinked(int length) {
        if (length <= 0) {
            return null;
        }
        ListNode head = new ListNode();
        ListNode cur = head;
        Random r = new Random();
        for (int i = 0; i < length; i++) {
            cur.next = new ListNode(r.nextInt(50));
            cur = cur.next;
        }
        return head.next;
    }

    /**
     * 返回一个回文链表,length为链表长度
     *
     * @param length
     * @return
     */
    public static ListNode createPalindromeLinked(int length) {
        if (length <= 0) {
            return null;
        }
        ListNode head = new ListNode();
        ListNode cur = head;
        int[] nums = new int[length / 2];
        Random r = new Random();
        // 构建前半部分链表
        for (int i = 0; i < length / 2; i++) {
            int var = r.nextInt(50);
            cur.next = new ListNode(var);
            cur = cur.next;
            nums[i] = var;
        }
        // 如果链表长度为奇数，添加中间节点
        if ((length & 1) == 1) {
            cur.next = new ListNode(r.nextInt(50));
            cur = cur.next;
        }
        // 构建后半部分链表
        for (int i = (length / 2) - 1; i >= 0; i--) {
            cur.next = new ListNode(nums[i]);
            cur = cur.next;
        }
        return head.next;
    }

    /**
     * 打印链表
     *
     * @param head
     */
    public static void print(ListNode head) {
        while (Objects.nonNull(head)) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }
}
