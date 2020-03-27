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


    /*
     * @Title select
     * @Description 利用中位数线性时间选择
     * @author 滑技工厂
     * @Date 2020/3/27
     * @param [a, l, r, k]
     * @return int
     * @throws
     */
    public static int select(int[] a, int l, int r, int k) {
        if (r - l < 75) {
            insertSort(a, l, r);    //用插入排序进行排序
            return a[l + k - 1];
        }
        int group = (r - l + 5) / 5;
        for (int i = 0; i < group; i++) {
            int left = l + 5 * i;
            int right = (l + i * 5 + 4) > r ? r : l + i * 5 + 4;  //如果超出右边界就用右边界赋值
            int mid = (left + right) / 2;
            insertSort(a, left, right);
            swap(a, l + i, mid);     // 将各组中位数与前i个
        }
        int pivot = select(a, l, l + group - 1, (group + 1) / 2);  //找出中位数的中位数
        int p = partition(a, l, r, pivot);    //用中位数的中位数作为基准的位置
        int j = p - l + 1;       //leftNum用来记录基准位置的前边的元素个数
        if (k == j)
            return a[p];
        else if (k < j)
            return select(a, l, p - 1, k);
        else                    //若k在基准位子的后边，则要从基准位置的后边数起，即第（k - leftNum - 1）个
            return select(a, p + 1, r, k - j - 1);
    }
    //适用于线性时间选择的partition方法
    public static int partition(int[] a, int l, int r, int pivot) {
        int i = l;
        int j = r;
        while (true) {
            while (a[i] <= pivot && i < r)
                ++i;   //i一直向后移动，直到出现a[i]>pivot
            while (a[j] > pivot)
                --j;   //j一直向前移动，直到出现a[j]<pivot
            if (i >= j) break;
            swap(a, i, j);
        }
        a[l] = a[j];
        a[j] = pivot;
        return j;
    }
    //插入排序
    public static void insertSort(int[] a, int law, int high) {
        for (int i = law + 1; i <= high; i++) {
            int key = a[i];
            int j = i - 1;
            while (j >= law && a[j] > key) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = key;
        }
    }


    public static void main(String[] args) {
        int[] a = {1, 58, 8, 3, 4, 8, 13, 86, 32};
        System.out.println(select(a, 0, a.length - 1, 6));
    }
}
