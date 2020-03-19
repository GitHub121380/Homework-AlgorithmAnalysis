package cn.huaji.main;

/**
 * @ClassName QuickSort
 * @Description 快速排序
 * @author 滑技工厂 https://blog.csdn.net/qq_41718454
 * @date 2020/3/17
 * @version 1.0
 */
public class QuickSort {

    /*
     * @Title quicksort 递归快排
     * @Description partation操作每次只操作一个 递归去循环
     * @author 滑技工厂
     * @Date 2020/3/17
     * @param [arr, L, R]
     * @return void
     * @throws
     */
    public static void quicksort(int[] arr, int L, int R) {
        if (L<R) {
            swap(arr, L+(int)(Math.random()*(R-L+1)), R);
            int[] temp = partation(arr, L, R);
            quicksort(arr, L, temp[0]-1);
            quicksort(arr, temp[1]+1, R);
        }
    }

    /*
     * @Title partation
     * @Description 把数组分为三部分并且返回等于部分的下标
     * @author 滑技工厂
     * @Date 2020/3/17
     * @param [arr, L初试范围+指针, R]
     * @return int[]
     * @throws
     */
    public static int[] partation(int[] arr , int L, int R){
        int less = L - 1; //小于范围
        int more = R;     //大于范围 把最后一位不放在遍历里面
        while (L<more) {
            if (arr[L]<arr[R]) {           //若指针数小于最后一位 就交换小于范围的下一个来扩大小于范围 然后指针后移一位
                swap(arr, L++, ++less);
            } else if (arr[L]>arr[R]) {    //若指针数大于最后一位 就交换大于范围的前一个来扩大大于范围 指针不变 经历下次循环
                swap(arr, --more, L);
            } else {                       //若指针数相等 就把指针后移一位
                L++;
            }
        }
        swap(arr, more++, R);			   //因为最后一位数作为比较数 一直没有动 所以把最后一位与大于范围第一位交换 大于范围下标后移
        return new int[]{less+1 , more-1};   //返回等于范围的下标
    }

    public static void swap(int[] arr , int i , int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
