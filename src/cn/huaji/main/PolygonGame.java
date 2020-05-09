package cn.huaji.main;

/**
 * @ClassName PolygonGame
 * @Description 多边形游戏
 *
 *有一个由n个顶点构成的多边形。每个顶点被赋予一个整数值，每条边被赋予一个运算符。每条边被赋予一个运算符+或*。所有边依次用整数1到n编号。
 * 游戏规则：
 *
 * 删去一条边
 * 后续步骤按以下方式操作：
 * 选择一条边E及边E的两个顶点v1和v2
 * 用一个新的顶点v取代边E及由E连接着的2个顶点，将2个顶点上的数值由E上的运算符获得结果，病赋值给新的顶点v。最后，所有的边都被删除，游戏结束，得到游戏分数(最后顶点上的整数值)
 * 问题：对于给定的多边形，计算最高得分
 *
 *
 * 当删除一条边后，展开就是一个链，接下来就和矩阵连乘的分解子问题步骤类似
 *
 * @author 滑技工厂 https://blog.csdn.net/qq_41718454
 * @date 2020/5/8
 * @version 1.0
 */
public class PolygonGame {

    //边和点的个数
    static int n;
    //点集
    static int[] v;
    //边集
    static char[] op;
    //存储最终计算结果
    static int[][][] m;

    static int maxf, minf;


    public static void main(String[] args) {

        n = 4;
        //以下所有数组下标为0的都不使用
        //构造出的多边形的最终结果：+ -7 + 4 * 2 * 5
        v = new int[]{Integer.MIN_VALUE, -7, 4, 2, 5};
        op = new char[] {' ', '+', '+', '*', '*'};
        m = new int[n + 1][n + 1][2];
        for(int i = 1; i <= n; i++)
        {
            //m[i][j][0]：表示链的起点为i，长度为j时的结果最小值
            m[i][1][0] = v[i];
            //m[i][j][1]：表示链的起点为i，长度为j时的结果最大值
            m[i][1][1] = v[i];
        }
        int result = polyMax();
        System.out.println(result);
    }

    /*
     * @Title minMax
     * @Description 从i开始长度为j的链从s断开
     * @author 滑技工厂
     * @Date 2020/5/9
     * @param [i链的起点, s断开位置, j链长度]
     * @return void
     * @throws
     */
    public static void minMax(int i, int s, int j) {
        int[] e = new int[n + 1];
        //s左侧子链最小值
        int a = m[i][s][0];
        // s左侧子链最大值
        int b = m[i][s][1];
        //做到循环 防止下标越界
        int r = (i + s - 1) % n + 1;

        //s右侧子链最小值
        int c = m[r][j - s][0];

        //s右侧子链最大值
        int d = m[r][j - s][1];

        //如果是加号
        if (op[r] == '+') {
            minf = a + c;
            maxf = b + d;
        } else if (op[r] == '*') {//如果是乘号

            e[1] = a * c;
            e[2] = a * d;
            e[3] = b * c;
            e[4] = b * d;
            minf = e[1];
            maxf = e[1];
            //
            for (int k = 2; k < 5; k++) {
                if (minf > e[k])
                    minf = e[k];
                if (maxf < e[k])
                    maxf = e[k];

            }


        }

    }

    /*
     * @Title polyMax
     * @Description 自底向上
     * @author 滑技工厂
     * @Date 2020/5/9
     * @param []
     * @return int
     * @throws
     */
    public static int polyMax() {

        //长度从2到n
        for (int j = 2; j <= n; j++) {
            //起始点逐渐上升
            for (int i = 1; i <= n; i++) {
                //分割点逐一尝试
                for (int s = 1; s < j; s++) {

                    minMax(i, s, j);

                    if (m[i][j][0] > minf)
                        m[i][j][0] = minf;
                    if (m[i][j][1] < maxf)
                        m[i][j][1] = maxf;

                }

            }

        }

        int max = m[1][n][1];
        int edge = 1;
        for (int i = 1; i <= n; i++) {
            System.out.println("删除第" + i + "条边时为" + m[i][n][1]);
            if (m[i][n][1] > max) {
                max = m[i][n][1];
                edge = i;
            }


        }
        System.out.println("删除第" + edge + "时为最大：" + max);
        return max;
    }

}
