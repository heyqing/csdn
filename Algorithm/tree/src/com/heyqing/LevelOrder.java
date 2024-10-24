package com.heyqing;

import java.util.*;

/**
 * ClassName:LevelOrder
 * Package:com.heyqing
 * Description:
 * 层次遍历
 *
 * @Date:2024/10/21
 * @Author:Heyqing
 */
public class LevelOrder {

    /**
     * 层次遍历
     *
     * @param root
     * @return
     */
    public static List<Integer> levelOrder(TreeNode root) {
        if (Objects.isNull(root)) return null;
        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode first = queue.poll();
            result.add(first.val);
            if (Objects.nonNull(first.left)) {
                queue.offer(first.left);
            }
            if (Objects.nonNull(first.right)) {
                queue.offer(first.right);
            }
        }
        return result;
    }


    /**
     * 层次遍历 - 分层
     *
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrderLayer(TreeNode root) {
        if (Objects.isNull(root)) return null;
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int loop = queue.size();
            List<Integer> temp = new ArrayList<>(loop);
            for (int i = 0; i < loop; i++) {
                TreeNode first = queue.poll();
                temp.add(first.val);
                if (Objects.nonNull(first.left)) {
                    queue.offer(first.left);
                }
                if (Objects.nonNull(first.right)) {
                    queue.offer(first.right);
                }
            }
            result.add(temp);
        }
        return result;
    }
}
