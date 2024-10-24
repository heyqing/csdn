package com.heyqing;

import java.util.Objects;

/**
 * ClassName:BalancedBinaryTree
 * Package:com.heyqing
 * Description:
 * 平衡二叉树
 *
 * @Date:2024/10/24
 * @Author:Heyqing
 */
public class BalancedBinaryTree {
    /*
    平衡二叉树 : | left.height - right.height | <= 1
     */


    /**
     * 平衡二叉树判断返回类
     */
    private static class BalanceInfo {
        /**
         * 是否为平衡二叉树
         */
        private boolean isBalance;

        /**
         * 树高
         */
        private int height;

        public BalanceInfo(boolean isBalance, int height) {
            this.isBalance = isBalance;
            this.height = height;
        }
    }

    /**
     * 判断平衡二叉树
     *
     * @param root
     * @return
     */
    public static boolean isBBT(TreeNode root) {
        return achieveFuncByBalance(root).isBalance;
    }

    /**
     * 是否为平衡二叉树 - 实现函数
     *
     * @param root
     * @return
     */
    private static BalanceInfo achieveFuncByBalance(TreeNode root) {
        /*
        是否为平衡二叉树需要满足：左子树和右子树为平衡二叉树，自己的左右子树高度差小于等于 1
         */
        // 触底返回 - 叶子节点是平衡二叉树
        if (Objects.isNull(root)) return new BalanceInfo(true, 0);
        // 获取当前树的左子树
        BalanceInfo left = achieveFuncByBalance(root.left);
        // 获取当前树的右子树
        BalanceInfo right = achieveFuncByBalance(root.right);
        // 当前树高等于 ：子树最大高度 + 1
        int height = Math.max(left.height, right.height) + 1;
        // 当前树是不是平衡二叉树 ：左子树是平衡二叉树 and 右子树是平衡二叉树 and 自己的左右子树高度差小于 1
        boolean isBalance = left.isBalance && right.isBalance && Math.abs(left.height - right.height) <= 1;
        // 将当前树返回至顶层
        return new BalanceInfo(isBalance, height);
    }


}
