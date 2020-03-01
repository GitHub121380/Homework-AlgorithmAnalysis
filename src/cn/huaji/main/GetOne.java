package cn.huaji.main;

/**
 * @ClassName GetOneNumber
 * @Description 二进制中“1”的个数
 * @author 滑技工厂 https://blog.csdn.net/qq_41718454
 * @date 2020/2/27
 * @version 1.0
 */
public class GetOne {

    public static void main(String[] args) {

        System.out.println(count3(15));
    }


    /*
     * @Title count1
     * @Description 除2余1
     * @author 滑技工厂
     * @Date 2020/3/1
     * @param [n]
     * @return int
     * @throws
     */
    public static int count1(int n) {
        int num = 0;
        while (n > 0) {
            if (n % 2 == 1)
                num++;
            n /= 2;

        }
        return num;
    }


    /*
     * @Title count2
     * @Description 与1位运算
     * @author 滑技工厂
     * @Date 2020/3/1
     * @param [n]
     * @return int
     * @throws
     */
    public static int count2(int n) {
        int num = 0;
        while (n > 0) {
            num += (n & 1);
            n = n >> 1;

        }
        return num;
    }

    /*
     * @Title count3
     * @Description 高效位操作
     * @author 滑技工厂
     * @Date 2020/3/1
     * @param [n]
     * @return int
     * @throws
     */
    public static int count3(int n) {
        int num = 0;
        while (n > 0) {
            n = n & (n - 1);
            num++;

        }
        return num;
    }


}
