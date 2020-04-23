package cn.huaji.main;

/**
 * @ClassName MatrixMultiply
 * @Description 矩阵连乘问题
 * 分析：
 * 矩阵链乘法问题描述：
 * 给定由n个矩阵构成的序列｛A1，A2，...，An}，对乘积A1A2...An，找到最小化乘法次数的加括号方法。
 *
 * 1）寻找最优子结构
 * 此问题最难的地方在于找到最优子结构。对乘积A1A2...An的任意加括号方法都会将序列在某个地方分成两部分，也就是最后一次乘法计算的地方，
 * 我们将这个位置记为k，也就是说首先计算A1...Ak和Ak+1...An，然后再将这两部分的结果相乘。
 * 最优子结构如下：假设A1A2...An的一个最优加括号把乘积在Ak和Ak+1间分开，
 * 则前缀子链A1...Ak的加括号方式必定为A1...Ak的一个最优加括号，后缀子链同理。
 * 一开始并不知道k的确切位置，需要遍历所有位置以保证找到合适的k来分割乘积。
 *
 * 2）构造递归解
 * 设m[i,j]为矩阵链Ai...Aj的最优解的代价，则
 *
 *m[i,j]=0  i=j   /     min{m[i,k]+m[k+1,j]+Pi-1*Pi*Pj}  i<j
 *
 * 3）构建辅助表，解决重叠子问题
 * 从第二步的递归式可以发现解的过程中会有很多重叠子问题，可以用一个nXn维的辅助表m[n][n] s[n][n]分别表示最优乘积代价及其分割位置k 。
 * 辅助表s[n][n]可以由2种方法构造，一种是自底向上填表构建，该方法要求按照递增的方式逐步填写子问题的解，也就是先计算长度为2的所有矩阵链的解，然后计算长度3的矩阵链，直到长度n；另一种是自顶向下填表的备忘录法，该方法将表的每个元素初始化为某特殊值(本问题中可以将最优乘积代价设置为一极大值)，以表示待计算，在递归的过程中逐个填入遇到的子问题的解。
 *
 *
 * @author 滑技工厂 https://blog.csdn.net/qq_41718454
 * @date 2020/4/22
 * @version 1.0
 */
public class MatrixMultiply {
    //max表示最大值，用来与i<j时每次k划分的m[i,j]进行比较，若m[i，j]小则把其赋值给MAX，表示目前他是最大
    static final int MAX = 1000000000;
    //m：存储矩阵Ai——Aj的最小相乘次数  s：表示矩阵Ai——Aj中相乘最小时其k的划分位置
    static int[][] m, s;
    //p：每个矩阵a*b的b，就是列数，p[i-1]是Ai-1的列数，也是Ai的行数，用p来代指每个矩阵的维度
    static int[] p;

    /*
     * @Title recursiveMatrixMultiply
     * @Description 普通递归算法  i：矩阵序列的左起始点 j：矩阵序列的右起始点
     * @author 滑技工厂
     * @Date 2020/4/22
     * @param [i, j]
     * @return int 计算出来是Ai——Aj这段矩阵序列的最小相乘次数
     * @throws
     */
    public static int recursiveMatrixMultiply(int i, int j) {
        //表示最小值，用来与i<j时每次k划分的m[i,j]进行比较，若m[i，j]小则把其赋值给tempMin，表示目前他是最小
        int tempMin = MAX;
        if (i == j)
            return 0;
        //表示从k=i——j-1的所有划分情况，在这些情况中，每次k都要与上次比大小，找出最小的并付给tempMin
        //不能等于j，因为上限是j，递归方程中有k+1---j的值
        for (int k = i; k < j; k++) {
            //k表示i--j中的分割位置，分割为i——k，k+1——j
            int result = recursiveMatrixMultiply(i, k) + recursiveMatrixMultiply(k + 1, j) + p[i - 1] * p[k] * p[j];
            //如果得到的结果比当前最小值小，则更新最小值，并更新s[i,j]的值，也就是划分位置k
            if (result < tempMin) {
                tempMin = result;
                s[i][j] = k;
            }
        }

        return tempMin;
    }

    /*
     * @Title upToDownDPMatrixMultiply
     * @Description 自顶向下的dp解法
     * @author 滑技工厂
     * @Date 2020/4/23
     * @param [i, j]ij同上
     * @return int
     * @throws
     */
    public static int upToDownDPMatrixMultiply(int i, int j) {
        int tempMin = MAX;
        if (i==j)
            return 0;
        //与递归算法不同的是，这里可以查表，对于已经计算过一次的m[i][j]，可以直接查表获得对应的计算次数，而不用多次去计算m[i][j]
        if (m[i][j]!=0)
            return m[i][j];
        for (int k = i; k < j; k++) {
            int result = upToDownDPMatrixMultiply(i, k) + upToDownDPMatrixMultiply(k + 1, j) + p[i - 1] * p[k] * p[j];
            if (result<tempMin){
                tempMin = result;
                s[i][j] = k;
            }
            m[i][j] = tempMin;

        }
        return tempMin;
    }

    /*
     * @Title pprint
     * @Description 打印矩阵序列的方法
     * @author 滑技工厂
     * @Date 2020/4/23
     * @param [i左范围, j右范围]
     * @return void
     * @throws
     */
    public static void pprint(int i, int j) {

        if (i == j) {
            System.out.print("A" + i);
            return;
        }
        System.out.print("(");
        pprint(i, s[i][j]);
        pprint(s[i][j] + 1, j);
        System.out.print(")");
    }

    public static void main(String[] args) {
        //6个值，5个矩阵
        p = new int[]{5,6,8,3,2,6};
        m = new int[5+1][5+1];
        s = new int[5+1][5+1];
        //从1到n
        int min = upToDownDPMatrixMultiply(1,5);

        System.out.println(min+" "+s[1][5]);
        pprint(1,5);
    }
}
