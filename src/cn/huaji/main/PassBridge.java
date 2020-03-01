package cn.huaji.main;

import java.util.Vector;

/**
 * @ClassName PassBridge
 * @Description 最快过桥问题
 * @author 滑技工厂 https://blog.csdn.net/qq_41718454
 * @date 2020/3/1
 * @version 1.0
 */
public class PassBridge {

    public static Integer travelBridge(Vector<Integer> times) {
        int length = times.size();
        if (length <= 2) {
            return times.get(length - 1);
        } else if (length == 3) {
            return times.get(0) + times.get(1) + times.get(2);
        } else {

            Integer a = times.get(0);
            Integer b = times.get(1);
            Integer c = times.get(length - 2);
            Integer d = times.get(length - 1);

            if (2 * b < a + c) {//最小带最大慢，采用先ab后cd模式二
                times.remove(times.lastElement());
                times.remove(times.lastElement());
                return b + a + d + b + travelBridge(times);
            } else {//采用模式一
                times.remove(times.lastElement());
                return d + a + travelBridge(times);
            }
        }
    }

    public static void main(String[] args) {
        Vector<Integer> times = new Vector<>();
        times.add(1);
        times.add(2);
        times.add(5);
        times.add(10);
        int total = travelBridge(times);
        System.out.println(total);
    }

}
