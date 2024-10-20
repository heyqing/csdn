package com.heyqing;

/**
 * ClassName:ListNode
 * Package:com.heyqing
 * Description:
 * 链表节点
 *
 * @Date:2024/10/19
 * @Author:Heyqing
 */
public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
