package com.heyqing;

/**
 * ClassName:SelectionSort
 * Package:com.heyqing
 * Description:
 * 选择排序
 *
 * @Date:2024/10/16
 * @Author:Heyqing
 */
public class SelectionSort {

    /**
     * 外部调用接口
     *
     * @param source
     */
    public static void sort(int[] source) {
        selection(source);
    }

    /**
     * 排序具体实现
     *
     * @param nums
     */
    private static void selection(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            int minTemp = i; // 找到从i开始到数组末尾的最小元素的索引
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[minTemp]) minTemp = j;
            }
            // 将找到的最小元素与第i个位置的元素交换
            swap(nums, i, minTemp);
        }
    }

    /**
     * 交换两个数 常规做法
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
