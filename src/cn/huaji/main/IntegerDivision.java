package cn.huaji.main;

/**
 * @ClassName IntegerDivision
 * @Description 整数划分问题
 * @author 滑技工厂 https://blog.csdn.net/qq_41718454
 * @date 2020/3/8
 * @version 1.0
 */
public class IntegerDivision {
    /*
     * @Title division
     * @Description 整数划分方法
     * @author 滑技工厂
     * @Date 2020/3/8
     * @param [n, m]   n->要划分的整数  m->最大加数
     * @return int 输出有多少种划分
     * @throws
     */
    public static int division(int n, int m) {
        if (n < 1 || m < 1)
            return 0;
        if (n == 1 || m == 1)
            return 1;
        //最大加数如果大于n 则另最大加数为n
        if (n < m)
            return division(n, n);
        //m=n，则从
        if (n == m)
            return division(n, m - 1) + 1;

        return division(n, m - 1) + division(n - m, m);

    }


    static int mark[] = new int[100];//记录分解情况
    static int n;
    /*
     * @Title divide
     * @Description 输出划分
     * @author 滑技工厂
     * @Date 2020/3/8
     * @param [now, k, pre]
     * @return void
     * @throws
     */
    public static void divide(int now, int k, int pre) {
        int i;
        //数组长度大于n就返回
        if (now > n) return;

        if (now == n) {

            System.out.printf("%d=", n);
            for (i = 0; i < k - 1; i++) {
                System.out.printf("%d+", mark[i]);
            }
            System.out.printf("%d\n", mark[i]);
        } else {
            for (i = pre; i > 0; i--) {
                if (i <= pre) {
                    mark[k] = i;
                    now += i;
                    divide(now, k + 1, i);
                    now -= i;
                }
            }
        }
    }


    public static void main(String[] args) {
        System.out.println(division(6, 6));
        n = 6;
        divide(0, 0, n);

    }

}
