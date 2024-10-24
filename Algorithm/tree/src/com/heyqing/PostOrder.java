package com.heyqing;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

/**
 * ClassName:PostOrder
 * Package:com.heyqing
 * Description:
 * 后序遍历:LRD
 *
 * @Date:2024/10/21
 * @Author:Heyqing
 */
public class PostOrder {

    /**
     * 后序遍历 - 递归
     *
     * @param root
     * @return
     */
    public static List<Integer> postOrderByRecursion(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        return achieveFuncByPoOR(root, result);
    }

    /**
     * 实现函数 - 后序遍历 - 递归
     *
     * @param root
     * @param result
     */
    private static List<Integer> achieveFuncByPoOR(TreeNode root, List<Integer> result) {
        if (Objects.isNull(root)) return result;
        achieveFuncByPoOR(root.left, result);
        achieveFuncByPoOR(root.right, result);
        result.add(root.val);
        return result;
    }

    /**
     * 后序遍历 - 非递归
     *
     * @param root
     * @return
     */
    public static List<Integer> postOrderByNonRecursion(TreeNode root) {
        /*
        申请两个栈
        先将root压入栈中
        再将stack1弹出top1，向stack1压入top1的孩子（向左后右），将top1压入stack2，直至stack1 为空
        依次弹出stack2
         */
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(root);
        while (!stack1.isEmpty()) {
            TreeNode top1 = stack1.pop();
            if (Objects.nonNull(top1.left)) {
                stack1.push(top1.left);
            }
            if (Objects.nonNull(top1.right)) {
                stack1.push(top1.right);
            }
            stack2.push(top1);
        }
        while (!stack2.isEmpty()) {
            result.add(stack2.pop().val);
        }
        return result;
    }
}
