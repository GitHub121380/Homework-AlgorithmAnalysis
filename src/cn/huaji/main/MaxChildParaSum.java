package cn.huaji.main;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName MaxChildParaSum
 * @Description 最大子段和问题
 *
 * 给定n个整数（可能为负数）组成的序列a[1],a[2],a[3],…,a[n],
 * 求该序列如a[i]+a[i+1]+…+a[j]的子段和的最大值。
 * 当所给的整数均为负数时定义子段和为0，依此定义，所求的最优值为： Max{0,a[i]+a[i+1]+…+a[j]},1<=i<=j<=n
 * 例如，当（a[1],a[2],a[3],a[4],a[5],a[6]）=(-2,11,-4,13,-5,-2)时，最大子段和为20。
 *
 * dp[i] = max{     dp[i-1] + A[i]    ,         A[i]       }
 * （正数+正数肯定更大，正数+负数也一定比Ai大，负数+正数/负数+负数那肯定Ai更大,因此选择Ai这种情况只是为了防止负数越加越大的情况，及时止损重新开始一个子段）
 * 每次得到的dpi都和max比较，取最大的值作max
 * 上一位的子段和加自己   如果加出来的和还没自己大，那说明自己要重开一个子段，自己做子段的第一个元素
 *
 * @author 滑技工厂 https://blog.csdn.net/qq_41718454
 * @date 2020/5/22
 * @version 1.0
 */
public class MaxChildParaSum {
    //存储最长子段和的子段序列
    static Queue<Integer> queue = new LinkedList<>();

    /*
     * @Title maxChildSum
     * @Description
     *
     * dp[i] = max{     dp[i-1] + A[i]    ,         A[i]       }
     * （正数+正数肯定更大，正数+负数也一定比Ai大，负数+正数/负数+负数那肯定Ai更大,因此选择Ai这种情况只是为了防止负数越加越大的情况，及时止损重新开始一个子段）
     * 每次得到的dpi都和max比较，取最大的值作max
     * 上一位的子段和加自己   如果加出来的和还没自己大，那说明自己要重开一个子段，自己做子段的第一个元素
     *
     * @author 滑技工厂
     * @Date 2020/5/22
     * @param [arr]序列数组
     * @return int返回的最大子段和
     * @throws
     */
    public static int maxChildSum(int[] arr) {
        int[] dp = new int[arr.length];
        int max = 0;
        //初始化dp数组 判断第一个数是否>0 true则可以当作子段头入队，false则不当子段头去看下一个
        if (arr[0] >= 0) {
            dp[0] = arr[0];
            queue.add(0);
        } else
            dp[0] = 0;

        //用来存储不断在用于计算的子段长
        //因为就算max没有改变 dp也在不断计算 i也在不断添加到队列中 输出的序列是长于最大子段和的
        //因此queue用来存储最大子段序列，之后计算dp时都存在temp序列中，当dp>max时，更新queue将temp添加到queue中并清空temp
        Queue<Integer> temp = new LinkedList<>();

        //从1开始
        for (int i = 1; i < arr.length; i++) {
            //如果前一个子段和大于0，则将Ai连接到上个子段中
            if (dp[i - 1] > 0) {
                dp[i] = dp[i - 1] + arr[i];
            } else {//如果<=0，则清空队列，自己重新开一个子段，作为子段头
                dp[i] = arr[i];
                queue.clear();
                temp.clear();
            }
            //无论是连接还是新开子段，都要将i添加到队列中
            temp.add(i);

            //如果本次子段和比当前最大值大
            if (max < dp[i]){
                //赋值
                max = dp[i];
                //将临时存储的子段序列加入到最大子段和序列中并清空临时队列去重新存储
                queue.addAll(temp);
                temp.clear();
            }

        }

        return max;
    }

    public static void main(String[] args) {
        int[] arr = {-20,11,-4,13,-5,2};
        System.out.println(maxChildSum(arr));
        for (Integer integer : queue) {
            System.out.print(integer.intValue()+" ");
        }

    }

}
