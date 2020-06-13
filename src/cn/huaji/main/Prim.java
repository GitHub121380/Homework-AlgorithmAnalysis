package cn.huaji.main;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Prim
 * @Description Prim算法 最小生成树
 * @author 滑技工厂 https://blog.csdn.net/qq_41718454
 * @date 2020/6/13
 * @version 1.0
 */
public class Prim {

    static int MAX = Integer.MAX_VALUE;

    /*
     * @Title prim
     * @Description prim算法
     * @author 滑技工厂
     * @Date 2020/6/13
     * @param [map 邻接矩阵, n 结点个数]
     * @return void
     * @throws
     */
    public static void prim(int[][] map, int n) {
        char[] c = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'};
        //未加入的点到新集合的最小权
        int[] lowcost = new int[n];
        //存取前驱结点
        int[] mid = new int[n];
        //按加入顺序存储新加入结点
        List<Character> list = new ArrayList<>();
        //初始化
        for (int i = 1; i < n; i++) {
            lowcost[i] = map[0][i];
            mid[i] = 0;
        }
        list.add(c[0]);


        int min, minId, sum = 0;
        //还剩n-1个结点未加入
        for (int i = 1; i < n; i++) {
            //初始化最小权值
            min = MAX;
            minId = 0;

            //每次找距离集合最近的点
            for (int j = 1; j < n; j++) {
                //不是自己 且要找最小的权值
                if (lowcost[j] != 0 && lowcost[i] < min) {
                    min = lowcost[j];
                    minId = j;
                }
            }
            //有点不可达
            if (minId == 0)
                return;
            list.add(c[minId]);

            lowcost[minId] = 0;
            //求最小权值
            sum += min;
            System.out.println(c[mid[minId]] + "到" + c[minId] + "权值：" + min);

            //加入该点后，更新其他点到整个集合的距离
            for (int j = 1; j < n; j++) {

                if (lowcost[j] != 0 && lowcost[j] > map[minId][j]) {
                    lowcost[j] = map[minId][j];
                    mid[j] = minId;
                }

            }

        }
        System.out.println("sum:" + sum);
    }


    public static void main(String[] args) {
        int[][] map = new int[][]{
                {0, 10, MAX, MAX, MAX, 11, MAX, MAX, MAX},
                {10, 0, 18, MAX, MAX, MAX, 16, MAX, 12},
                {MAX, MAX, 0, 22, MAX, MAX, MAX, MAX, 8},
                {MAX, MAX, 22, 0, 20, MAX, MAX, 16, 21},
                {MAX, MAX, MAX, 20, 0, 26, MAX, 7, MAX},
                {11, MAX, MAX, MAX, 26, 0, 17, MAX, MAX},
                {MAX, 16, MAX, MAX, MAX, 17, 0, 19, MAX},
                {MAX, MAX, MAX, 16, 7, MAX, 19, 0, MAX},
                {MAX, 12, 8, 21, MAX, MAX, MAX, MAX, 0}};
        prim(map, map.length);
    }

}
