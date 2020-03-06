package cn.huaji.main;

/**
 * @ClassName Node
 * @Description 结点类
 * @author 滑技工厂 https://blog.csdn.net/qq_41718454
 * @date 2020/3/6
 * @version 1.0
 */
public class Node {
    Integer data;
    Node next;

    public Node() {
    }

    public Node(Integer data, Node next) {
        this.data = data;
        this.next = next;
    }

    /*
     * @Title getReciprocal
     * @Description 求倒数第n个，递归中n表示index离目标结点的距离为length-n+1
     *               flag先移动，每次都--n，直到n=1，flag与index一同移动，flag到尾巴时index即为所求
     * @author 滑技工厂
     * @Date 2020/3/6
     * @param [flag, index, n]
     * @return cn.huaji.main.Node
     * @throws
     */
    public static Node getReciprocal(Node flag, Node index, int n) {
        //第一个指针到尾巴，就返回指针2
        if (flag.next == null) {
            return index;
        }
        if (n <= 1) {
            //n=1时index开始移动
            index = index.next;
        }
        flag = flag.next;

        return getReciprocal(flag, index, --n);

    }


    /*
     * @Title reverse
     * @Description 逆置单链表
     * @author 张佳乐
     * @Date 2020/2/25
     * @param [node, head]
     * @return void
     * @throws
     */
    public static void reverse(Node node, Node head) {
        //如果子节点为空（到了最后一个结点，尾巴），将头结点的next指向尾巴，返回上个结点（倒数第二个）
        if (node.next == null) {
            head.next = node;
            return;
        }
        //一直往下递归，直到尾巴
        reverse(node.next, head);
        //向上返回之后，让node的子节点的next指向node自己
        node.next.next = node;
        //这步对于除第一个结点外的其他结点无用，是为了让逆置之后的尾结点的next为null而写，其他结点的next会因上一步指向自己的父节点
        node.next = null;
    }
    /*
     * @Title print
     * @Description 打印单链表
     * @author 张佳乐
     * @Date 2020/2/25
     * @param [node]
     * @return void
     * @throws
     */
    public static void print(Node node) {

        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }

    }
    /*
     * @Title create
     * @Description 根据数组创建单链表
     * @author 张佳乐
     * @Date 2020/2/25
     * @param [head, a]
     * @return void
     * @throws
     */
    public static void create(Node head, int a[]) {

        Node tmp = head;
        for (int i = 0; i < a.length; i++) {
            tmp.next = new Node(a[i], null);
            tmp = tmp.next;
        }
    }

    public static void main(String[] args) {

        Node head = new Node();

        int[] arr = new int[9];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        //创建单链表
        Node.create(head, arr);
        //打印
        Node.print(head.next);

        System.out.println();

        //逆置
        //Node.reverse(head.next, head);


        /* 测试
         * 求倒数第n个
        Node flag = head;
        Node index = head;

        System.out.println(Node.getReciprocal(flag,index,2).data);
        */
        Node.print(head.next);



    }


}
