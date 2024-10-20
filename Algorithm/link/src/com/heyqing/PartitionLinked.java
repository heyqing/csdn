package com.heyqing;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ClassName:PartitionLinked
 * Package:com.heyqing
 * Description:
 * 划分链表
 *
 * @Date:2024/10/19
 * @Author:Heyqing
 */
public class PartitionLinked {

    /**
     * 划分链表 - 数组
     *
     * @param head
     * @return
     */
    public static ListNode partitionLinkedV1(ListNode head, int target) {
        if (Objects.isNull(head)) return null;
        List<ListNode> listNodes = new ArrayList<>();
        while (Objects.nonNull(head)) {
            listNodes.add(head);
            head = head.next;
        }
        //划分
        int size = listNodes.size(), min = -1, max = size;
        for (int i = 0; i < max; i++) {
            if (listNodes.get(i).val < target) {
                //小于
                swap(listNodes, i, min + 1);
                min++;
            } else if (listNodes.get(i).val > target) {
                //大于
                swap(listNodes, i, max - 1);
                i--;
                max--;
            }
        }
        //串起来
        ListNode result = new ListNode();
        ListNode p = result;
        for (int i = 0; i < size; i++) {
            p.next = listNodes.get(i);
            p = p.next;
        }
        p.next = null;
        return result.next;
    }

    /**
     * 划分链表 - 有限变量
     * <p>
     * 保证稳定性
     *
     * @param head
     * @param target
     * @return
     */
    public static ListNode partitionLinkedV2(ListNode head, int target) {
        //定义小于 等于 大于 的首位指针
        ListNode sh = null, st = null, eh = null, et = null, mh = null, mt = null, next = null;
        while (Objects.nonNull(head)) {
            next = head.next;
            head.next = null;
            if (head.val < target) {
                //小于
                if (Objects.isNull(sh)) {
                    sh = head;
                } else {
                    st.next = head;
                }
                st = head;
            } else if (head.val == target) {
                //等于
                if (Objects.isNull(eh)) {
                    eh = head;
                } else {
                    et.next = head;
                }
                et = head;
            } else {
                //大于
                if (Objects.isNull(mh)) {
                    mh = head;
                } else {
                    mt.next = head;
                }
                mt = head;
            }
            head = next;
        }
        if (Objects.nonNull(st)) {
            st.next = eh;
            et = Objects.isNull(et) ? st : et;
        }
        if (Objects.nonNull(et)) {
            et.next = mh;
        }
        return Objects.nonNull(sh) ? sh : (Objects.nonNull(eh) ? eh : mh);
    }

    private static void swap(List<ListNode> listNodes, int i, int j) {
        ListNode temp = listNodes.get(i);
        listNodes.set(i, listNodes.get(j));
        listNodes.set(j, temp);
    }

}
