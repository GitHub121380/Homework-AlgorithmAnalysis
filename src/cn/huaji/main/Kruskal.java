package cn.huaji.main;

import java.util.Arrays;

/**
 * @ClassName Kruskal
 * @Description 最小生成树 Kruskal算法
 *
 * 寻找最小边，每找到一次最小边保证不会和以前找到的边形成回路，否则找次小边
 *
 *
 * @author 滑技工厂 https://blog.csdn.net/qq_41718454
 * @date 2020/6/12
 * @version 1.0
 */
public class Kruskal {
    //边数
    private int edgeNum;
    //顶点数组
    private char[] vertexs;
    //邻接矩阵
    private int[][] matrix;
    //不相接 设置权值最大
    private static int MAX = Integer.MAX_VALUE;


    public Kruskal(char[] vertexs, int[][] matrix) {
        this.vertexs = vertexs;
        this.matrix = matrix;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i+1; j < matrix[0].length; j++) {
                if (this.matrix[i][j] != MAX)
                    edgeNum++;
            }
        }
    }

    /*
     * @Title kruskal
     * @Description 克鲁斯卡尔算法
     * @author 滑技工厂
     * @Date 2020/6/12
     * @param []
     * @return void
     * @throws
     */
    public void kruskal() {
        int index = 0;//表示最后结果数组的索引
        int[] ends = new int[edgeNum];//用于保存"已有最小生成树“中的每个顶点在最小生成树中的终点
        //创建结果数组，保存最后的最小生成树
        EData[] rets = new EData[edgeNum];

        //获取图中所有的边的集合，一共有12边
        EData[] edges = getEdges();
        System.out.println("图的边的集合=" + Arrays.toString(edges) + "共" + edges.length);

        //按照边的权值大小进行排序
        sortEdges(edges);

        //遍历edges数组，将边添加到最小生成树中时，判断是准备加入的边形成了回路，如果没有，就加入rets，否则不能加入
        for (int i = 0; i < edgeNum; i++) {
            //获取到第i条边的第一个顶点（起点）
            int p1 = getPosition(edges[i].start);
            //  //获取到第i条边的第2个顶点
            int p2 = getPosition(edges[i].end);

            //获取p1这个顶点在已有最小生成树中的终点
            int m = getEnd(ends, p1);
            //获取p2这个顶点在已有最小生成树中的终点
            int n = getEnd(ends, p2);
            //是否构成回路
            if (m != n) {//没有
                ends[m] = n;//设置m在”已有最小生成树“的终点<E,F>[0,0,0,0,0,0,0,0,0,0,0,0]
                rets[index++] = edges[i];//有一条边加入到rets数组
            }

        }
        //统计并打印”最小生成树“，输出rets
        System.out.println("最小生成树为=" + Arrays.toString(rets));
    }

    /*
     * @Title print
     * @Description 打印邻接矩阵
     * @author 滑技工厂
     * @Date 2020/6/12
     * @param []
     * @return void
     * @throws
     */
    public void print() {
        System.out.println("邻接矩阵为:\n");
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                System.out.printf("%12d", matrix[i][j]);
            }
            System.out.println();//换行
        }
    }

    /*
     * @Title sortEdges
     * @Description 对边进行排序 冒泡排序
     * @author 滑技工厂
     * @Date 2020/6/12
     * @param [edges]
     * @return void
     * @throws
     */
    private void sortEdges(EData[] edges) {
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - 1 - i; j++) {
                if (edges[j].weight > edges[j + 1].weight) {
                    EData tmp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = tmp;
                }
            }
        }
    }


    /*
     * @Title getPosition
     * @Description 获取顶点下标
     * @author 滑技工厂
     * @Date 2020/6/12
     * @param [ch] 顶点的值，比如'A','B'
     * @return int 返回ch顶点对应的下标，如果找不到，返回-1
     * @throws
     */
    private int getPosition(char ch) {
        for (int i = 0; i < vertexs.length; i++) {
            if (vertexs[i] == ch) {
                return i;
            }
        }
        //找不到，返回-1
        return -1;
    }

    /*
     * @Title getEdges
     * @Description 获取图中边，放到 EData[]数组中，后面我们需要遍历该数组
     * @author 滑技工厂
     * @Date 2020/6/12
     * @param []
     * @return cn.huaji.main.EData[]
     * @throws
     */
    private EData[] getEdges() {
        int index = 0;
        //未知边数 将所有
        EData[] edges = new EData[edgeNum];
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = i + 1; j < vertexs.length; j++) {
                if (matrix[i][j] != MAX) {
                    edges[index++] = new EData(vertexs[i], vertexs[j], matrix[i][j]);
                }
            }
        }
        edges = Arrays.copyOf(edges,index);
        return edges;
    }

    /*
     * @Title getEnd
     * @Description 获取下标为i的顶点的终点（），用于后面判断两个顶点的终点是否相同
     * @author 滑技工厂
     * @Date 2020/6/12
     * @param [ends数组就是记录了各个顶点对应的终点是哪个，ends数组是在遍历过程中逐步形成, i表示传入的顶点对应的下标]
     * @return int 返回的就是下标为i的这个顶点对应的终点的下标
     * @throws
     */
    private int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }

    public static void main(String[] args) {

        //测试
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //克鲁斯卡尔的邻接矩阵
        int matrix[][] = {
                /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                /*A*/{0, 12, MAX, MAX, MAX, 16, 14},
                /*B*/{12, 0, 10, MAX, MAX, 7, MAX},
                /*C*/ {MAX, 10, 0, 3, 5, 16, MAX},
                /*D*/ {MAX, MAX, 3, 0, 4, MAX, MAX},
                /*E*/ {MAX, MAX, 5, 4, 0, 2, 8},
                /*F*/{16, 7, 6, MAX, 2, 0, 9},
                /*G*/{14, MAX, MAX, MAX, 8, 9, 0}};
        //创建一个KruskalCace 对象实例
        Kruskal kruskalCace = new Kruskal(vertexs, matrix);
        //输出
        kruskalCace.print();
        kruskalCace.kruskal();

    }

}


/**
 * @ClassName EData
 * @Description 创建一个类EData,它的对象实例就表示一条边
 * @author 滑技工厂 https://blog.csdn.net/qq_41718454
 * @date 2020/6/12
 * @version 1.0
 */
class EData {
    char start;//边的起点
    char end;//边的另外一个点
    int weight;//权值

    //构造器
    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    //重写toString，便于输出
    @Override
    public String toString() {
        return "EData[<" + start + "," + end + ">=" + weight + ']';
    }
}