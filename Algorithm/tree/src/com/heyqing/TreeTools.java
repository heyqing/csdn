package com.heyqing;

import java.util.*;

/**
 * ClassName:TreeTools
 * Package:com.heyqing
 * Description:
 * 树工具类
 *
 * @Date:2024/10/20
 * @Author:Heyqing
 */
public class TreeTools {
    private static boolean ldrFlag = true;
    private static boolean dlrFlag = true;
    private static boolean lrdFlag = true;
    private static boolean ltFlag = true;
    private static TreeNode ldr = new TreeNode(-1);
    private static TreeNode dlr = new TreeNode(-1);
    private static TreeNode lrd = new TreeNode(-1);
    private static TreeNode lt;

    /**
     * 创建随机数
     *
     * @param level
     * @return
     */
    public static TreeNode createRandomTree(int level) {

        int height = 1;
        Random r = new Random();
        TreeNode root = new TreeNode(r.nextInt(100));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            if (height >= level) break;
            int loop = queue.size(), fIndex = 0;
            boolean[] f = new boolean[(int) Math.pow(2, loop) + 1];
            boolean hasTrue = false;
            for (int i = 0; i < f.length; i++) {
                hasTrue |= r.nextBoolean();
                f[i] = hasTrue;
            }
            for (int i = 0; i < loop; i++) {
                TreeNode first = queue.poll();
                if (f[fIndex++]) {
                    //左
                    first.left = new TreeNode(r.nextInt(100));
                    queue.offer(first.left);
                }
                if (f[fIndex++]) {
                    first.right = new TreeNode(r.nextInt(100));
                    queue.offer(first.right);
                }
            }
            height++;
        }
        System.out.print("随机树层次遍历：");
        System.out.println(LevelOrder.levelOrderLayer(root));
        return root;
    }

    /**
     * 创建升序满先序二叉树
     *
     * @param level
     * @param start
     * @param step
     * @return
     */
    public static TreeNode createAscByDLRFullTree(int level, int start, int step) {
        int[] array = createAscArray(level, start, step);
        achieveFuncByAscDLR(null, array, 0, array.length - 1);
        System.out.print("先序二叉树创建内置打印：");
        printDLR(dlr);
        System.out.println();
        return dlr;
    }


    /**
     * 创建升序满中序二叉树
     *
     * @param level
     * @param start
     * @param step
     * @return
     */
    public static TreeNode createAscByLDRFullTree(int level, int start, int step) {
        int[] array = createAscArray(level, start, step);
        achieveFuncByAscLDR(null, array, 0, array.length - 1);
        System.out.print("中序二叉树创建内置打印：");
        printLDR(ldr);
        System.out.println();
        return ldr;
    }

    /**
     * 创建升序满后序二叉树
     *
     * @param level
     * @param start
     * @param step
     * @return
     */
    public static TreeNode createAscByLRDFullTree(int level, int start, int step) {
        int[] array = createAscArray(level, start, step);
        achieveFuncByAscLRD(null, array, 0, array.length - 1);
        System.out.print("后序二叉树创建内置打印：");
        printLRD(lrd);
        System.out.println();
        return lrd;
    }

    /**
     * 创建层次遍历树
     *
     * @param level
     * @param start
     * @param step
     * @return
     */
    public static TreeNode createLevelAseFullTree(int level, int start, int step) {
        int[] array = createAscArray(level, start, step);
        int i = 0;
        TreeNode dummy = new TreeNode(array[i++]), root = dummy;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (i < array.length) {
            TreeNode first = queue.poll();
            first.left = new TreeNode(array[i++]);
            queue.offer(first.left);
            first.right = new TreeNode(array[i++]);
            queue.offer(first.right);
        }
        printLT(array);
        return dummy;
    }

    /**
     * 打印中序遍历
     *
     * @param array
     */
    private static void printLT(int[] array) {
        System.out.print("内置层次遍历打印，满二叉树：|| ");
        int level = 1;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
            if ((i + 1) == Math.pow(2, level) - 1) {
                level++;
                System.out.print("|| ");
            }
        }
        System.out.println();
    }


    /**
     * 打印中序遍历
     *
     * @param root
     */
    public static void printLDR(TreeNode root) {
        if (Objects.isNull(root)) return;
        printLDR(root.left);
        System.out.print(root.val + " ");
        printLDR(root.right);
    }

    /**
     * 打印先序遍历
     *
     * @param root
     */
    public static void printDLR(TreeNode root) {
        if (Objects.isNull(root)) return;
        System.out.print(root.val + " ");
        printDLR(root.left);
        printDLR(root.right);
    }


    /**
     * 打印后序遍历
     *
     * @param root
     */
    public static void printLRD(TreeNode root) {
        if (Objects.isNull(root)) return;
        printLRD(root.left);
        printLRD(root.right);
        System.out.print(root.val + " ");
    }


    /**
     * 创建升序满后序二叉树 - 实现函数
     *
     * @param root
     * @param array
     * @param i
     * @param j
     * @return
     */
    private static TreeNode achieveFuncByAscLRD(TreeNode root, int[] array, int i, int j) {
        if (i > j) return root;
        int mid = i + ((j - i) >> 1);
        root = new TreeNode(array[j]);
        if (lrdFlag) {
            lrd = root;
            lrdFlag = false;
        }
        root.left = achieveFuncByAscLRD(root.left, array, i, mid - 1);
        root.right = achieveFuncByAscLRD(root.right, array, mid, j - 1);
        return root;
    }

    /**
     * 创建升序满先序二叉树 - 实现函数
     *
     * @param root
     * @param array
     * @param i
     * @param j
     */
    private static TreeNode achieveFuncByAscDLR(TreeNode root, int[] array, int i, int j) {
        if (i > j) return root;
        int mid = i + ((j - i) >> 1);
        root = new TreeNode(array[i]);
        if (dlrFlag) {
            dlr = root;
            dlrFlag = false;
        }
        root.left = achieveFuncByAscDLR(root.left, array, i + 1, mid);
        root.right = achieveFuncByAscDLR(root.right, array, mid + 1, j);
        return root;
    }

    /**
     * 创建升序满中序二叉树 - 实现函数
     *
     * @param root
     * @param array
     * @param i
     * @param j
     */
    private static TreeNode achieveFuncByAscLDR(TreeNode root, int[] array, int i, int j) {
        if (i > j) return root;
        int mid = i + ((j - i) >> 1);
        root = new TreeNode(array[mid]);
        if (ldrFlag) {
            ldr = root;
            ldrFlag = false;
        }
        root.left = achieveFuncByAscLDR(root.left, array, i, mid - 1);
        root.right = achieveFuncByAscLDR(root.right, array, mid + 1, j);
        return root;
    }


    /**
     * 创建升序数组
     *
     * @param level
     * @param start
     * @param step
     * @return
     */
    private static int[] createAscArray(int level, int start, int step) {
        int[] array = new int[(int) (Math.pow(2, level)) - 1];
        Random r = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = start;
            start += r.nextInt(step) + 1;
        }
        return array;
    }
}
