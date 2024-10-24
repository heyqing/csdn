package com.heyqing;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

/**
 * ClassName:PreOrder
 * Package:com.heyqing
 * Description:
 * 先序遍历 ：DLR
 *
 * @Date:2024/10/21
 * @Author:Heyqing
 */
public class PreOrder {


    /**
     * 先序遍历 - 递归
     *
     * @param root
     * @return
     */
    public static List<Integer> preOrderByRecursion(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        return achieveFuncByPOR(root, result);
    }

    /**
     * 实现函数 - 先序遍历 - 递归
     *
     * @param root
     * @param result
     * @return
     */
    private static List<Integer> achieveFuncByPOR(TreeNode root, List<Integer> result) {
        if (Objects.isNull(root)) return result;
        result.add(root.val);
        achieveFuncByPOR(root.left, result);
        achieveFuncByPOR(root.right, result);
        return result;
    }

    /**
     * 先序遍历 - 非递归
     *
     * @param root
     * @return
     */
    public static List<Integer> inOrderByNonRecursion(TreeNode root) {
        /*
        定义栈先将头压入
        然后弹出，获得栈顶元素进行操作，弹出后将孩子节点压入栈中（先右再左）
         */
        if (Objects.isNull(root)) return null;
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode top = stack.pop();
            result.add(top.val);
            //先右
            if (Objects.nonNull(top.right)) {
                stack.push(top.right);
            }
            //后左
            if (Objects.nonNull(top.left)) {
                stack.push(top.left);
            }
        }
        return result;
    }
}
