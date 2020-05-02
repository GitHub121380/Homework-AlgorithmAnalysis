package cn.huaji.main;

/**
 * @ClassName BestTriangleDivide
 * @Description
 *
 * 给定凸多边形P，以及定义在由多边形的边和弦组成的三角形上的权函数w。要求确定该凸多边形的三角剖分，使得即该三角剖分中诸三角形上权之和为最小。
 * 解题思路：
 * 若凸(n+1)边形P={v0,v1,…,vn-1}的最优三角剖分T包含三角形v0vkvn，1≤k≤n-1，则T的权为3个部分权的和：三角形v0vkvn的权，子多边形{v0,v1,…,vk}和{vk,vk+1,…,vn}的权之和。
 * 可以断言，由T所确定的这2个子多边形的三角剖分也是最优的。因为若有{v0,v1,…,vk}或{vk,vk+1,…,vn}的更小权的三角剖分将导致T不是最优三角剖分的矛盾。
 *
 * 那么我们定义一个t[i][j]，1<=i<=j<=N,为凸子多边形{vi-1,vi,…,vj}的最优三角剖分所对应的权函数值，即其最优值。据此定义，要计算的凸(n+1)边形P的最优权值为t[1][n]。
 * 　　t[i][j]的值可以利用最优子结构性质递归地计算。当j-i≥1时，凸子多边形至少有3个顶点。由最优子结构性质，t[i][j]的值应为t[i][k]的值加上t[k+1][j]的值，再加上三角形vi-1vkvj的权值，其中i≤k≤j-1。由于在计算时还不知道k的确切位置，而k的所有可能位置只有j-i个，因此可以在这j-i个位置中选出使t[i][j]值达到最小的位置。由此，t[i][j]可递归地定义为：
 *
 * 对于要求的t[1][n]，可以用通过由下至上的，从链长（多边形的边）为2开始计算，每次求t[i][j]的最小值，并且记录最小值所对应的K值，根据最优子结构的性质，逐步向上就可以求出t[1][n]的最小值。
 * 类似的，求三角划分顶点的乘积的最小值问题，也是一样的。
 *
 * @author 滑技工厂 https://blog.csdn.net/qq_41718454
 * @date 2020/4/30
 * @version 1.0
 */
public class BestTriangleDivide {
    //多边形边数
    static int N = 6;
    //凸多边形的权
    static int[][] weight = {
            {0, 2, 2, 3, 1, 4},
            {2, 0, 1, 5, 2, 3},
            {2, 1, 0, 2, 1, 4},
            {3, 5, 2, 0, 6, 2},
            {1, 2, 1, 6, 0, 1},
            {4, 3, 4, 2, 1, 0}
    };

    /*
     * @Title minestWehghtVal
     * @Description 划分  与矩阵连乘的自底向上类似
     * @author 滑技工厂
     * @Date 2020/4/30
     * @param [n, t, s]
     * @return void
     * @throws
     */
    public static int minestWehghtVal(int n, int[][] t, int[][] s) {

        //r为当前计算链长（子问题规模）
        for (int r = 2; r <= n; r++) {
            for (int i = 1; i <= n - r + 1; i++) {

                int j = i + r - 1;
                //将链i——j划分，这里就是k=i
                t[i][j] = t[i + 1][j] + weight(i - 1, i, j);

                s[i][j] = i;

                for (int k = i + 1; k < j; k++) {
                    int result = t[i][k] + t[k + 1][j] + weight(i - 1, k, j);
                    if (result < t[i][j]) {
                        t[i][j] = result;
                        s[i][j] = k;
                    }
                }

            }

        }
        return t[1][N - 1];

    }

    public static int weight(int a, int b, int c) {
        return weight[a][b] + weight[b][c] + weight[a][c];
    }

    public static void traceback(int i, int j, int[][] s) {
        if (i == j)
            return;
        traceback(i, s[i][j], s);
        traceback(s[i][j] + 1, j, s);
        System.out.println("三角剖分顶点：v" + (i - 1) + ",v" + j + ",v" + s[i][j]);
    }

    public static void main(String[] args) {

        int[][] s = new int[N][N];
        int[][] t = new int[N][N];
        System.out.println("此多边形的最优三角剖分值为：" + minestWehghtVal(N-1, t, s));

        traceback(1, 5, s);

    }

}
