package com.heyqing;

import java.util.List;
import java.util.Random;

import static com.heyqing.LinkTools.*;

/**
 * ClassName:Enter
 * Package:com.heyqing
 * Description:
 * 测试入口
 *
 * @Date:2024/10/18
 * @Author:Heyqing
 */
public class Enter {
    public static void main(String[] args) {
        /*
         经典例题说明
         */
        Random r = new Random();
        //链表长度
        int length = r.nextInt(20) + 3;
        reverseLinkedTest(length);
        isPalindromeTest(length);
        partitionLinkedTest(length, r.nextInt(50));
        cycleLinkedTest(length);
    }

    private static void cycleLinkedTest(int length) {
        System.out.println("-----------------环形链表-----------------");
        System.out.println("_________________判断成环__________________");
        ListNode cycleLinked = createCycleLinked(length);
        System.out.print("=====> v1:\t");
        System.out.println(HasCycle.hasCycleV1(cycleLinked));
        System.out.print("=====> v2:\t");
        System.out.println(HasCycle.hasCycleV2(cycleLinked));
        System.out.println("_________________获取入环节点__________________");
        System.out.println(HasCycle.getEnterCycleNode(cycleLinked));
        System.out.println("_________________相交链表__________________");
        ListNode v11 = createCycleLinked(length);
        ListNode v12 = createCycleLinked(length);
        List<ListNode> linksByCycle = createIntersectCycleLinked(length);
        List<ListNode> linksByNoCycle = createIntersectLinked(length);
        System.out.println("__________________成环不相交____________________");
        System.out.println(HasCycle.intersectLinked(v11, v12));
        System.out.println("__________________成环相交____________________");
        System.out.println(HasCycle.intersectLinked(linksByCycle.get(0), linksByCycle.get(1)));
        System.out.println("__________________不成环相交____________________");
        System.out.println(HasCycle.intersectLinked(linksByNoCycle.get(0), linksByNoCycle.get(1)));
        System.out.println("__________________不成环不相交____________________");
        ListNode randomLinked1 = createRandomLinked(length);
        ListNode randomLinked2 = createRandomLinked(length);
        System.out.println(HasCycle.intersectLinked(randomLinked1, randomLinked2));
    }

    private static void partitionLinkedTest(int length, int target) {
        System.out.println("-----------------划分链表-----------------");
        ListNode random = createRandomLinked(length);
        print(random);
        System.out.println(target);
        System.out.println("=====> v1:\t");
        print(PartitionLinked.partitionLinkedV1(random, target));
        //测试时v1 v2不能同时运行
        System.out.println("=====> v2:\t");
        print(PartitionLinked.partitionLinkedV2(random, target));
    }

    private static void isPalindromeTest(int length) {
        System.out.println("-----------------判断回文-----------------");
        System.out.println("__________________随机____________________");
        ListNode random = createRandomLinked(length);
        print(random);
        System.out.print("=====> v1:\t");
        System.out.println(IsPalindrome.isPalindromeV1(random));
        System.out.print("=====> v2:\t");
        System.out.println(IsPalindrome.isPalindromeV2(random));
        System.out.print("=====> v3:\t");
        System.out.println(IsPalindrome.isPalindromeV3(random));
        System.out.println("__________________偶数____________________");
        int evenParam = length % 2 == 0 ? length : length + 1;
        ListNode even = createPalindromeLinked(evenParam);
        print(even);
        System.out.print("=====> v1:\t");
        System.out.println(IsPalindrome.isPalindromeV1(even));
        System.out.print("=====> v2:\t");
        System.out.println(IsPalindrome.isPalindromeV2(even));
        System.out.print("=====> v3:\t");
        System.out.println(IsPalindrome.isPalindromeV3(even));
        System.out.println("__________________奇数____________________");
        int oddParam = length % 2 == 0 ? length - 1 : length;
        ListNode odd = createPalindromeLinked(oddParam);
        print(odd);
        System.out.print("=====> v1:\t");
        System.out.println(IsPalindrome.isPalindromeV1(odd));
        System.out.print("=====> v2:\t");
        System.out.println(IsPalindrome.isPalindromeV2(odd));
        System.out.print("=====> v3:\t");
        System.out.println(IsPalindrome.isPalindromeV3(odd));
    }

    private static void reverseLinkedTest(int length) {
        System.out.println("-----------------反转链表-----------------");
        ListNode head = createRandomLinked(length);
        print(head);
        ListNode reverse = reverseLinked(head);
        print(reverse);
    }
}
