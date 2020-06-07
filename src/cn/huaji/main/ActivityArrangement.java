package cn.huaji.main;

import java.io.BufferedInputStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @ClassName ActivityArrangement
 * @Description 活动安排问题 贪心算法 不考虑整体最优而专注局部最优解
 *
 * 设有n个活动的集合E＝{1，2，…，n}，其中每个活动都要求使用同一资源，如演讲会场等，
 * 而在同一时间内只有一个活动能使用这一资源。
 * 每个活动i都有一个要求使用该资源的起始时间si和一个结束时间fi，且si＜fi。
 * 如果选择了活动i，则它在半开时间区间[si ，fi )内占用资源。若区间[si ，fi )与区间[sj，fj )不相交，
 * 则称活动i与活动j是相容的。当 si ≥ fj 或 sj ≥ fi 时，活动i与活动j相容。
 * 活动安排问题就是在所给的活动集合中选出最大的相容活动子集合。
 *
 *
 * @author 滑技工厂 https://blog.csdn.net/qq_41718454
 * @date 2020/6/5
 * @version 1.0
 */
public class ActivityArrangement {

    /*
     * @Title chooseActivity
     * @Description 选择活动方法
     * @author 滑技工厂
     * @Date 2020/6/5
     * @param [activities]
     * @return boolean[] 返回活动选择数组
     * @throws
     */
    public static boolean[] chooseActivity(Activity[] activities) {
        int n = activities.length;
        //用来表示原活动序列（未排序）是否被选中
        boolean[] flags = new boolean[n];

        //把序列按照结束时间进行升序排序
        Arrays.sort(activities);

        //结束时间指针
        int flag = 0;

        for (int i = 0; i < n; i++) {
            //排序之后，如果一个活动的开始时间在指针之后或相等，那么便可以举办这个活动
            if (activities[i].starttime >= flag) {
                //举办活动 更新结束时间指针
                flag = activities[i].finaltime;
                flags[activities[i].index] = true;
            }
        }
        return flags;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //设置活动个数
        int n = sc.nextInt();
        Activity activities[] = new Activity[n];

        //设置活动索引和开始时间
        for (int i = 0; i < n; i++) {
            activities[i] = new Activity();
            activities[i].index = i;
            activities[i].starttime = sc.nextInt();
        }
        //设置活动结束时间
        for (int i = 0; i < n; i++) {
            activities[i].finaltime = sc.nextInt();
        }
        //选活动
        boolean[] flags = chooseActivity(activities);

        for (boolean flag : flags) {
            System.out.print(flag + " ");
        }

    }

}

/**
 * @ClassName Activity
 * @Description 活动类
 * @author 滑技工厂 https://blog.csdn.net/qq_41718454
 * @date 2020/6/5
 * @version 1.0
 */
class Activity implements Comparable<Activity> {
    int index;
    int starttime;
    int finaltime;

    /*
     * @Title compareTo
     * @Description 排序方法
     * @author 滑技工厂
     * @Date 2020/6/5
     * @param [activity]
     * @return int 1表示this对象要排前面 -1表示不排前面
     * @throws
     */
    @Override
    public int compareTo(Activity activity) {
        if (activity.finaltime > this.finaltime)
            return -1;
        else if (activity.finaltime == this.finaltime)
            return 0;
        else
            return 1;
    }
}