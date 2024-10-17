package com.heyqing;

/**
 * ClassName:InsertionSort
 * Package:com.heyqing
 * Description:
 * 插入排序
 *
 * @Date:2024/10/16
 * @Author:Heyqing
 */
public class InsertionSort {
    /**
     * 外部调用接口
     *
     * @param source
     */
    public static void sort(int[] source) {
        insertion(source);
    }

    /**
     * 排序具体实现
     *
     * @param nums
     */
    private static void insertion(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int key = nums[i];
            int j = i - 1;
            //将大于key的元素向后移动
            while (j >= 0 && nums[j] > key) {
                nums[j + 1] = nums[j];
                j -= 1;
            }
            //插入key到正确的位置
            nums[j + 1] = key;
        }
    }
}
