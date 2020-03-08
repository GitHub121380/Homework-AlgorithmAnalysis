package cn.huaji.main;

import java.util.Scanner;

/**
 * @ClassName HanoiImpl
 * @Description 汉诺塔
 * @author 滑技工厂 https://blog.csdn.net/qq_41718454
 * @date 2020/3/8
 * @version 1.0
 */
public class HanoiImpl {
    //移动次数
    static int times;

    public static void main(String[] args) {
        char A = 'A';
        char B = 'B';
        char C = 'C';
        System.out.println("汉诺塔游戏开始啦");
        System.out.println("请输入盘子数：");
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        //调用汉诺塔
        hanoi(n, A, B, C);
        s.close();

    }

    /*
     * @Title move
     * @Description 单次移动
     * @author 滑技工厂
     * @Date 2020/3/8
     * @param [disk, M, N]
     * @return void
     * @throws 
     */
    public static void move(int disk, char M, char N ){
        System.out.println("第"+(++times)+"次移动, 盘子"+disk+ "  "+M+"------->"+N);
    }

    /*
     * @Title hannoi
     * @Description TODO(这里用一句话描述这个方法的作用)
     * @author 滑技工厂
     * @Date 2020/3/8
     * @param [n, A, B, C]
     * @return void
     * @throws
     */
    public static void hanoi(int n, char A, char B, char C){
        if(n == 1){
            move(n, A, C);
        }else{
            //移动上一关的步骤移动到B
            hanoi(n - 1, A, C, B);
            //把最大的盘子移动C塔
            move(n, A, C);
            //再把B上的上一关的盘子移动到C上就可以了
            hanoi(n - 1, B, A, C);

        }
    }


}
