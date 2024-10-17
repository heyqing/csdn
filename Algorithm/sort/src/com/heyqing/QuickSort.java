package com.heyqing;

import java.util.Arrays;
import java.util.Random;

/**
 * ClassName:QuickSort
 * Package:com.heyqing
 * Description:
 * 快速排序
 *
 * @Date:2024/10/16
 * @Author:Heyqing
 */
public class QuickSort {

    /**
     * 外部调用接口 - 第一版本
     *
     * @param source
     */
    public static void sortV1(int[] source) {
        quickV1(source);
    }

    /**
     * 外部调用接口 - 第二版本
     *
     * @param source
     */
    public static void sortV2(int[] source) {
        quickV2(source);
    }

    /**
     * 外部调用接口 - 第三版本
     *
     * @param source
     */
    public static void sortV3(int[] source) {
        quickV3(source);
    }

    /**
     * 外部调用接口 - Java库函数
     *
     * @param source
     */
    public static void sort(int[] source) {
        quick(source);
    }

    /**
     * 排序具体实现 - v1
     *
     * @param nums
     */
    private static void quickV1(int[] nums) {
        /*
         使用最后一个数 n 作为划分值
         使得n前面的所有数以荷兰国旗式划分开
         划分完后使用大于区域的第一个数与 n 交换
         然后对 n 左右区域做递归处理
         */
        int start = 0, end = nums.length;
        achieveFunctionQuickV1(nums, start, end);
    }

    /**
     * 实现函数 - v1
     *
     * @param nums
     * @param start
     * @param end
     */
    private static void achieveFunctionQuickV1(int[] nums, int start, int end) {
        if (start >= end) return;
        int position = DutchFlagV1(nums, start, end);
        swap(nums, position + 1, end - 1);
        //左边
        achieveFunctionQuickV1(nums, start, position + 1);
        //右边
        achieveFunctionQuickV1(nums, position + 2, end);
    }


    /**
     * 排序具体实现 - v2
     *
     * @param nums
     */
    private static void quickV2(int[] nums) {
        /*
         使用最后一个数 n 作为划分值
         使得n前面的所有数以荷兰国旗式划分开
         划分完后使用大于区域的第一个数与 n 交换
         然后对 n 左右区域做递归处理
         与第一版不同的是将等于部分置于中间
         */
        int start = 0, end = nums.length;
        achieveFunctionQuickV2(nums, start, end);
    }

    /**
     * 实现函数 - v2
     *
     * @param nums
     * @param start
     * @param end
     */
    private static void achieveFunctionQuickV2(int[] nums, int start, int end) {
        if (start >= end) return;
        //一最后一个数为分界数，返回第一个大于和最后一个小于分界数的下标
        int[] positions = DutchFlagV2(nums, start, end);
        //左边
        achieveFunctionQuickV2(nums, start, positions[0]);
        //右边
        achieveFunctionQuickV2(nums, positions[1], end);
    }


    /**
     * 排序具体实现 - v3
     *
     * @param nums
     */
    private static void quickV3(int[] nums) {
        /*
         随机选择数组中的一个数作为分界数
         使得n前面的所有数以荷兰国旗式划分开
         划分完后使用大于区域的第一个数与 n 交换
         然后对 n 左右区域做递归处理
         与第二版不同的是随机选取数EX = n log n
         */
        int start = 0, end = nums.length;
        achieveFunctionQuickV3(nums, start, end);
    }

    /**
     * 实现函数 - v3
     *
     * @param nums
     * @param start
     * @param end
     */
    private static void achieveFunctionQuickV3(int[] nums, int start, int end) {
        if (start >= end) return;
        //随机选取
        Random r = new Random();
        int randomIndex = r.nextInt(end - start) + start;
        swap(nums, randomIndex, end - 1);
        int[] positions = DutchFlagV3(nums, start, end);
        //左边
        achieveFunctionQuickV3(nums, start, positions[0] + 1);
        //右边
        achieveFunctionQuickV3(nums, positions[1], end);
    }


    /**
     * 排序具体实现 - java依赖库
     * <p>
     * 双轴快速排序（Dual-Pivot Quicksort）算法
     *
     * @param nums
     */
    private static void quick(int[] nums) {
        /*
         Java中的Arrays.sort方法底层使用的排序算法依赖于数组的类型和大小。对于基本数据类型数组（如int[], long[]等）
         Arrays.sort使用的是双轴快速排序（Dual-Pivot Quicksort）算法，这是一种优化过的快速排序算法
         由Vladimir Yaroslavskiy、Jon Bentley和Joshua Bloch提出
         它在许多数据集上提供了接近O(n log n)的性能，通常比传统的单轴快速排序更快
         */
        Arrays.sort(nums);
    }


    /**
     * 荷兰国旗算法 - v1
     * <p>
     * 给定一个数组 nums 数组中的最后一个数为 target
     * <p>
     * 要求将数组 nums 中所有小于等于 target 的数置于左侧，大于的数置于右侧
     * <p>
     * 返回分界值 position ：小于等于 position 的数值为 小于等于 target 的值
     *
     * @param nums
     * @param start
     * @param end
     * @return
     */
    private static int DutchFlagV1(int[] nums, int start, int end) {
        int len = end - start, position = start - 1, target = nums[end - 1];
        for (int i = start; i < len - 1; i++) {
            if (nums[i] <= target) {
                swap(nums, i, position + 1);
                position++;
            }
        }
        return position;
    }


    /**
     * 荷兰国旗算法 - v2
     * <p>
     * 给定一个数组 nums 数组中的最后一个数为 target
     * <p>
     * 要求将数组 nums 中所有小于 target 的数置于左侧，等于的数置于中间，大于的数置于右侧
     * <p>
     * 返回分界值 positions ：小于的边界，大于的边界
     *
     * @param nums
     * @param start
     * @param end
     * @return
     */
    private static int[] DutchFlagV2(int[] nums, int start, int end) {
        int target = nums[end - 1];
        //初始化边界数组
        int[] positions = new int[]{start - 1, end};
        for (int i = start; i < positions[1]; i++) {
            if (nums[i] < target) {
                //小于
                swap(nums, i, positions[0] + 1);
                positions[0]++;
            } else if (nums[i] > target) {
                //大于
                swap(nums, i, positions[1] - 1);
                positions[1]--;
                i--;
            }
        }
        return positions;
    }

    /**
     * 荷兰国旗算法 - v3
     * <p>
     * 给定一个数组 nums 数组中的随机一个数为 target
     * <p>
     * 要求将数组 nums 中所有小于 target 的数置于左侧，等于的数置于中间，大于的数置于右侧
     * <p>
     * 返回分界值 positions ：小于的边界，大于的边界
     *
     * @param nums
     * @param start
     * @param end
     * @return
     */
    private static int[] DutchFlagV3(int[] nums, int start, int end) {
        int target = nums[end - 1];
        //初始化边界数组
        int[] positions = new int[]{start - 1, end};
        for (int i = start; i < positions[1]; i++) {
            if (nums[i] < target) {
                //小于
                swap(nums, i, positions[0] + 1);
                positions[0]++;
            } else if (nums[i] > target) {
                //大于
                swap(nums, i, positions[1] - 1);
                positions[1]--;
                i--;
            }
        }
        return positions;
    }

    /**
     * 交换两个数
     *
     * @param nums
     * @param i
     * @param j
     */
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
