package com.heyqing;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * ClassName:CompleteBinaryTree
 * Package:com.heyqing
 * Description:
 *
 * @Date:2024/10/23
 * @Author:Heyqing
 */
public class CompleteBinaryTree {

    /**
     * 判断完全二叉树
     *
     * @param root
     * @return
     */
    public static boolean judgeBCT(TreeNode root) {
        /*
        使用层次遍历
        碰到第一个无右树的节点后的所有节点必须为叶子节点，否则就不是完全二叉树
         */
        if (Objects.isNull(root)) return false;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean noRight = false;
        while (!queue.isEmpty()) {
            int loop = queue.size();
            for (int i = 0; i < loop; i++) {
                TreeNode first = queue.poll();
                /*
                碰到第一个无右树节点后，发现有不是叶子节点的节点 - false
                 */
                if (noRight && (Objects.nonNull(first.left) || Objects.nonNull(first.right))) {
                    return false;
                }
                /*
                有右没左 - false
                 */
                if (Objects.isNull(first.left) && Objects.nonNull(first.right)) {
                    return false;
                }
                if (Objects.nonNull(first.left)) {
                    queue.offer(first.left);
                }
                if (Objects.nonNull(first.right)) {
                    queue.offer(first.right);
                } else {
                    /*
                    第一个无右树的节点
                     */
                    noRight = true;
                }
            }
        }
        return true;
    }
}
