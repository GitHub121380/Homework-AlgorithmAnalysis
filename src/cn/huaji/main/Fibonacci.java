package cn.huaji.main;

/**
 * @ClassName Fibonacci
 * @Description 斐波那契数列
 * @author 滑技工厂 https://blog.csdn.net/qq_41718454
 * @date 2020/3/1
 * @version 1.0
 */
public class Fibonacci {

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
}
