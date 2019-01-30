// Elizabeth DeMunck
// demun004

public class testSparseInt {

    public static void main(String[] args) {
        SparseIntMatrix matrix1 = new SparseIntMatrix(800,800,"matrix1_data.txt");
        SparseIntMatrix matrix2 = new SparseIntMatrix(800,800,"matrix2_data.txt");
        SparseIntMatrix otherMat = new SparseIntMatrix(800,800,"matrix2_noise.txt");
        MatrixViewer.show(matrix1);
        matrix2.minus(otherMat);
        MatrixViewer.show(matrix2);
    }
}
