package cn.huaji.main;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ShortestDis
 * @Description 平面点集求其中距离最近的两个点及其距离。
 * @author 滑技工厂 https://blog.csdn.net/qq_41718454
 * @date 2020/4/30
 * @version 1.0
 */
public class ShortestDis {

    /*
     * @Title divide
     * @Description 求平面上距离最近的两个点
     * @author 滑技工厂
     * @Date 2020/3/28
     * @param [left, right, points]
     * @return double
     * @throws
     */
    public static double divide(int left, int right, Point[] points) {
        // 当前最小两点距离，初始值设置为无穷大
        double curMinDis = 1e20;
        // 如果只有一个点，则不存在最近两点距离，返回无穷大
        if (left == right) {
            return curMinDis;
        }
        // 这里是判断是否为只有两个点，如果只有两个点的话那么直接求解。
        if (left + 1 == right) {
            return distance(points[left], points[right]);
        }

        // 分治法：第一步：分区，并求取左右分区最小两点距离
        // 通过右移运算除2，对区域进行合理的划分，使得左右两边保持大致相等个数点
        int middle = (left + right) >> 1;
        double leftMinDis = divide(left, middle, points);
        double rightMinDis = divide(middle, right, points);

        curMinDis = (leftMinDis <= rightMinDis) ? leftMinDis : leftMinDis;

        // 分治法：第二步：假设距离最近的两点分别在左右分区中
        // 关键代码，距离最近的两个点，一个位于左边区域，一个位于右边区域，x轴搜索范围[middle-curMinDis, middle+curMinDis]
        // 记录搜索区间内的点的索引，便于进一步计算最小距离
        List<Integer> validPointIndex = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (Math.abs(points[middle].x - points[i].x) <= curMinDis) {
                validPointIndex.add(i);
            }
        }
        // 基于索引，进一步计算区间内最小两点距离
        for (int i = 0; i < validPointIndex.size() - 1; i++) {
            for (int j = i + 1; j < validPointIndex.size(); j++) {
                // 如果区间内的两点y轴距离大于curMinDis，则没必要计算了，因为，它们的距离肯定大于curMinDis，
                if (Math.abs(points[validPointIndex.get(i)].y
                        - points[validPointIndex.get(j)].y) > curMinDis) {
                    continue;
                }
                double tempDis = distance(points[validPointIndex.get(i)],
                        points[validPointIndex.get(j)]);

                curMinDis = (tempDis < curMinDis) ? tempDis : curMinDis;
            }
        }

        return curMinDis;
    }


    public static double distance(Point p1, Point p2) {
        return Math.sqrt((p2.y - p1.y) * (p2.y - p1.y) + (p2.x - p1.x) * (p2.x - p1.x));
    }

    class Point {
        int x;
        int y;

    }
}
