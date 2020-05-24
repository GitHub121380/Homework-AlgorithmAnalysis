package cn.huaji.main;

import java.util.Arrays;

/**
 * @ClassName Johnson
 * @Description 流水作业调度问题
 *
 * n个作业{0, 1, 2, …, n}在2台机器上M1 和M2 组成的流水线上完成加工。
 * 每个作业加工的顺序都是先在M1 上加工，后在M2 上加工。
 * 在两台机器上加工的时间分别为ai和bi。
 * 确定这n个作业的加工顺序，使得从第一台作业开始加工，到最后一个作业完成加工所需要的时间最少。 
 *
 * @author 滑技工厂 https://blog.csdn.net/qq_41718454
 * @date 2020/5/24
 * @version 1.0
 */
public class Johnson {

    //在M1上的工作时间序列
    static int[] a;
    //在M2上的工作时间序列
    static int[] b;

    static int[] c;

    private static class Element implements Comparable {
        int key;
        int index;
        boolean job;

        public Element(int key, int index, boolean job) {
            this.key = key;
            this.index = index;
            this.job = job;
        }

        @Override//根据key值排序 升序
        public int compareTo(Object o) {

            int keys = ((Element) o).key;
            if (key < keys)
                return -1;
            else if (key == keys)
                return 0;
            else
                return 1;
        }
    }

    public static int flowShop(int[] a, int[] b, int[] c) {
        int n = a.length;
        Element[] el = new Element[n];
        for (int i = 0; i < n; i++) {
            //分别取对应的b[i]或a[i]值作为关键字
            int key = a[i] > b[i] ? b[i] : a[i];
            //给符合条件a[i]<b[i]的放入到N1子集标记为true
            boolean job = (a[i] <= b[i]);
            el[i] = new Element(key, i, job);

        }
        //对数组el按关键字升序进行排序
        Arrays.sort(el);

        int j = 0;
        int k = n - 1;
        for (int i = 0; i < n; i++) {
            if (el[i].job)
                //将排过序的数组d，取其中作业序号属于N1的从前面进入（d[i].index保存的作业序号0，1，2，等等）
                c[j++] = el[i].index;
            else
                //属于N2的从后面进入，从而实现N1的非减序排序，N2的非增序排序
                c[k--] = el[i].index;
        }

        j = a[c[0]];
        k = j + b[c[0]];
        for (int i = 1; i < n; i++) {
            //M1在执行c[i]作业的同时，M2在执行c[i-1]号作业，最短执行时间取决于M1与M2谁后执行完
            j += a[c[i]];
            //计算最优加工时间
            k = j < k ? k + b[c[i]] : j + b[c[i]];
        }
        System.out.println("作业调度的顺序为（编号从0开始）：");
        for (int i = 0; i < c.length; i++) {
            System.out.print(c[i] + " ");
        }
        System.out.println();
        return k;
    }


    public static void main(String[] args) {
        a = new int[]{2, 4, 3, 6, 1};
        b = new int[]{5, 2, 3, 1, 7};
        c = new int[a.length];

        int k = flowShop(a, b, c);
        System.out.println("完成作业的最短时间为:" + k);

    }

}
