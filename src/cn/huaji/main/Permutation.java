package cn.huaji.main;

import java.util.Arrays;

/**
 * @ClassName Permutation
 * @Description 全排列类
 * @author 滑技工厂 https://blog.csdn.net/qq_41718454
 * @date 2020/2/27
 * @version 1.0
 */
public class Permutation {

    public static void swap(char[] chars, int i, int j) {
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
    }

    /*
     * @Title allPermutation
     * @Description chars的全排列
     * @author 滑技工厂
     * @Date 2020/2/27
     * @param [chars, cursor, end]
     * @return void
     * @throws
     */
    public static void allPermutation(char[] chars, int cursor, int end) {
        if (cursor == end) {//cursor到数组最末
            System.out.println(Arrays.toString(chars));
        }

        for (int i = cursor; i <= end; i++) {
            if (!swapAccepted(chars, cursor,i))
                continue;

            //选一个元素放入已排列
            swap(chars, cursor, i);
            //指针后移，cursor之前的都是已经确定好的元素，+1来确定下一个位置的字母
            allPermutation(chars, cursor + 1, end);
            //用来把之前交换的两个值换回来
            swap(chars, cursor, i);
        }

    }

    public static boolean swapAccepted(char[] array, int start, int end) {
        for (int i = start; i < end; i++) {
            if (array[i] == array[end]) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {

        char[] chars = {'a', 'b', 'c', 'c'};
        allPermutation(chars, 0, chars.length - 1);

    }
}
