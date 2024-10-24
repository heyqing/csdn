package com.heyqing;

import java.util.Objects;

/**
 * ClassName:SearchBinaryTree
 * Package:com.heyqing
 * Description:
 * 搜索二叉树
 *
 * @Date:2024/10/22
 * @Author:Heyqing
 */
public class SearchBinaryTree {

    /**
     * 判断是否为搜索二叉树
     *
     * @param root
     * @return
     */
    public static boolean judgeBST(TreeNode root) {
        /*
        搜索二叉树：左子树都比自己小，右子树都比自己大
        使用中序遍历 LDR
        左树为搜索二叉树，右树为搜索二叉树
        左树的最大值小于 当前树
        右树的最小数大于 当前树
         */
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean isValidBST(TreeNode node, long minValue, long maxValue) {
        if (Objects.isNull(node)) {
            return true;
        }
        /*
        小于最小值或大于最大值 - 不是搜索二叉树
         */
        if (node.val <= minValue || node.val >= maxValue) {
            return false;
        }
        return isValidBST(node.left, minValue, node.val) && isValidBST(node.right, node.val, maxValue);
    }
}
