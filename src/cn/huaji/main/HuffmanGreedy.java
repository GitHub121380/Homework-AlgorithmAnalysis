package cn.huaji.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName HuffmanGreedy
 * @Description 哈夫曼编码的贪心
 * @author 滑技工厂 https://blog.csdn.net/qq_41718454
 * @date 2020/6/6
 * @version 1.0
 */
public class HuffmanGreedy implements Comparable {
    Bintree tree;
    float weight;
    String coding;

    public HuffmanGreedy(Bintree tree, float weight) {
        this.tree = tree;
        this.weight = weight;
    }

    @Override
    public int compareTo(Object x) {
        float w = ((HuffmanGreedy) x).weight;
        if (this.weight < w) return -1;
        if (this.weight == w) return 0;
        return 1;

    }

    /**
     * 二叉树类
     *
     * @author Lican
     */
    public static class Bintree {
        public Bintree Left;
        public Bintree Right;
        public String data;
        public String coding;

        public Bintree(Bintree l, Bintree r, String data) {
            this.Left = l;
            this.Right = r;
            this.data = data;
        }

        public String getCoding() {
            return coding;
        }

        public void setCoding(String coding) {
            this.coding = coding;
        }

    }

    /**
     * 创建Huffman树
     *
     * @param h
     * @return
     */
    public static HuffmanGreedy createHuffmanTree(List<HuffmanGreedy> h) {
        while (h.size() > 1) {
            Collections.sort(h);
            HuffmanGreedy h1 = h.get(0);
            HuffmanGreedy h2 = h.get(1);
            float w = h1.weight + h2.weight;
            Bintree b1 = h1.tree;
            Bintree b2 = h2.tree;
            Bintree b = new Bintree(b1, b2, "");
            HuffmanGreedy node = new HuffmanGreedy(b, w);
            h.remove(0);
            h.remove(0);
            h.add(node);
        }
        return h.get(0);
    }

    /**
     * 根据树来编码，左子树分配0，右子树分配1
     *
     * @param node
     * @param str
     */
    public static void process(Bintree node, String str) {
        //叶子结点
        if (node.Left == null) {
            node.setCoding(str);
            System.out.println(node.data + ": " + node.coding);
            return;
        }
        //对左子树分配代码"0"
        process(node.Left, str + "0");
        //对右子树分配代码"1"
        process(node.Right, str + "1");
    }

    public static void main(String[] args) {
        List<HuffmanGreedy> h = new ArrayList<HuffmanGreedy>();
        Bintree b1 = new Bintree(null, null, "A");
        HuffmanGreedy h1 = new HuffmanGreedy(b1, 40);
        h.add(h1);
        Bintree b2 = new Bintree(null, null, "B");
        HuffmanGreedy h2 = new HuffmanGreedy(b2, 8);
        h.add(h2);
        Bintree b3 = new Bintree(null, null, "C");
        HuffmanGreedy h3 = new HuffmanGreedy(b3, 10);
        h.add(h3);
        Bintree b4 = new Bintree(null, null, "D");
        HuffmanGreedy h4 = new HuffmanGreedy(b4, 30);
        h.add(h4);
        Bintree b5 = new Bintree(null, null, "E");
        HuffmanGreedy h5 = new HuffmanGreedy(b5, 10);
        h.add(h5);
        Bintree b6 = new Bintree(null, null, "F");
        HuffmanGreedy h6 = new HuffmanGreedy(b6, 2);
        h.add(h6);
        HuffmanGreedy root = createHuffmanTree(h);
        process(root.tree, "");
    }
}
/**
 * A: 0
 * D: 10
 * F: 1100
 * B: 1101
 * C: 1110
 * E: 1111
 */