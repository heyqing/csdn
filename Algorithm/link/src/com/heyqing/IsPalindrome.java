package com.heyqing;

import java.util.Objects;
import java.util.Stack;

import static com.heyqing.LinkTools.*;

/**
 * ClassName:IsPalindrome
 * Package:com.heyqing
 * Description:
 * 回文链表
 *
 * @Date:2024/10/19
 * @Author:Heyqing
 */
public class IsPalindrome {

    /**
     * 栈 - 全压
     *
     * @param head
     * @return
     */
    public static boolean isPalindromeV1(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        ListNode p = head;
        while (Objects.nonNull(p)) {
            stack.push(p.val);
            p = p.next;
        }
        while (Objects.nonNull(head)) {
            if (stack.pop() - head.val != 0) return false;
            head = head.next;
        }
        return true;
    }


    /**
     * 栈 - 压一半
     *
     * @param head
     * @return
     */
    public static boolean isPalindromeV2(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        /*
        快慢指针
         */
        if (Objects.isNull(head) || Objects.isNull(head.next) || Objects.isNull(head.next.next)) {
            return judgeLinkedLengthLess3(head);
        }
        ListNode quick = head.next.next;
        ListNode slow = head.next;
        stack.push(head.val);
        while (Objects.nonNull(quick) && Objects.nonNull(quick.next)) {
            quick = quick.next.next;
            stack.push(slow.val);
            slow = slow.next;
        }
        if (Objects.nonNull(quick)) slow = slow.next;
        while (Objects.nonNull(slow)) {
            if (stack.pop() - slow.val != 0) return false;
            slow = slow.next;
        }
        return true;
    }

    /**
     * 快慢指针 - 反转后一半
     * <p>
     * 不使用额外空间（有限的几个变量）
     *
     * @param head
     * @return
     */
    public static boolean isPalindromeV3(ListNode head) {
        if (Objects.isNull(head) || Objects.isNull(head.next) || Objects.isNull(head.next.next)) {
            return judgeLinkedLengthLess3(head);
        }
        ListNode quick = head;
        ListNode slow = head;
        ListNode reverse = null;
        while (Objects.nonNull(quick.next) && Objects.nonNull(quick.next.next)) {
            slow = slow.next;
            quick = quick.next.next;
        }
        /*
         快指针指向慢指针
         慢指针指向空
         使用reverse反转
         */
        quick = slow.next;
        slow.next = null;
        while (Objects.nonNull(quick)) {
            //记录下一个
            reverse = quick.next;
            //反转 指向上一个
            quick.next = slow;
            //记录当前位置，下一个的上一个
            slow = quick;
            //跳转至下一个
            quick = reverse;
        }
        quick = head;
        while (Objects.nonNull(slow) && Objects.nonNull(quick)) {
            if (slow.val != quick.val) return false;
            slow = slow.next;
            quick = quick.next;
        }
        return true;
    }

    /**
     * 长度小于 3 的链表
     *
     * @param head
     * @return
     */
    private static boolean judgeLinkedLengthLess3(ListNode head) {
        if (Objects.isNull(head) || Objects.isNull(head.next)) {
            return true;
        }
        if (Objects.isNull(head.next.next)) {
            return head.val == head.next.val;
        } else {
            return head.val == head.next.next.val;
        }
    }
}
