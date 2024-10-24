package com.heyqing;

/**
 * ClassName:TreeNode
 * Package:com.heyqing
 * Description:
 * 树节点
 *
 * @Date:2024/10/21
 * @Author:Heyqing
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
