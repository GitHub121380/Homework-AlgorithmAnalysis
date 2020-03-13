package cn.huaji.main;

/**
 * @ClassName SearchNumbers
 * @Description 寻找一个数组中最大数、最小数、第二大数、第k小数
 *              我着实没看懂这道题 是一个方法实现还是分别几个方法，我就按照后者吧
 *              假设数组都是>=0的
 * @author 滑技工厂 https://blog.csdn.net/qq_41718454
 * @date 2020/3/13
 * @version 1.0
 */

public class SearchNumbers {

    /*
     * @Title max
     * @Description 求最大值 遍历 时间复杂度n
     * @author 滑技工厂
     * @Date 2020/3/13
     * @param [arr]
     * @return int
     * @throws
     */
    public static int max(int[] arr) {
        int max = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max)
                max = arr[i];
        }
        return max;
    }

    /*
     * @Title min
     * @Description 寻找最小值  遍历 时间复杂度n
     * @author 滑技工厂
     * @Date 2020/3/13
     * @param [arr]
     * @return int
     * @throws
     */
    public static int min(int[] arr) {
        int min = 999999999;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min)
                min = arr[i];
        }
        return min;
    }

    /*
     * @Title secondMax
     * @Description 求第二大的数 时间复杂度n
     * @author 滑技工厂
     * @Date 2020/3/13
     * @param [arr]
     * @return int
     * @throws
     */
    public static int secondMax(int[] arr) {
        int max = -1;
        int secondMax = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < max) {
                secondMax = max;
                max = arr[i];
                continue;
            }
            if (arr[i] < max && arr[i] > secondMax)
                secondMax = arr[i];

        }
        return secondMax;
    }

    /*
     * @Title kthMin
     * @Description 求第k小数，这里用学上面直接遍历就不方便了 用一个排序算法然后通过下标比较方便
     *                 所以这里就是比排序算法的时间复杂度
     * @author 滑技工厂
     * @Date 2020/3/13
     * @param [arr, k]
     * @return int
     * @throws
     */
    public static int kthMin(int arr[], int k) {
        quicksort(arr, 0, arr.length - 1);
        return arr[k];
    }

    /*
     * @Title quicksort
     * @Description 快排
     * @author 滑技工厂
     * @Date 2020/3/13
     * @param [arr, L, R]
     * @return void
     * @throws
     */
    public static void quicksort(int[] arr, int L, int R) {
        if (L < R) {
            swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
            int[] temp = partation(arr, L, R);
            quicksort(arr, L, temp[0] - 1);
            quicksort(arr, temp[1] + 1, R);
        }
    }

    /*
     * @Title partation
     * @Description 把数组分为三部分并且返回等于部分的下标
     * @author 滑技工厂
     * @Date 2020/3/13
     * @param [arr, L, R]
     * @return int[]
     * @throws
     */
    public static int[] partation(int[] arr, int L, int R) {
        int less = L - 1; //小于范围
        int more = R;     //大于范围 把最后一位不放在遍历里面
        while (L < more) {
            if (arr[L] < arr[R]) {           //若指针数小于最后一位 就交换小于范围的下一个来扩大小于范围 然后指针后移一位
                swap(arr, L++, ++less);
            } else if (arr[L] > arr[R]) {    //若指针数大于最后一位 就交换大于范围的前一个来扩大大于范围 指针不变 经历下次循环
                swap(arr, --more, L);
            } else {                       //若指针数相等 就把指针后移一位
                L++;
            }
        }
        swap(arr, more++, R);               //因为最后一位数作为比较数 一直没有动 所以把最后一位与大于范围第一位交换 大于范围下标后移
        return new int[]{less + 1, more - 1};   //返回等于范围的下标
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {

    }
}
