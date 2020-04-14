package cn.huaji.main;

/**
 * @ClassName Fibonacci
 * @Description 斐波那契数列
 * @author 滑技工厂 https://blog.csdn.net/qq_41718454
 * @date 2020/3/1
 * @version 1.0
 */
public class Fibonacci {

    static int[] dp = new int[1000];

    /*
     * @Title fibonacci
     * @Description 斐波那契f(n)
     * @author 滑技工厂
     * @Date 2020/3/1
     * @param [n]
     * @return int
     * @throws
     */
    public static int fibonacci(int n) {
        if (n < 0)
            return -1;
        else if (n == 0)
            return 0;
        else if (n == 1)
            return 1;
        else
            return fibonacci(n - 1) + fibonacci(n - 2);
    }

    /*
     * @Title fibonaccidp1
     * @Description 自底向上的动态规划斐波那契
     * @author 滑技工厂
     * @Date 2020/4/14
     * @param [n]
     * @return int
     * @throws
     */
    public static int fibonaccidp1(int n) {
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /*
     * @Title fibonaccidp2
     * @Description 自顶向下的动态规划斐波那契
     * @author 滑技工厂
     * @Date 2020/4/14
     * @param [n]
     * @return int
     * @throws
     */
    public static int fibonaccidp2(int n) {
        //用来取已经记忆化的子问题结果
        if (dp[n] != 0)
            return dp[n];
        if (n == 0 || n == 1)
            return dp[0] = dp[1] = 1;
        if (n > 1)//计算dp[n]
            dp[n] = fibonaccidp2(n-1) + fibonaccidp2(n - 2);
        return dp[n];
    }

}
