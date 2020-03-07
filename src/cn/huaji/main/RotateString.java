package cn.huaji.main;

/**
 * @ClassName RotateString
 * @Description 字符串移位包含问题
 *                 给定两个字符串s1和s2，要求判断s2是否能够被通过s1做循环移位（rotate）得到的字符串包含。
 *                 例如，S1=AABCD和s2=CDAA，返回true；给定s1=ABCD和s2=ACBD，返回false。
 * @author 滑技工厂 https://blog.csdn.net/qq_41718454
 * @date 2020/3/7
 * @version 1.0
 */
public class RotateString {
    /*
     * @Title rotate
     * @Description 方法
     * @author 滑技工厂
     * @Date 2020/3/7
     * @param [s1, s2]
     * @return boolean
     * @throws
     */
    public static boolean rotate(String s1, String s2) {
        //创造一个s1，从不同的位置就是各个循环位移后的串
        s1 += s1;
        //比较
        if (s1.contains(s2))
            return true;
        else
            return false;

    }

    public static void main(String[] args) {
        System.out.println(rotate("AABCD", "CDAA"));
    }
}
