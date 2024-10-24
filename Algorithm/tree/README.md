# 二叉树的做题技巧 - 初级

## 简介

> 本文主要以代码说明为主，若有不理解，或有错误地方可留言指出说明，感谢`^_^`
>
> 更多代码: [code](https://github.com/heyqing/csdn/tree/master/Algorithm/tree/src/com/heyqing)

### 二叉树的基本概念

二叉树是一种非线性的数据结构，它由节点组成，每个节点最多有两个子节点，通常称为左子节点和右子节点。二叉树的特点是每个节点的子节点数量不超过2，且子节点的位置是固定的，即左子节点在右子节点之前。二叉树可以是空的，也可以包含一个或多个节点。在二叉树中，根节点位于最高层级，没有父节点，而其他所有节点都至少有一个父节点

### 二叉树的特殊类型

- **满二叉树**:每一层的节点数都达到最大值，即除了最后一层外，每层都完全填满，且所有节点都尽可能地向左对齐
- **完全二叉树**:除了最后一层外，其他各层的节点数都达到最大值，且最后一层的节点都连续集中在左侧。满二叉树是完全二叉树的一种特例
- **平衡二叉树**:也称为$AVL$树，是一种特殊的二叉搜索树。它的特点是任何节点的两个子树的高度差的绝对值不超过1，并且左子树和右子树也都是平衡二叉树
- **搜索二叉树**:如果树非空，则树的根节点的值要么是小于其左子树中所有节点的值，要么是大于其右子树中所有节点的值;树的左子树和右子树本身也必须是二叉搜索树

### 二叉树的性质

- 对于非空二叉树，第i层最多有 $2^{i−1}$个节点。

- 深度为h的二叉树最多有 $2^h−1$个节点。

- 具有n个节点的满二叉树的高度 $h=log⁡(n+1)$（以2为底）。

- 在完全二叉树中，如果按照从上至下从左至右的数组顺序对所有节点进行编号，则第i个节点的双亲节点编号为 $⌊(i−1)/2⌋$，左孩子节点编号为 $2i+1$ ，右孩子节点编号为 $2i+2$

### 二叉树的遍历

- **前序遍历**：先访问根节点，再遍历左子树，最后遍历右子树。

- **中序遍历**：先遍历左子树，再访问根节点，最后遍历右子树。在二叉搜索树中，中序遍历会得到一个升序列。

- **后序遍历**：先遍历左子树和右子树，最后访问根节点。

- **层次遍历**：按照树的层次从上到下、从左到右进行遍历，通常使用队列来辅助实现

## 二叉树的特殊类型

### 满二叉树与完全二叉树

```java
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
```

### 平衡二叉树

```java
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
```

### 搜索二叉树

```java
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
```

## 二叉树的遍历

### 前序遍历

```java
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
```

### 中序遍历

```java
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
```

### 后序遍历

```java
package com.heyqing;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

/**
 * ClassName:PostOrder
 * Package:com.heyqing
 * Description:
 * 后序遍历:LRD
 *
 * @Date:2024/10/21
 * @Author:Heyqing
 */
public class PostOrder {

    /**
     * 后序遍历 - 递归
     *
     * @param root
     * @return
     */
    public static List<Integer> postOrderByRecursion(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        return achieveFuncByPoOR(root, result);
    }

    /**
     * 实现函数 - 后序遍历 - 递归
     *
     * @param root
     * @param result
     */
    private static List<Integer> achieveFuncByPoOR(TreeNode root, List<Integer> result) {
        if (Objects.isNull(root)) return result;
        achieveFuncByPoOR(root.left, result);
        achieveFuncByPoOR(root.right, result);
        result.add(root.val);
        return result;
    }

    /**
     * 后序遍历 - 非递归
     *
     * @param root
     * @return
     */
    public static List<Integer> postOrderByNonRecursion(TreeNode root) {
        /*
        申请两个栈
        先将root压入栈中
        再将stack1弹出top1，向stack1压入top1的孩子（向左后右），将top1压入stack2，直至stack1 为空
        依次弹出stack2
         */
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(root);
        while (!stack1.isEmpty()) {
            TreeNode top1 = stack1.pop();
            if (Objects.nonNull(top1.left)) {
                stack1.push(top1.left);
            }
            if (Objects.nonNull(top1.right)) {
                stack1.push(top1.right);
            }
            stack2.push(top1);
        }
        while (!stack2.isEmpty()) {
            result.add(stack2.pop().val);
        }
        return result;
    }
}
```

### 层次遍历

```java
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
```

## 二叉树的简单题练习 

## 最低公共祖先

给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。

[百度百科](https://baike.baidu.com/item/最近公共祖先/8918834?fr=aladdin)中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（**一个节点也可以是它自己的祖先**）。”

## 纸条折痕

请把一段纸条竖着放在桌子上，然后从纸条的下边向上方对折1次，压出折痕后展开。此时折痕是凹下去的（下折痕），即折痕突起的方向指向纸条的背面。如果从纸条的下边向上方连续对折2次，压出折痕后展开，此时有三条折痕，从上到下依次是下折痕、下折痕和上折痕。

给定一个输入参数N，代表纸条从下边向上方连续对折N次。请从上到下打印所有折痕的方向。
## 答案

```java
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
```

