package cn.huaji.main;

/**
 * @ClassName LinearSelect
 * @Description 线性时间选择 获取第k小的元素
 * @author 滑技工厂 https://blog.csdn.net/qq_41718454
 * @date 2020/3/26
 * @version 1.0
 */
public class LinearSelect {
    /*
     * @Title randomizedSelect
     * @Description 基于随机元素为基准的线性时间选择
     * @author 滑技工厂
     * @Date 2020/3/26
     * @param [a, L, R, k -> L---R中的第k小]
     * @return int
     * @throws
     */
    public static int randomizedSelect(int[] a, int L, int R, int k) {
        if (L == R)
            return a[L];
        //获取为基准的随机元素的下标
        int i = randomizedPartition(a, L, R);
        //j为划分后左序列到基准（包含基准）的元素个数
        int j = i - L + 1;
        if (k < j)//如果k小于j，说明在基准i的左边
            return randomizedSelect(a, L, i - 1, k);
        else if (k == j)//
            return a[i];
        else//k大于j 说明在i的右边序列
            return randomizedSelect(a, i + 1, R, k - j);

    }

    /*
     * @Title randomizedPartition
     * @Description 随机划分
     * @author 滑技工厂
     * @Date 2020/3/26
     * @param [a, L, R]
     * @return int
     * @throws
     */
    public static int randomizedPartition(int[] a, int L, int R) {
        //获取L---R的随机数
        int i = L + (int) (Math.random() * (R - L + 1));
        //把随机数换到
        swap(a, L, i);
        int pivot = a[L];
        while (L < R) {
            while (L < R && a[R] >= pivot)
                R--;
            if (L < R)
                a[L++] = a[R];
            while (L < R && a[L] <= pivot)
                L++;
            if (L < R)
                a[R--] = a[L];
        }
        a[L] = pivot;
        return L;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] a = {1,58,8,3,4,8,13,86,32};
        System.out.println(randomizedSelect(a,0,a.length-1,6));
    }
}
