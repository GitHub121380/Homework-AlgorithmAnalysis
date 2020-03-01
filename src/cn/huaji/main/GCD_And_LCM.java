package cn.huaji.main;

import java.util.*;

/**
 * @ClassName GCD_And_LCM
 * @Description 最大公约数和最小公倍数  最小公倍数 = a / gcd(a,b) * b
 * @author 滑技工厂 https://blog.csdn.net/qq_41718454
 * @date 2020/3/1
 * @version 1.0
 */
public class GCD_And_LCM {
    /*
     * @Title gcd
     * @Description 辗转相除法
     * @author 滑技工厂
     * @Date 2020/3/1
     * @param [m, n]
     * @return int
     * @throws
     */
    public static int gcd(int m, int n) {
        return m % n == 0 ? n : gcd(n, m % n);
    }

    /*
     * @Title gcd2
     * @Description 更相减损法
     * @author 滑技工厂
     * @Date 2020/3/1
     * @param [m, n]
     * @return int
     * @throws
     */
    public static int gcd2(int m, int n) {
        return m - n == 0 ? n : gcd2(n, m - n);
    }



    /*
     * @Title gcd3
     * @Description 连续整数检测算法
     * @author 滑技工厂
     * @Date 2020/3/1
     * @param [m, n]
     * @return int
     * @throws
     */
    public static int gcd3(int m, int n) {

        int gcd = 1;
        if (m == n) {
            return n;
        } else {
            int min = m > n ? n : m;
            for (int i = min; i > 1; i--) {
                if (n % i == 0 && m % i == 0) {
                    return i;
                }

            }
            return gcd;
        }
    }

    /*
     * @Title gcd4
     * @Description 分解质因数然后找所有公因数求积
     * @author 滑技工厂
     * @Date 2020/3/1
     * @param [m, n]
     * @return int
     * @throws
     */
    public static int gcd4(int m, int n) {
        if (m == n) {
            return n;
        } else {
            //m的因数
            List<Integer> _first = new ArrayList<>();
            //n的因数
            List<Integer> _second = new ArrayList<>();

            //m分解质因数
            for (int i = 2; i <= m; i++) {

                while (m % i == 0 && m != 0) {
                    m /= i;
                    _first.add(i);
                }
                if (m == i) {
                    _first.add(i);
                    break;
                }

            }

            //n分解质因数
            for (int i = 2; i <= n; i++) {

                while (n % i == 0 && n != 0) {
                    n /= i;
                    _second.add(i);
                }
                if (n == i) {
                    _second.add(i);
                    break;
                }

            }
            //克隆m的所有因数
            Set<Integer> tmp = new HashSet<>(_first);
            //在m因数中除去m因数与n因数中共有部分 即公因数
            tmp.removeAll(_second);
            //克隆m所有因数
            Set<Integer> exist = new HashSet<>(_first);
            //除去剩余的tmp，剩余exist即为所有公因数list
            exist.removeAll(tmp);

            int gcd = 1;
            for (Integer i :exist) {
                gcd*=i;
            }

            return gcd;
        }
    }


}
