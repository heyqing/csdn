package com.heyqing;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

/**
 * ClassName:InOrder
 * Package:com.heyqing
 * Description:
 * 中序遍历：LDR
 *
 * @Date:2024/10/21
 * @Author:Heyqing
 */
public class InOrder {

    /**
     * 中序遍历 - 递归
     *
     * @param root
     * @return
     */
    public static List<Integer> inOrderByRecursion(TreeNode root) {
        List<Integer> listLDR = new ArrayList<>();
        return achieveFuncByIOR(root, listLDR);
    }

    /**
     * 中序遍历递归 - 实现函数
     *
     * @param root
     * @param listLDR
     * @return
     */
    private static List<Integer> achieveFuncByIOR(TreeNode root, List<Integer> listLDR) {
        if (Objects.isNull(root)) return listLDR;
        achieveFuncByIOR(root.left, listLDR);
        listLDR.add(root.val);
        achieveFuncByIOR(root.right, listLDR);
        return listLDR;
    }

    /**
     * 中序遍历 - 非递归
     *
     * @param root
     * @return
     */
    public static List<Integer> inOrderByNonRecursion(TreeNode root) {
        /*
        使用栈
        先将左边界压栈
        然后栈中一个 操作 再压入有孩子
         */
        List<Integer> listLDR = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        pushAllLeftNode(stack, root);
        while (!stack.isEmpty()) {
            TreeNode top = stack.pop();
            if (Objects.nonNull(top)) {
                listLDR.add(top.val);
                pushAllLeftNode(stack, top.right);
            }
        }
        return listLDR;
    }

    /**
     * 将所有左边界压入栈中
     *
     * @param stack
     * @param root
     */
    private static void pushAllLeftNode(Stack<TreeNode> stack, TreeNode root) {
        while (Objects.nonNull(root)) {
            stack.push(root);
            root = root.left;
        }
    }
}
