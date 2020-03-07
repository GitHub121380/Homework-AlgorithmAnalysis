package cn.huaji.main;

import java.io.BufferedInputStream;
import java.util.Scanner;

/**
 * @ClassName BrokenKeyboard
 * @Description 悲剧的文本
 *
 * 你有一个破损的键盘。键盘上所有的键都可以正常工作，但有时候Home键或者End键会自动按下。
 * 你并不知道键盘存在这一问题，而是专心打稿子，甚至连显示器都没打开。当你打开显示器后，
 * 展现在你面前的是一段悲剧文本。你的任务是在打开显示器之前计算出这段悲剧文本。
 * 输入包含多组数据。每组数据占一行，包含不超过100000个字母、下划线、字符“【”或者“】”。
 * 其中字符“【”表示Home键，“】”表示End键。输入结束标志为文件结束符（EOF）。输入文件不超过5MB。
 * 对于每组数据，输出一行，即屏幕上的悲剧文本。
 *
 * 样例输入：
 *
 * This_is_a_[Beiju]_text
 *
 * 样例输出：
 *
 * BeijuThis_is_a_text
 *
 * 链表结构
 *
 * @author 滑技工厂 https://blog.csdn.net/qq_41718454
 * @date 2020/3/7
 * @version 1.0
 */
public class BrokenKeyboard {

    public static void main(String[] args) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        String s = sc.nextLine();
        char[] chars = s.toCharArray();
        Node node = new Node();
        Node rear = node;
        node.create(node,rear,chars);

        node.print(node);
    }


    static class Node {
        char data;
        Node next;
        public Node() {
        }

        public Node(char data, Node next) {
            this.data = data;
            this.next = next;
        }
        public void print(Node node) {

            while (node != null) {
                if (node.data != 0)
                    System.out.print(node.data);
                node = node.next;
            }

        }
        /*
         * @Title create
         * @Description create方法，遇见'['从头指针插入，遇见']'从尾指针插入
         * @author 滑技工厂
         * @Date 2020/3/7
         * @param [head, a]
         * @return void
         * @throws
         */
        public void create(Node head, Node rear, char a[]) {
            //指针开始等于头结点
            Node flag = head;
            for (int i = 0; i < a.length; i++) {

                if (a[i] == '[') {
                    //[ 指针移到头结点
                    flag = head;
                    continue;
                } else if (a[i] == ']') {
                    //指针移到尾结点
                    flag = rear;
                    continue;
                }


                if (flag.next == null) {//flag=rear 在尾结点
                    flag.next = new Node(a[i], null);
                    flag = flag.next;
                    //保证rear始终在尾结点
                    rear = flag;
                } else {//不在尾结点，头结点还是中间结点都一样，next有值

                    Node tmp = new Node(a[i], flag.next);
                    flag.next = tmp;
                    flag = tmp;
                }

            }
        }

    }

}
