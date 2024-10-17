package com.heyqing;

/**
 * ClassName:CountingSort
 * Package:com.heyqing
 * Description:
 * 计数排序
 *
 * @Date:2024/10/16
 * @Author:Heyqing
 */
public class CountingSort {
    /**
     * 外部调用接口
     *
     * @param source
     */
    public static void sort(int[] source) {
        counting(source);
    }

    /**
     * 排序具体实现
     *
     * @param nums
     */
    private static void counting(int[] nums) {
        /*
        找出最大值max，然后开辟 max + 1 大小的空间计数
        该算法局限与待比较数的大小规模
         */
        //找出数组中的最大值
        int max = findMax(nums);
        //初始化计数数组
        int[] count = new int[max + 1];
        for (int i = 0; i < nums.length; i++) {
            count[nums[i]]++;
        }
        //累加计数数组
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }
        //反向填充目标数组
        int[] output = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            output[count[nums[i]] - 1] = nums[i];
            count[nums[i]]--;
        }
        //将排序后的数组复制回原数组
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
