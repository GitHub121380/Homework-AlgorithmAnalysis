package cn.huaji.main;

/**
 * @ClassName BigInteger
 * @Description 大整数操作
 * @author 滑技工厂 https://blog.csdn.net/qq_41718454
 * @date 2020/3/14
 * @version 1.0
 */
public class BigInteger {
    /*
     * @Title SIGN
     * @Description 返回A的符号
     * @author 滑技工厂
     * @Date 2020/3/14
     * @param [A]
     * @return int  符号 +1/-1
     * @throws
     */
    public static int SIGN(long A) {
        return A > 0 ? 1 : -1;
    }
    /*
     * @Title multiply
     * @Description 大整数乘法，减少时间复杂度
     * @author 滑技工厂
     * @Date 2020/3/14
     * @param [X, Y, n]
     * @return long
     * @throws
     */
    public static long multiply(long X, long Y, int n) {

        int sign = SIGN(X) * SIGN(Y);

        X = Math.abs(X);
        Y = Math.abs(Y);

        if (n == 1) {
            return sign * X * Y;
        } else {
            long A = (long) (X / Math.pow(10, n / 2));
            long B = (long) (X % Math.pow(10, n / 2));
            long C = (long) (Y / Math.pow(10, n / 2));
            long D = (long) (Y % Math.pow(10, n / 2));

            long AC = multiply(A, C, n / 2);
            long BD = multiply(B, D, n / 2);
            long ABCD = multiply(A - B, D - C, n / 2);

            return (long) (AC * Math.pow(10, n) + (AC + BD + ABCD) * Math.pow(10, n / 2) + BD)*sign;

        }
    }

}
