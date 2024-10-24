package com.heyqing;

import java.util.*;

/**
 * ClassName:Question
 * Package:com.heyqing
 * Description:
 *
 * @Date:2024/10/23
 * @Author:Heyqing
 */
public class Question {


    /**
     * 最低公共祖先 - The Lowest Common Ancestor
     *
     * @param root
     * @param node1
     * @param node2
     * @param version
     * @return
     */
    public static List<Integer> lca(TreeNode root, TreeNode node1, TreeNode node2, String version) {
        /*
        val1和val2一定在树上
        version1:使用map，记录每个节点的父节点，
                然后将node1一致回溯到头节点，再将node2回溯到头，
                在node2回溯的过程中set添加失败节点即为共同祖先
        version2: 纯算法 *_*
         */
        // 返回 node.hashcode 和 value
        List<Integer> result = new ArrayList<>(2);
        if (version.equals("v1")) return lcaV1(root, node1, node2);
        if (version.equals("v2")) return lcaV2(root, node1, node2);
        return result;
    }

    /**
     * 纸条折痕
     *
     * @param n
     * @return
     */
    public static List<String> PaperFold(int n) {
        /*
        将一个纸条面向自己对折，从上到下出现一个折痕，这个折痕是向外凸向里凹的，称之为凹折痕，反之则为凸折痕
        问一个纸条在理想条件下这样往复对折 n 次 ，从上到下的折痕依次是什么折痕
         */
        List<String> fold = new ArrayList<>(((1 << n) - 1));
        achieveFuncByPaperFold(1, n, true, fold);
        return fold;
    }


    /******************************************************private******************************************************/

    /**
     * 纸条折痕 - 实现函数
     *
     * @param i
     * @param n
     * @param down
     * @param fold
     */
    private static void achieveFuncByPaperFold(int i, int n, boolean down, List<String> fold) {
        /*
        对折 n 次后，产生的折痕是以 凹折痕为root节点其所有左子树都是以凹折痕这位root，右子树为凸折痕为root
        中序遍历即可得到从上到下的折痕顺序
         */
        if (i > n) return;
        achieveFuncByPaperFold(i + 1, n, true, fold);
        fold.add(down ? "down" : "up");
        achieveFuncByPaperFold(i + 1, n, false, fold);
    }

    /**
     * 最低公共祖先 - v1 - map
     *
     * @param root
     * @param node1
     * @param node2
     * @return
     */
    private static List<Integer> lcaV1(TreeNode root, TreeNode node1, TreeNode node2) {
        /*
        使用map，记录每个节点的父节点，
        然后将node1一致回溯到头节点，再将node2回溯到头，
        在node2回溯的过程中set添加失败节点即为共同祖先
         */
        List<Integer> result = new ArrayList<>(2);
        Map<TreeNode, TreeNode> fatherMap = new HashMap<>();
        achieveFuncByFullFatherMap(root, fatherMap);
        Set<TreeNode> node1FatherSet = new HashSet<>();
        TreeNode cur = node1;
        while (!Objects.equals(cur, fatherMap.get(cur))) {
            node1FatherSet.add(cur);
            cur = fatherMap.get(cur);
        }
        // 将头节点加入
        node1FatherSet.add(root);
        cur = node2;
        while (!Objects.equals(cur, fatherMap.get(cur))) {
            TreeNode father = fatherMap.get(cur);
            if (node1FatherSet.contains(father)) {
                //此时的father为最低公共祖先
                result.add(father.hashCode());
                result.add(father.val);
            }
        }
        return result;
    }

    /**
     * 最低公共祖先 - v2
     *
     * @param root
     * @param node1
     * @param node2
     * @return
     */
    private static List<Integer> lcaV2(TreeNode root, TreeNode node1, TreeNode node2) {
        List<Integer> result = new ArrayList<>(2);
        TreeNode father = achieveFuncByLcaV2(root, node1, node2);
        result.add(father.hashCode());
        result.add(father.val);
        return result;
    }

    /**
     * 最低公共祖先 - v2 - 实现函数
     *
     * @param root
     * @param node1
     * @param node2
     * @return
     */
    private static TreeNode achieveFuncByLcaV2(TreeNode root, TreeNode node1, TreeNode node2) {
        /*
        root 可能出现的情况
        node1,node2一个是另一个的最低公共祖先，特点就是与node1，node2的兄弟树必没有匹配值 ： 先碰到谁谁就是最低公共祖先
        node1,node2一个不是另一个的最低公共祖先，特特点是他们分别在兄弟树上，必汇聚至一点 ：第一个左右子树都右返回值的点
         */
        // 碰到 null 返回 null，碰到 node1 返回 node1 ，碰到 node2 返回 node2
        if (Objects.isNull(root) || Objects.equals(root, node1) || Objects.equals(root, node2)) {
            return root;
        }
        // left，right 就四种情况 null , node1 , node2 ,father
        TreeNode left = achieveFuncByLcaV2(root.left, node1, node2);
        TreeNode right = achieveFuncByLcaV2(root.right, node1, node2);
        // 这里为第四中情况，最多只会出现一次，即当前树的左子树与右子树分别有node1和node2
        // 出现过一次以后必不会再出现，因为node1，node2都在当前树下，兄弟节点返回的必为null
        if (Objects.nonNull(left) && Objects.nonNull(right)) {
            return root;
        }
        // 有两种种情况会走到这 ： 1、node1,node2一个是另一个的最低公共祖先，2、第四中情况出现过一次后
        // 归根结底是因为两个节点都在当前树下，兄弟节点必为 null
        return Objects.nonNull(left) ? left : right;
    }

    /**
     * 填充父map
     *
     * @param root
     * @param fatherMap
     */
    private static void achieveFuncByFullFatherMap(TreeNode root, Map<TreeNode, TreeNode> fatherMap) {
        if (Objects.isNull(root)) return;
        fatherMap.put(root.left, root);
        fatherMap.put(root.right, root);
        achieveFuncByFullFatherMap(root.left, fatherMap);
        achieveFuncByFullFatherMap(root.right, fatherMap);
    }
}
