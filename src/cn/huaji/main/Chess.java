package cn.huaji.main;

/**
 * @ClassName Chess
 * @Description 棋盘覆盖问题
 * @author 滑技工厂 https://blog.csdn.net/qq_41718454
 * @date 2020/3/13
 * @version 1.0
 */
public class Chess {
    //表示棋盘
    private int[][] board;
    //表示棋盘的大小为2的多少次方
    private int boardSize;
    //棋盘中特殊方格的位置（行号，列号）
    private int dr, dc;
    //骨牌标号
    private int tile;

    public Chess() {
        board = new int[1][1];
        dr = 0;
        dc = 0;
        boardSize = 0;
    }

    //s为指数
    public Chess(int tr, int tc, int s) {
        //n为阶数
        int n = (int) Math.pow(2, s);
        if (n <= tr || n <= tc)
            System.out.println("初始化参数错误！");
        else {
            board = new int[n][n];
            dr = tr;
            dc = tc;
            boardSize = s;
        }
    }

    /*
     * @Title print
     * @Description 打印棋盘
     * @author 滑技工厂
     * @Date 2020/3/13
     * @param []
     * @return void
     * @throws
     */
    public void print() {
        for (int i = 0; i < Math.pow(2, this.boardSize); i++) {
            for (int j = 0; j < Math.pow(2, this.boardSize); j++) {
                System.out.print(String.format("%3d", this.board[i][j]));
            }
            System.out.println();
        }
    }

    /*
     * @Title chessBoard
     * @Description 划分L骨牌分布具体情况
     *              tr 棋盘左上角方格的行号
     *              tc 棋盘左上角的列号
     *              dr 特殊方格所在行号
     *              dc 特殊方格所在列号
     *              size 2^k棋盘规格
     * @author 滑技工厂
     * @Date 2020/3/13
     * @param [tr, tc, dr, dc, size]
     * @return void
     * @throws
     */
    public void chessBoard(int tr, int tc, int dr, int dc, int size) {
        if (size == 1)
            return;

        int t = tile++;
        int s = size / 2;

        //覆盖左上角子棋盘
        if (dr < tr + s && dc < tc + s)//特殊方格在这个里面
            chessBoard(tr, tc, dr, dc, s);
        else {//无特殊方块，用t号L骨牌覆盖右下角
            board[tr + s - 1][tc + s - 1] = t;
            //覆盖其余方格
            chessBoard(tr, tc, tr + s - 1, tc + s - 1, s);
        }

        //覆盖右上角子棋盘
        if (dr < tr + s && dc >= tc + s)//特殊方格在这个里面
            chessBoard(tr, tc + s, dr, dc, s);
        else {//无特殊方块，用t号L骨牌覆盖左下角
            board[tr + s - 1][tc + s] = t;
            chessBoard(tr, tc + s, tr + s - 1, tc + s, s);
        }

        //覆盖左下角子棋盘
        if (dr >= tr + s && dc < tc + s)//特殊方格在这个里面
            chessBoard(tr + s, tc, dr, dc, s);
        else {//无特殊方块，用t号L骨牌覆盖右上角
            board[tr + s][tc + s - 1] = t;
            chessBoard(tr + s, tc, tr + s, tc + s - 1, s);
        }

        //覆盖右下角子棋盘
        if (dr >= tr + s && dc >= tc + s)//特殊方格在这个里面
            chessBoard(tr + s, tc + s, dr, dc, s);
        else {//无特殊方块，用t号L骨牌覆盖左上角
            board[tr + s][tc + s] = t;
            chessBoard(tr + s, tc + s, tr + s, tc + s, s);
        }

    }


    public static void main(String[] args) {

        Chess chess = new Chess(3, 4, 3);
        chess.chessBoard(0, 0, chess.dr, chess.dc, (int) Math.pow(2, chess.boardSize));
        chess.print();

    }
}
