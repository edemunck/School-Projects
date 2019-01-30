// Elizabeth DeMunck
// demun004

public class MatrixEntry {
    private int col, row, data;
    private MatrixEntry nextRow, nextCol;
    // 'this' is head, nextRow is link to next row node, nextCol is link to next col node

    // constructor
    public MatrixEntry(int row, int col, int data) {
        this.col = col;
        this.row = row;
        this.data = data;
    }

    // getters and setters
    public int getColumn() {
        return col;
    }

    public void setColumn(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public MatrixEntry getNextRow() {
        return nextRow;
    }

    public void setNextRow(MatrixEntry el) {
        nextRow = el;
    }

    public MatrixEntry getNextCol() {
        return nextCol;
    }

    public void setNextCol(MatrixEntry el) {
        nextCol = el;
    }
}
