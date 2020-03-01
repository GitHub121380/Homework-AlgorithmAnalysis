package cn.huaji.main;

/**
 * @ClassName Ackerman
 * @Description Ackerman阿克曼函数
 * @author 滑技工厂 https://blog.csdn.net/qq_41718454
 * @date 2020/3/1
 * @version 1.0
 */
public class Ackerman {

    /*
     * @Title ackerman
     * @Description 根据递推公式求的
     * @author 滑技工厂
     * @Date 2020/3/1
     * @param [m, n]
     * @return long
     * @throws
     */
    public static long ackerman(long m, long n) {
        if (m < 0 || n < 0)
            return -1;
        else if (m == 0)
            return n + 1;
        else if (m > 0 && n == 0)
            return ackerman(m - 1, 1);
        else  //m>0 and n>0
            return ackerman(m - 1, ackerman(m, n - 1));
    }


    /*
     * @Title ackerman2
     * @Description 简化后的阿克曼函数 因为m取到3这个数就很大 我们假设m只取0~3
     * @author 滑技工厂
     * @Date 2020/3/1
     * @param [m, n]
     * @return long
     * @throws
     */
    public static long ackerman2(long m, long n) {
        if (n == 0)
            return ackerman(m - 1, 1);
        else if (m == 0)
            return n + 2;
        else if (m == 1)
            return n + 2;
        else if (m == 2)
            return 2 * n + 3;
        else if (m == 3)
            return ackerman(m, n - 1) * 2 + 3;
        return -1;
    }

}
