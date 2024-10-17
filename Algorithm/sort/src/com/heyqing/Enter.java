package com.heyqing;

/**
 * ClassName:Enter
 * Package:com.heyqing
 * Description:
 *
 * @Date:2024/10/16
 * @Author:Heyqing
 */
public class Enter {

    private static int[] source = new int[]{3, 2, 4, 7, 1, 6, 4, 5};
    private static float[] bucket = new float[]{0.42f, 0.32f, 0.59f, 0.26f, 0.77f, 0.05f};

    public static void main(String[] args) {
        System.out.println("程序测试入口");
//        BubbleSort.sort(source);
//        SelectionSort.sort(source);
//        InsertionSort.sort(source);
//        QuickSort.sortV1(source);
//        QuickSort.sortV2(source);
//        QuickSort.sortV3(source);
//        MergeSort.sort(source);
//        HeapSort.sort(source);
//        ShellSort.sort(source);
//        CountingSort.sort(source);
//        RadixSort.sort(source);
        BucketSort.sort(bucket);
//        print();
    }

    private static void print() {
        for (int i : source) {
            System.out.print(i + " ");
        }
    }

}
