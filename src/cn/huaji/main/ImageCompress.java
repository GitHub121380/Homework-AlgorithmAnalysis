package cn.huaji.main;

/**
 * @ClassName ImageCompress
 * @Description 图像压缩问题
 * @author 滑技工厂 https://blog.csdn.net/qq_41718454
 * @date 2020/5/23
 * @version 1.0
 */
public class ImageCompress {

    public static final int N = 7;
    public static int m;
    /*
     * @Title compressImg
     * @Description 分段
     * @author 滑技工厂
     * @Date 2020/5/23
     * @param [n, image, s, l, b]
     * @return void
     * @throws
     */
    public static void compressImg(int n, int[] image, int[] s, int[] l, int[] b) {

        int Lmax = 256;
        int header = 11;
        s[0] = 0;
        //i为子问题后边界
        for (int i = 1; i <= n; i++) {
            //像素点p[i]所需的存储位数
            b[i] = length(image[i]);
            int Bmax = b[i];
            s[i] = s[i - 1] + Bmax;
            l[i] = 1;

            //最后段长j
            for (int j = 2; j <= i && j < Lmax; j++) {

                if (Bmax < b[i - j + 1]) {
                    Bmax = b[i - j + 1];
                }
                if (s[i] > s[i - j] + j * Bmax) {
                    //找到更好的分段
                    s[i] = s[i - j] + j * Bmax;
                    l[i] = j;
                }

            }

            s[i] += header;
        }

    }

    /*
     * @Title length
     * @Description 求i的存储位数
     * @author 滑技工厂
     * @Date 2020/5/23
     * @param [i]
     * @return int  存储位数
     * @throws
     */
    public static int length(int i) {
        int k = 1;
        i = i / 2;
        while (i > 0) {
            k++;
            i = i / 2;
        }
        return k;
    }

    /*
     * @Title trackBack
     * @Description 追溯
     * @author 滑技工厂
     * @Date 2020/5/23
     * @param [n, i, s, l]
     * @return void
     * @throws
     */
    public static void trackBack(int n, int[] s, int[] l) {
        if (n == 0)
            return;
        trackBack(n - l[n], s, l);
        s[m++] = n - l[n];
    }

    /*
     * @Title output
     * @Description 输出
     * @author 滑技工厂
     * @Date 2020/5/23
     * @param [s, l, b, n]
     * @return void
     * @throws
     */
    public static void output(int[] s, int[] l, int[] b, int n) {

        System.out.println("图像压缩后的最小空间为：" + s[n]);

        m = 0;
        trackBack(n,  s, l);
        s[m] = n;
        System.out.println("将原灰度序列分成" + m + "段序列段");
        for (int j = 1; j <= m; j++) {
            l[j] = l[s[j]];
            b[j] = b[s[j]];
        }
        for (int j = 1; j <= m; j++) {
            System.out.println("段长度：" + l[j] + ",所需存储位数：" + b[j]);
        }

    }

    public static void main(String[] args) {
        //图像灰度数组，我们从1开始
        int[] p = {0, 10, 12, 25, 255, 1, 2};
        int[] s = new int[N];
        int[] l = new int[N];
        int[] b = new int[N];
        System.out.println("图像的序列为：");
        for (int i = 1; i < N; i++) {
            System.out.print(p[i] + " ");
        }

        System.out.println();
        compressImg(N - 1, p, s, l, b);
        output(s, l, b, N - 1);

    }

}
