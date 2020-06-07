package cn.huaji.main;

/**
 * @ClassName ShortestDistance
 * @Description 最短路径问题
 * @author 滑技工厂 https://blog.csdn.net/qq_41718454
 * @date 2020/4/29
 * @version 1.0
 */
public class ShortestDistance {
    public static final int MAX = 1000000000;

    /*
     * @Title dijkstra
     * @Description dist[j]=min{dist[j],dist[i]+matrix[i][j]}
     * @author 滑技工厂
     * @Date 2020/4/29
     * @param [matrix]
     * @return int[] 最短距离数组
     * @throws
     */
    public static int[] dijkstra(int[][] matrix) {
        int[] distance = new int[matrix.length];
        //最短距离数组初始化 除了源点都默认到v0与各个结点的距离（不可达的是MAX）
        for (int i = 1; i < distance.length; i++) {
            distance[i] = matrix[0][i];
        }
        boolean[] visit = new boolean[distance.length];
        //到源点到每个点的最短距离的路径
        String[] path = new String[distance.length];
        for (int i = 0; i < path.length; i++) {
            //初始化默认从源点直达，后面如果途径中间结点会修改
            path[i] = "0-->" + i;
        }
        //源点已经访问过
        visit[0] = true;
        //表示计数，已经访问过一个结点
        int count = 1;
        //当count==结点数时结束循环
        while (count++ < distance.length) {
            //从刚被访问的结点可直达的结点中找一个distance最小的结点返回下标，即为下一个要访问的
            //但是这个方法其实是遍历所有未被访问的结点，因为访问不到的为MAX所以意义一样
            int index = min(distance, visit);
            visit[index] = true;
            //添加过新访问的结点后，重新计算到各点最短路径，如果某结点有着能通过新访问结点的最短路径（比原来大），那么将其最短路径值更新
            for (int j = 1; j < distance.length; j++) {
                if (!visit[j] && distance[j] > distance[index] + matrix[index][j]) {
                    distance[j] = distance[index] + matrix[index][j];
                    path[j] = path[index] + "-->" + j;
                }

            }

        }

        for (int i = 0; i < path.length; i++) {
            System.out.println(path[i]);
        }
        return distance;
    }


    /*
     * @Title min
     * @Description 返回距离数组中未确定元素的下标
     * @author 滑技工厂
     * @Date 2020/4/29
     * @param [distance当前的最短距离数组（包含已确定和未确定和正在确定）, visit表示结点是否为确定状态]
     * @return int
     * @throws
     */
    public static int min(int[] distance,boolean[] visit) {
        int min = MAX;
        int index = 0;
        //省略源点v0的距离（因为值为0），从点v1开始
        for (int i = 1; i < distance.length; i++) {
            //说明是未确定过最短路径的点
            if (!visit[i]&&distance[i] < min) {
                min = distance[i];
                index = i;
            }

        }
        return index;
    }

    public static void main(String[] args) {
//        int[][] matrix = new int[6][6];
//        //矩阵初始化 所有距离都先默认到最大值
//        for (int i = 0; i < matrix.length; i++) {
//
//            for (int j = 0; j < matrix[1].length; j++) {
//                matrix[i][j] = MAX;
//            }
//        }
        int[][] weight = {//邻接矩阵
                {0, 3, 20, 7, MAX},
                {3, 0, 4, 2, MAX},
                {MAX, 4, 0, 5, 4},
                {7, 2, 5, 0, 6},
                {MAX, MAX, 4, 6, 0}
        };
        int[] distance = dijkstra(weight);

        for (int i = 0; i < distance.length; i++) {
            System.out.print(distance[i]+" ");
        }
    }
}
