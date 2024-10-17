package com.heyqing;

/**
 * ClassName:ShellSort
 * Package:com.heyqing
 * Description:
 * 希尔排序
 *
 * @Date:2024/10/16
 * @Author:Heyqing
 */
public class ShellSort {
    /**
     * 外部调用接口
     *
     * @param source
     */
    public static void sort(int[] source) {
        shell(source);
    }

    /**
     * 排序具体实现
     *
     * @param nums
     */
    private static void shell(int[] nums) {
        int n = nums.length;
        //初始增量，一般取数组长度的一半
        for (int i = n / 2; i > 0; i /= 2) {
            //对每个子序列进行插入排序
            for (int j = i; j < n; j++) {
                int temp = nums[j];
                int k;
                for (k = j; k >= i && nums[k - i] > temp; k -= i) {
                    nums[k] = nums[k - i];
                }
                nums[k] = temp;
            }
        }
    }
}
