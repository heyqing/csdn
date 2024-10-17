package com.heyqing;

/**
 * ClassName:HeapSort
 * Package:com.heyqing
 * Description:
 * 堆排序
 *
 * @Date:2024/10/16
 * @Author:Heyqing
 */
public class HeapSort {
    /**
     * 外部调用接口
     *
     * @param source
     */
    public static void sort(int[] source) {
        heap(source);
    }

    /**
     * 排序具体实现
     *
     * @param nums
     */
    private static void heap(int[] nums) {
        int n = nums.length;
        //构建堆
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(nums, n, i);
        }
        //一个个从堆顶取出元素
        for (int i = n - 1; i >= 0; i--) {
            // 当前堆顶元素（最大值）与最后一个元素交换
            swap(nums, i, 0);
            //调整剩余堆
            heapify(nums, i, 0);
        }
    }

    /**
     * 堆化
     *
     * @param nums
     * @param n
     * @param i
     */
    private static void heapify(int[] nums, int n, int i) {
        int largest = i; // 初始化最大元素索引为根节点
        int left = 2 * i + 1; // 左子节点
        int right = 2 * i + 2; // 右子节点
        // 如果左子节点大于根节点
        if (left < n && nums[left] > nums[largest]) {
            largest = left;
        }
        // 如果右子节点比最大元素还大
        if (right < n && nums[right] > nums[largest]) {
            largest = right;
        }
        // 如果最大元素不是根节点
        if (largest != i) {
            swap(nums, i, largest);
            // 递归地调整受影响的子树
            heapify(nums, n, largest);
        }
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
