package cn.huaji.main;

import java.util.Stack;

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
        if (L < R) {
            swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
            int[] temp = partation(arr, L, R);
            quicksort(arr, L, temp[0] - 1);
            quicksort(arr, temp[1] + 1, R);
        }
    }

    /*
     * @Title partation
     * @Description 把数组分为三部分并且返回等于部分的下标 改进的
     * @author 滑技工厂
     * @Date 2020/3/17
     * @param [arr, L初试范围+指针, R]
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


    /*
     * @Title generQuickSort
     * @Description 非递归快排
     * @author 滑技工厂
     * @Date 2020/3/20
     * @param [arr, left, right]
     * @return int[]
     * @throws
     */
    public static int[] generQuickSort(int[] arr, int left, int right) {
        //定义一个栈
        Stack<Integer> stack = new Stack<>();
        //前提条件就是left < right
        if (left < right) {
            stack.push(left);  //将left压进栈中
            stack.push(right);  //将right压进栈中
            while (!stack.isEmpty()) {  //当栈不为空时，进行以后的操作
                int height = stack.pop();  //取出栈中第一位，我最后压进的是right，所以它是最末位的下标；
                int low = stack.pop();  //最底下的是left的下标。到底哪个对应那个，要看你是怎么压进去的

                int index = getTargetIndex(arr, low, height);  //得到基准数的下标
                if (index - 1 > low) {   //判断基准数左边的数的下标与开始下标的大小
                    stack.push(low);    //根据情况压进去以基准数分开的下标的左半边部分的低位
                    stack.push(index - 1);   //根据情况压进去以基准数分开的下标的左半边部分的高位
                }

                if (index + 1 < height) {  //判断基准数右边的数的下标与开始下标的大小
                    stack.push(index + 1);   //根据情况压进去以基准数分开的下标的右半边部分的低位
                    stack.push(height);   //根据情况压进去以基准数分开的下标的右半边部分的高位
                }
            }
        }
        return arr;
    }

    /*
     * @Title getTargetIndex
     * @Description 用于获取每一次基准数下表的函数
     * @author 滑技工厂
     * @Date 2020/3/20
     * @param [arr, left, right]
     * @return int
     * @throws
     */
    public static int getTargetIndex(int[] arr, int left, int right) {
        //这个检验是为了保证程序的健壮性
        if (left >= right || arr == null) {
            return -1;
        }

        //key为基准数，其它数都要与这个key比较
        int key = arr[left];
        while (left < right) {    //为什么使用while？因为我们要循环的操作不止一次
            while (key < arr[right] && left < right) //当末尾数大于基准数时的情况
                right--;   //当最右边的数大于基准数时，末尾下标进行--操作,while的作用域只有这一行
            arr[left] = arr[right];   //当最右边的数小于基准数时，要做的操作

            while (key >= arr[left] && left < right) //当末尾数小于基准数时的情况，这里包含了相等的情况
                left++;    //当最右边的数小于基准数时，开始下标进行++操作,while的作用域只有这一行
            arr[right] = arr[left];   //当最左边的数大于基准数时，要做的操作
        }
        arr[left] = key;  //当所有下标都遍历完了以后，将基准数放在left下标位置
        System.out.println(left + "***");
        return left;     //返回基准数的下标
    }


    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
