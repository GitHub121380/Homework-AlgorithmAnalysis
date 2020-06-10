package cn.huaji.main;

import java.util.Arrays;

/**
 * @ClassName NormalPackageProblem
 * @Description 普通背包问题
 *
 * 比较单价 优先选单价大的
 *
 * @author 滑技工厂 https://blog.csdn.net/qq_41718454
 * @date 2020/6/10
 * @version 1.0
 */
public class NormalPackageProblem {

    /*
     * @Title normalPackage
     * @Description 普通背包方法
     * @author 滑技工厂
     * @Date 2020/6/10
     * @param [huoWus]
     * @return int
     * @throws
     */
    public static int normalPackage(HuoWu[] huoWus, int W) {
        int sum = 0;
        int n = huoWus.length;

        //遍历货物
        for (int i = 0; i < n; i++) {
            //背包剩余容量完全放得下当前单价最高货物
            if (huoWus[i].weight <= W) {
                //背包容量减少
                W -= huoWus[i].weight;
                //被拿光
                huoWus[i].weight = 0;
                //背包内价值增加
                sum += huoWus[i].value;
            } else {//背包容量不够了 放一部分 这是最后一次放东西
                //缩放价值为仅剩重量*当前货物单价
                sum += huoWus[i].average * W;
                huoWus[i].weight -= W;
                break;
            }

        }

        return sum;
    }


    public static void main(String[] args) {
        int n = 3;
        HuoWu[] huoWus = new HuoWu[n];
        int[] values = {25, 24, 15};
        int[] weights = {18, 15, 10};
        int W = 20;

        for (int i = 0; i < n; i++) {
            huoWus[i] = new HuoWu(weights[i],values[i]);
        }

        Arrays.sort(huoWus);

        int sum = normalPackage(huoWus, W);

        System.out.println(sum);

    }

}

class HuoWu implements Comparable<HuoWu> {

    int weight;
    int value;
    float average;

    public HuoWu(int weight,int value) {
        this.weight = weight;
        this.value = value;
        this.average = (float) value / (float) weight;
    }

    @Override
    public int compareTo(HuoWu o) {
        //比较单价 单位重量的价格
        if (this.average > o.average)
            return -1;
        else if (this.average == o.average)
            return 0;
        else
            return 1;
    }
}