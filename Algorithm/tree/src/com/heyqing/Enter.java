package com.heyqing;

import java.util.Random;

import static com.heyqing.TreeTools.*;

/**
 * ClassName:Enter
 * Package:com.heyqing
 * Description:
 * 测试入口
 *
 * @Date:2024/10/20
 * @Author:Heyqing
 */
public class Enter {
    public static void main(String[] args) {
        // 创建树并不是多线程的故不能依次多个函数测试
//        preOrderTest();
//        inOrderTest();
//        postOrderTest();
//        levelOrderTest();
//        searchBinaryTreeTest();
//        completeBinaryTreeTest();
//        balancedBinaryTreeTest();
//        questionTest();
    }

    private static void questionTest() {
        System.out.println("-----------------简单的问题-----------------");
        TreeNode root = createLevelAseFullTree(3, 1, 1);
        System.out.println("________________最低公共祖先_________________");
        System.out.println("最低公共祖先这里并未实现其测试函数，但可去leetcode做题：\n" +
                "LCR 194. 二叉树的最近公共祖先:https://leetcode.cn/problems/er-cha-shu-de-zui-jin-gong-gong-zu-xian-lcof/description/ \n" +
                "236. 二叉树的最近公共祖先:https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/description/");
//        System.out.println(Question.lca(root,null,null,"v1"));
//        System.out.println(Question.lca(root,null,null,"v2"));
        System.out.println("________________纸条折痕_________________");
        Random r = new Random();
        int n = r.nextInt(7) + 1;
        System.out.print("折" + n + "次，后的折痕从上到下依次为：");
        System.out.println(Question.PaperFold(n));
    }

    private static void balancedBinaryTreeTest() {
        System.out.println("-----------------平衡二叉树-----------------");
        System.out.println("_____________判断是否为平衡二叉树_______________");
        TreeNode noBBT = createRandomTree(3);
        TreeNode bbt = createLevelAseFullTree(3, 1, 1);
        System.out.println("随机树：" + BalancedBinaryTree.isBBT(noBBT));
        System.out.println("层次树：" + BalancedBinaryTree.isBBT(bbt));
    }

    private static void completeBinaryTreeTest() {
        System.out.println("-----------------完全二叉树-----------------");
        System.out.println("_____________判断是否为完全二叉树_______________");
        TreeNode noBCT = createRandomTree(3);
        TreeNode lt = createLevelAseFullTree(3, 1, 1);
        System.out.println("随机树：" + CompleteBinaryTree.judgeBCT(noBCT));
        System.out.println("层次树：" + CompleteBinaryTree.judgeBCT(lt));
    }

    private static void searchBinaryTreeTest() {
        System.out.println("-----------------搜索二叉树-----------------");
        System.out.println("_____________判断是否为搜索二叉树_______________");
        TreeNode noBST = createRandomTree(3);
        TreeNode ldr = createAscByLDRFullTree(3, 1, 1);
        System.out.println("随机树：" + SearchBinaryTree.judgeBST(noBST));
        System.out.println("中序树：" + SearchBinaryTree.judgeBST(ldr));

    }

    private static void levelOrderTest() {
        System.out.println("-----------------层次遍历-----------------");
        TreeNode lt = createLevelAseFullTree(3, 1, 1);
        System.out.println("======> v1:");
        System.out.println(LevelOrder.levelOrder(lt));
        System.out.println("======> v2:");
        System.out.println(LevelOrder.levelOrderLayer(lt));
    }


    private static void preOrderTest() {
        System.out.println("-----------------先序遍历-----------------");
        TreeNode dlr = createAscByDLRFullTree(3, 1, 1);
        System.out.println("_________________递归__________________");
        System.out.println(PreOrder.preOrderByRecursion(dlr));
        System.out.println("_________________非递归__________________");
        System.out.println(PreOrder.inOrderByNonRecursion(dlr));
    }

    private static void inOrderTest() {
        System.out.println("-----------------中序遍历-----------------");
        TreeNode ldr = createAscByLDRFullTree(3, 1, 1);
        System.out.println("_________________递归__________________");
        System.out.println(InOrder.inOrderByRecursion(ldr));
        System.out.println("_________________非递归__________________");
        System.out.println(InOrder.inOrderByNonRecursion(ldr));
    }

    private static void postOrderTest() {
        System.out.println("-----------------后序遍历-----------------");
        TreeNode lrd = createAscByLRDFullTree(3, 1, 1);
        System.out.println("_________________递归__________________");
        System.out.println(PostOrder.postOrderByRecursion(lrd));
        System.out.println("_________________非递归__________________");
        System.out.println(PostOrder.postOrderByNonRecursion(lrd));
    }

}
