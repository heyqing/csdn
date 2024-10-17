package com.heyqing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ClassName:BucketSort
 * Package:com.heyqing
 * Description:
 * 桶排序
 *
 * @Date:2024/10/16
 * @Author:Heyqing
 */
public class BucketSort {
    /**
     * 外部调用接口
     *
     * @param source
     */
    public static void sort(float[] source) {
        bucket(source);
        print(source);
    }

    /**
     * 排序具体实现
     *
     * @param nums
     */
    private static void bucket(float[] nums) {
        int n = nums.length;
        List<Float>[] buckets = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            buckets[i] = new ArrayList<Float>();
        }
        for (float num : nums) {
            int idx = (int) num * n;
            buckets[idx].add(num);
        }
        for (List<Float> bucket : buckets) {
            Collections.sort(bucket);
        }
        int index = 0;
        for (List<Float> bucket : buckets) {
            for (float num : bucket) {
                nums[index++] = num;
            }
        }
    }

    /**
     * 桶排序-内置打印
     *
     * @param nums
     */
    private static void print(float[] nums) {
        System.out.println("桶排序-内置打印");
        System.out.print("\t");
        for (float num : nums) {
            System.out.print(num + " ");
        }
    }

}
