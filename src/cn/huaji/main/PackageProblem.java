package cn.huaji.main;

/**
 * @ClassName PackageProblem
 * @Description 各类背包问题
 * @author 滑技工厂 https://blog.csdn.net/qq_41718454
 * @date 2020/5/7
 * @version 1.0
 */
public class PackageProblem {

    static boolean[] choice;
    static int[][] dp;

    static int[] weights = {2, 3, 4, 5};            //商品的体积2、3、4、5
    static int[] values = {3, 4, 5, 6};


    /*
     * @Title package01
     * @Description 01背包问题
     * @author 滑技工厂
     * @Date 2020/5/7
     * @param [weights, values, W]
     * @return int[]
     * @throws
     */
    public static int[][] package01(int[] weights, int[] values, int W) {
        //表示物品个数
        int n = weights.length;

        //最大值最优解的数组 i——0~i表示选择第i个物品
        // j——0~j，当小背包（从大背包W中分出一个小背包j）容量为j时选第i个物品的最大价值
        dp = new int[n + 1][W + 1];
        //从第1个物品开始选择
        for (int i = 1; i <= n; i++) {

            //小背包容量为0的时候不可能放东西的，所以也从1开始
            for (int j = 1; j <= W; j++) {
                //判断小背包容量j是否能装下i（看weight[i]），如果不能装下，则等于没装i（小背包容量为j前i-1物品状态，j范围内装下前i-1且装不下i）
                //如果装得下，要判断最大值，是不装dp[i - 1][j]，还是装上dp[i - 1][j - weights[i]] + values[i]
                //（values[i]+小背包容量为j-weights[i]的前i-1种情况，小背包j中再分一部分小背包j-weights[i]存储前i-1种情况，装上物品i正好为j）
                dp[i][j] = (j >= weights[i - 1] ? Math.max(dp[i - 1][j], dp[i - 1][j - weights[i - 1]] + values[i - 1]) : dp[i - 1][j]);

            }

        }

        return dp;
    }

    /*
     * @Title content
     * @Description 追溯放进背包的物品
     * @author 滑技工厂
     * @Date 2020/5/7
     * @param [i, j]
     * @return void
     * @throws
     */
    public static void content(int i, int j) {

        if (i > 0) {
            if (dp[i][j] == dp[i - 1][j]) {
                choice[i - 1] = false;
                content(i - 1, j);
            } else if (j >= weights[i - 1] && dp[i][j] == dp[i - 1][j - weights[i - 1]] + values[i - 1]) {
                choice[i - 1] = true;
                content(i - 1, j - weights[i - 1]);
            }

        }
    }


    public static void main(String[] args) {
        int bagV = 8;
        System.out.println(package01(weights, values, bagV)[4][8]);
    }

}
