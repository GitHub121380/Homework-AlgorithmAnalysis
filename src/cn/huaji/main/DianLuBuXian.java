package cn.huaji.main;

/**
 * @ClassName DianLuBuXian
 * @Description 电路布线问题
 *
 * 在一块电路板的上、下两端分别有n个接线柱。根据电路设计，
 * 要求用导线(i,π(i)) 将上端接线柱i与下端接线柱π(i)相连，
 * 如下图。其中，π(i),1≤ i ≤n,是｛1,2,…,n｝的一个排列。
 * 导线(I, π(i))称为该电路板上的第i条连线。对于任何1 ≤ i ≤ j ≤n,
 * 第i条连线和第j条连线相交的充要条件是π(i)> π(j).
 *
 * @author 滑技工厂 https://blog.csdn.net/qq_41718454
 * @date 2020/5/24
 * @version 1.0
 */
public class DianLuBuXian {

    static int[] c;
    static int[][] size;
    static int[] net;

    public static void mnset(int[] c, int[][] size) {

        int n = c.length - 1;
        for (int j = 1; j < c[1]; j++) {
            size[1][j] = 0;
        }
        for (int j = c[1]; j <= n; j++) {
            size[1][j] = 1;
        }
        for (int i = 2; i < n; i++) {

            for (int j = 0; j < c[i]; j++) {

                size[i][j] = size[i - 1][j];

            }
            for (int j = c[i]; j <= n; j++) {
                size[i][j] = Math.max(size[i - 1][j], size[i - 1][c[i] - 1] + 1);
            }


        }
        size[n][n] = Math.max(size[n - 1][n], size[n - 1][c[n - 1] - 1] + 1);

    }

    public static int traceback(int[] c, int[][] size, int[] net) {
        int n = c.length - 1;
        int j = n;
        int m = 0;
        for (int i = n; i > 1; i--) {

            if (size[i][j] != size[i - 1][j]) {
                net[m++] = i;
                j = c[i] - 1;
            }

        }

        if (j >= c[1])
            net[m++] = 1;
        System.out.println("最大不相交连线分别为：");
        for (int t = m - 1; t >= 0; t--) {

            System.out.println(net[t] + " " + c[net[t]]);

        }
        return m;
    }

    public static void main(String[] args) {

        c = new int[]{0, 8, 7, 4, 2, 5, 1, 9, 3, 10, 6};
        size = new int[c.length][c.length];//下标从1开始
        net = new int[c.length];

        mnset(c, size);
        int x = traceback(c, size, net);
        System.out.println("最大不相交连线数目为：" + x);

    }
}
