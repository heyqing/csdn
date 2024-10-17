package com.heyqing;

/**
 * ClassName:MergeSort
 * Package:com.heyqing
 * Description:
 * 归并排序
 *
 * @Date:2024/10/16
 * @Author:Heyqing
 */
public class MergeSort {

    /**
     * 外部调用接口
     *
     * @param source
     */
    public static void sort(int[] source) {
        merge(source);
    }

    /**
     * 排序具体实现
     *
     * @param nums
     */
    private static void merge(int[] nums) {
        int start = 0, end = nums.length - 1;
        achieveFuncMerge(nums, start, end);
    }

    /**
     * 实现函数
     *
     * @param nums
     * @param start
     * @param end
     */
    private static void achieveFuncMerge(int[] nums, int start, int end) {
        if (start >= end) return;
        //找到中间索引 防止溢出
        int mid = start + ((end - start) >> 1);
        //分别对左右两部分进行归并排序
        //左边
        achieveFuncMerge(nums, start, mid);
        //右边
        achieveFuncMerge(nums, mid + 1, end);
        //合并两个已排序的部分
        mergeSortNums(nums, start, mid, end);
    }

    /**
     * 合并两个已排序的部分
     *
     * @param nums
     * @param start
     * @param mid
     * @param end
     */
    private static void mergeSortNums(int[] nums, int start, int mid, int end) {
        //计算两个子数组的长度
        int len1 = mid - start + 1;
        int len2 = end - mid;
        //创建临时数组
        int[] L = new int[len1];
        int[] R = new int[len2];
        //将数据拷贝到临时数组中
        System.arraycopy(nums, start, L, 0, len1);
        System.arraycopy(nums, mid + 1, R, 0, len2);
        //合并临时数组
        int i = 0, j = 0;
        int k = start;
        while (i < len1 && j < len2) {
            if (L[i] <= R[j]) {
                nums[k] = L[i];
                i++;
            } else {
                nums[k] = R[j];
                j++;
            }
            k++;
        }
        // 拷贝L中剩余的元素
        while (i < len1) {
            nums[k] = L[i];
            i++;
            k++;
        }
        // 拷贝R中剩余的元素
        while (j < len2) {
            nums[k] = R[j];
            j++;
            k++;
        }
    }
}
