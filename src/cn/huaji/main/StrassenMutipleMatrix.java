package cn.huaji.main;

/**
 * @ClassName StrassenMutipleMatrix
 * @Description strassen矩阵乘法
 * @author 滑技工厂 https://blog.csdn.net/qq_41718454
 * @date 2020/3/14
 * @version 1.0
 */
public class StrassenMutipleMatrix {

    /*
     * @Title mutipleMatrix
     * @Description 普通的矩阵乘法
     * @author 滑技工厂
     * @Date 2020/3/14
     * @param [matrix1, matrix2, result, row1, col1, col2]
     * @return void
     * @throws
     */
    public static void mutipleMatrix(int[][] matrix1, int[][] matrix2,
                                     int[][] result, int row1, int col1, int col2) {
        for (int i = 0; i < row1; i++)
            for (int k = 0; k < col2; k++)
                for (int j = 0; j < col1; j++)
                    result[i][k] += matrix1[i][j] * matrix2[j][k];
    }

    /*
     * @Title matrixSub
     * @Description 矩阵减法
     * @author 滑技工厂
     * @Date 2020/3/14
     * @param [matrixA, matrixB, result]
     * @return void
     * @throws
     */
    public static void matrixSub(int[][] matrixA, int[][] matrixB, int[][] result){
        for(int i = 0; i < matrixA.length; i++)
            for(int j = 0; j < matrixA.length; j++)
                result[i][j] = matrixA[i][j] - matrixB[i][j];
    }
    /*
     * @Title matrixAdd
     * @Description 矩阵加法
     * @author 滑技工厂
     * @Date 2020/3/14
     * @param [matrixA, matrixB, result]
     * @return void
     * @throws
     */
    public static void matrixAdd(int[][] matrixA, int[][] matrixB, int[][] result){
        for(int i = 0; i < matrixA.length; i++)
            for(int j = 0; j < matrixA.length; j++)
                result[i][j] = matrixA[i][j] + matrixB[i][j];
    }
    /*
     * @Title Strassen
     * @Description Strassen矩阵乘法
     * @author 滑技工厂
     * @Date 2020/3/14
     * @param [N, matrixA, matrixB, result]
     * @return void
     * @throws
     */
    public static void Strassen(int N, int[][] matrixA, int[][] matrixB, int[][] result){
        if(N == 1){
            result[0][0] = matrixA[0][0] * matrixB[0][0];
            return;
        }
        int halfSize = N / 2;
        int[][] A = new int[halfSize][halfSize];
        int[][] B = new int[halfSize][halfSize];
        int[][] C = new int[halfSize][halfSize];
        int[][] D = new int[halfSize][halfSize];
        int[][] E = new int[halfSize][halfSize];
        int[][] F = new int[halfSize][halfSize];
        int[][] G = new int[halfSize][halfSize];
        int[][] H = new int[halfSize][halfSize];
        int[][] C1 = new int[halfSize][halfSize];
        int[][] C2 = new int[halfSize][halfSize];
        int[][] C3 = new int[halfSize][halfSize];
        int[][] C4 = new int[halfSize][halfSize];

        int[][] P1 = new int[halfSize][halfSize];
        int[][] P2 = new int[halfSize][halfSize];
        int[][] P3 = new int[halfSize][halfSize];
        int[][] P4 = new int[halfSize][halfSize];
        int[][] P5 = new int[halfSize][halfSize];
        int[][] P6 = new int[halfSize][halfSize];
        int[][] P7 = new int[halfSize][halfSize];

        int[][] tempA = new int[halfSize][halfSize];
        int[][] tempB = new int[halfSize][halfSize];
        for(int i = 0; i < halfSize; i++)
            for(int j = 0; j < halfSize; j++){
                A[i][j] = matrixA[i][j];
                B[i][j] = matrixA[i][halfSize + j];
                C[i][j] = matrixA[i + halfSize][j];
                D[i][j] = matrixA[i + halfSize][j + halfSize];

                E[i][j] = matrixB[i][j];
                F[i][j] = matrixB[i][halfSize + j];
                G[i][j] = matrixB[i + halfSize][j];
                H[i][j] = matrixB[i + halfSize][j + halfSize];
            }
        matrixSub(F,H,tempB);
        Strassen(halfSize,A,tempB,P1);

        matrixAdd(A,B,tempA);
        Strassen(halfSize,tempA,H,P2);

        matrixAdd(C,D,tempA);
        Strassen(halfSize,tempA,E,P3);

        matrixSub(G,E,tempB);
        Strassen(halfSize,D,tempB,P4);

        matrixAdd(A,D,tempA);
        matrixAdd(E,H,tempB);
        Strassen(halfSize,tempA,tempB,P5);

        matrixSub(B,D,tempA);
        matrixAdd(G,H,tempB);
        Strassen(halfSize,tempA,tempB,P6);

        matrixSub(A,C,tempA);
        matrixAdd(E,F,tempB);
        Strassen(halfSize,tempA,tempB,P7);

        matrixAdd(P5,P4,C1);
        matrixSub(C1,P2,C1);
        matrixAdd(C1,P6,C1);

        matrixAdd(P1,P2,C2);

        matrixAdd(P3,P4,C3);

        matrixAdd(P5,P1,C4);
        matrixSub(C4,P3,C4);
        matrixSub(C4,P7,C4);

        for(int i = 0; i < halfSize; i++)
            for(int j = 0; j < halfSize; j++){
                result[i][j] = C1[i][j];
                result[i][j + halfSize] = C2[i][j];
                result[i + halfSize][j] = C3[i][j];
                result[i + halfSize][j + halfSize] = C4[i][j];
            }
    }
}
