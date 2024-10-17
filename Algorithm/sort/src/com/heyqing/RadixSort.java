package com.heyqing;

/**
 * ClassName:RadixSort
 * Package:com.heyqing
 * Description:
 * 基数排序
 *
 * @Date:2024/10/16
 * @Author:Heyqing
 */
public class RadixSort {

    /**
     * 外部调用接口
     *
     * @param source
     */
    public static void sort(int[] source) {
        radix(source);
    }

    /**
     * 排序具体实现
     *
     * @param nums
     */
    private static void radix(int[] nums) {
        int max = findMax(nums), n = nums.length;
        for (int i = 1; max / i > 0; i *= 10) {
            achieveFuncRadix(nums, n, i);
        }
    }

    /**
     * 实现函数
     *
     * @param nums
     * @param n
     * @param exp
     */
    private static void achieveFuncRadix(int[] nums, int n, int exp) {
        int[] output = new int[n];
        int i;
        int[] count = new int[10];
        for (i = 0; i < n; i++)
            count[(nums[i] / exp) % 10]++;
        for (i = 1; i < 10; i++)
            count[i] += count[i - 1];
        for (i = n - 1; i >= 0; i--) {
            output[count[(nums[i] / exp) % 10] - 1] = nums[i];
            count[(nums[i] / exp) % 10]--;
        }
        System.arraycopy(output, 0, nums, 0, nums.length);
    }

    /**
     * 找出数组中的最大数
     *
     * @param nums
     * @return
     */
    private static int findMax(int[] nums) {
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > max) max = nums[i];
        }
        return max;
    }
}
