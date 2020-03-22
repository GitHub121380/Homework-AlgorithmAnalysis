package cn.huaji.main;

import java.util.Arrays;

/**
 * @ClassName MergeSort
 * @Description 归并排序
 * @author 滑技工厂 https://blog.csdn.net/qq_41718454
 * @date 2020/3/17
 * @version 1.0
 */
public class MergeSort {
    /*
     * @Title mergeSort
     * @Description 递归归并排序
     * @author 滑技工厂
     * @Date 2020/3/17
     * @param [a, left 左指针, right 右指针]
     * @return void
     * @throws
     */
    public static void mergeSort(int a[], int left, int right) {

        if (left < right) {//划分的持续条件
            //找到中间指针
            int i = (left + right) / 2;
            //一个序列分为左右两边，分别进行mergesort，然后还会继续划分，
            // 直到形成一个元素一个序列的情况，就可以序列之间排序merge了
            mergeSort(a, left, i);
            mergeSort(a, i + 1, right);
            //序列排序并合并到b,left、i、right来进行划分左右序列
            int[] b = new int[right - left + 1];
            merge(a, b, left, i, right);
            copy(a, b, left, right);

        }

    }

    public static void merge(int[] a, int[] b, int left, int i, int rigth) {
        //表示左序列指针 left--i  left为起始位置
        int x = left;
        //表示右序列指针 i+1--right  i+1
        int y = i + 1;
        //表示b的指针
        int z = 0;
        //两个指针都要在范围内
        while (x <= i && y <= rigth) {
            //谁小谁在b内排前面，同时指针后移
            if (a[x] < a[y])
                b[z++] = a[x++];
            else
                b[z++] = a[y++];

        }
        //左序列还剩余数据，右序列已经全放入b
        while (x <= i)
            b[z++] = a[x++];
        //右序列还剩余数据，左序列已全部放入
        while (y <= rigth)
            b[z++] = a[y++];
    }

    /*
     * @Title copy
     * @Description 复制
     * @author 滑技工厂
     * @Date 2020/3/17
     * @param [a, b, m, n]
     * @return void
     * @throws
     */
    public static void copy(int[] a, int[] b, int left, int right) {
        for (int i = left, j = 0; i <= right; i++, j++) {
            a[i] = b[j];
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    //归并排序的非递归
    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length <= 0)
            return;
        int width = 1;
        while (width < arr.length) {
            mergePass(arr, width);
            width *= 2;
        }
    }

    private static void mergePass(int[] arr, int width) {
        int start = 0;
        while (start + 2 * width - 1 < arr.length) {
            int mid = start + width - 1;
            int end = start + 2 * width - 1;
            merge2(arr, start, mid, end);
            start = start + 2 * width;
        }
        //剩余无法构成完整的两组也要进行处理
        if (start + width - 1 < arr.length)
            merge2(arr, start, start + width - 1, arr.length - 1);
    }

    private static void merge2(int[] arr, int start, int mid, int end) {
        int i = start;
        int j = mid + 1;
        int[] temp = new int[end - start + 1];
        int index = 0;
        while (i <= mid && j <= end) {
            if (arr[i] <= arr[j])
                temp[index++] = arr[i++];
            else
                temp[index++] = arr[j++];
        }
        while (i <= mid)
            temp[index++] = arr[i++];
        while (j <= end)
            temp[index++] = arr[j++];

        for (int k = start; k <= end; k++)
            arr[k] = temp[k - start];
    }

    ///////////////////////////////////////////////////////////////////////////////
    //自然归并排序

    //判断数组有序子序段的长度
    int indexLen(int j, int[] a) {

        int k = j + 1;
        int len = 1;
        for (; ; ) {
            if (k >= a.length)
                break;
            if (a[j] < a[k]) {
                len++;
                j++;
                k++;
            } else break;
        }
        return len;
    }

    int index = 0;

    public void Printf(int a[]) {
        index = indexLen(index, a);
    }

    public void mergeSort(int[] a) {
        int len = 0;
        while (indexLen(0, a) < a.length) {
            for (int i = 0, k1 = 0, k2 = 0; i < a.length; ) {
                k1 = indexLen(i, a);
                k2 = indexLen(k1, a);
                merge(a, i, k1, k2);
                i = i + k1 + k2;
                System.out.println("相邻两组合并排序以后:" + Arrays.toString(a));
            }
        }
    }

    public void merge(int[] a, int i, int len1, int len2) {
        int start = i;
        int X = i + len1;// 归并的前半部分数组
        int j = i + len1;
        int Y = j + len2;// 归并的后半部分数组
        int[] temp = new int[len1 + len2];
        int count = 0;
        while (i < X && j < Y && j < a.length) {
            if (a[i] <= a[j]) {
                temp[count++] = a[i++];
            } else {
                temp[count++] = a[j++];
            }
        }
        while (i < X && i < a.length) {
            temp[count++] = a[i++];
        }
        while (j < Y && j < a.length) {
            temp[count++] = a[j++];
        }
        count = 0;
        while (start < j && start < a.length) {
            a[start++] = temp[count++];
        }
    }

    //////////////////////////////////////////////////////
    /*
     * @Title print
     * @Description 打印
     * @author 滑技工厂
     * @Date 2020/3/21
     * @param [a]
     * @return void
     * @throws
     */
    public static void print(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }


    public static void main(String[] args) {
        int[] a = {49, 38, 65, 97, 76, 12, 27};
        mergeSort(a, 0, 6);
        print(a);
    }
}
