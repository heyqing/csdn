package com.heyqing;

/**
 * ClassName:BubbleSort
 * Package:com.heyqing
 * Description:
 * 冒泡排序
 *
 * @Date:2024/10/16
 * @Author:Heyqing
 */
public class BubbleSort {
    /**
     * 外部调用接口
     *
     * @param source
     */
    public static void sort(int[] source) {
        bubble(source);
    }

    /**
     * 排序具体实现
     *
     * @param nums
     */
    private static void bubble(int[] nums) {
        boolean flag; // 内层是否有序标志
        //第一层 不用判断最后一个数
        for (int i = 0; i < nums.length - 1; i++) {
            flag = false;
            //第二层 不用判断最后i躺个数
            for (int j = 0; j < nums.length - 1 - i; j++) {
                //如果前一个数 大于 后一个数
                if (nums[j] > nums[j + 1]) {
                    //交换两个数
                    swap(nums, j);
                    flag = true;
                }
            }
            // 如果内层循环没有进行交换，说明数组已经有序，可以提前结束排序
            if (!flag) break;
        }
    }

    /**
     * 交换两个数
     * <p>
     * index 与 index + 1 进行交换
     *
     * @param nums
     * @param index
     */
    private static void swap(int[] nums, int index) {
        /*
         若 a = nums[index] , b = nums[index + 1]
         第一行： a = a ^ b 此时： a = a ^ b ,b = b
         第二行： b = (a ^ b) ^ b = a 此时： a = a ^ b , b = a
         第三行： a = (a ^ b) ^ a = b 此时： a = b , b = a 完成交换
         此算法实现了不使用额外空间完成两数交换
         注意：使用此算法时要保证 a , b 不同内存空间，错误用例见附录
         */
        nums[index] = nums[index] ^ nums[index + 1];
        nums[index + 1] = nums[index] ^ nums[index + 1];
        nums[index] = nums[index] ^ nums[index + 1];
    }
}
