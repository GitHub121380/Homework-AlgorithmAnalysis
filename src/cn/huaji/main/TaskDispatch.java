package cn.huaji.main;

import java.util.Arrays;

/**
 * @ClassName TaskDispatch
 * @Description 任务调度
 *
 * 问题：有n项任务，每项任务加工时间已知，从 0时刻开始陆续安排到一台机器上加工，
 * 每个任务的完成时间是从 0 时刻到任务加工截止的时间。
 * 求：总完成时间 最短的安排方案（所有任务完成时间之和）。
 *
 * @author 滑技工厂 https://blog.csdn.net/qq_41718454
 * @date 2020/6/11
 * @version 1.0
 */
public class TaskDispatch {

    public static void main(String[] args) {
        int n = 5;
        int[] times = {3, 8, 5, 10, 15};
        //总时间和
        int sum = 0;

        Task[] tasks = new Task[n];
        for (int i = 0; i < n; i++) {
            tasks[i] = new Task(i+1,times[i]);
        }

        Arrays.sort(tasks);

        tasks[0].sumTime = tasks[0].time;

        for (int i = 1; i < n; i++) {
            tasks[i].sumTime = tasks[i].time + tasks[i - 1].sumTime;
        }

        for (int i = 0; i < n; i++) {
            sum += tasks[i].sumTime;
        }

        System.out.println(sum);

    }

    /**
     * @ClassName Task
     * @Description 任务类
     * @author 滑技工厂 https://blog.csdn.net/qq_41718454
     * @date 2020/6/11
     * @version 1.0
     */
    static class Task implements Comparable<Task> {
        int index;
        int time;
        int sumTime;

        public Task(int index, int time) {
            this.index = index;
            this.time = time;
        }

        @Override
        public int compareTo(Task task) {
            if (this.time < task.time)
                return -1;
            else if (this.time == task.time)
                return 0;
            else
                return 1;

        }
    }

}
