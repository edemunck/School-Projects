// Elizabeth DeMunck
// demun004

//  PART V
// (a) For a linked list: 5m (because each element is storing references to 5 different things)
//     For a 2D array: N^2 (because an array stores N*N elements, whether they are zero or not)
// (b) The SparseIntMatrix implementation is more space effective, by around 9*10^9.
//     For the 2D array to be more efficient than the SparseIntMatrix, there would have to be
//        about 2*10^9 non-zero elements in the matrix.


import java.io.File;
import java.util.Scanner;

public class SparseIntMatrix {
    private int numRows, numCols;
    private MatrixEntry[] rows, cols;


    public SparseIntMatrix(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        rows = new MatrixEntry[numRows];
        cols = new MatrixEntry[numCols];
    }

    public SparseIntMatrix(int numRows, int numCols, String inputFile) {
        this.numRows = numRows;
        this.numCols = numCols;
        rows = new MatrixEntry[numRows];
        cols = new MatrixEntry[numCols];
        // file input
        File input = new File(inputFile);
        Scanner scan;
        try {
            scan = new Scanner(input);
        } catch(Exception e) {
            System.out.println("Error opening file");
            return;
        }
        // set up matrix
        int row,col,data;
        while(scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] str = line.split(",");
            row = Integer.valueOf(str[0]);
            col = Integer.valueOf(str[1]);
            data = Integer.valueOf(str[2]);
            setElement(row,col,data);
        }
    }


    /*
    * Method that returns the data variable associated with the given row, col input.
    * If the element does not exist, the result is 0
    *
    * @params row, col : integers representing the location of the data to be returned
    *
    * @returns the element corresponding with the given row and column
     */
    public int getElement(int row, int col) {
        int result = 0;
        MatrixEntry n = cols[col];
        if(cols[col] == null || rows[row] == null) {
            return result;
        }
        while(n!=null && n.getRow()!=row) {
            n = n.getNextRow();
        }
        if(n!=null && n.getRow()==row) {
            result = n.getData();
        }
        return result;
    }


    /*
    * Method that assigns the given data member to the list element at the given
    *   row and column. If the data is 0, it does not create a new MatrixEntry
    *   at the given row and column and returns false. Many, many null checks were made here.
    *
    * @params row, col, data : integers with the location of the new element to be added,
    *   integer of the data to be assigned to the new element
    *
    * @returns true if the element is valid and was added, false otherwise.
     */
    public boolean setElement(int row, int col, int data) {
        // true if row and column are in range
        // false otherwise
        if (data == 0 || row >= numRows || col >= numCols || row < 0 || col < 0) {
            return false;
        } else {
            MatrixEntry newEntry = new MatrixEntry(row, col, data);
            // new entry at given position
            if (rows[row] != null) {
                // if the row list at that entry is not empty
                MatrixEntry current = rows[row];
                MatrixEntry previous = null;
                while (current != null && current.getColumn() < col) {
                    // the current entry isn't null and the current
                    // column id is less than passed in column
                    previous = current;
                    current = current.getNextCol();
                }
                if (current != null && current.getColumn() == col) {
                    //element already exists, just set data
                    current.setData(data);
                } else if (previous == null) {
                    // set entry before first entry
                    // bc there is no previous entry
                    newEntry.setNextCol(rows[row]);
                    rows[row] = newEntry;
                } else if (current != null) {
                    // if the entry is in the middle
                    previous.setNextCol(newEntry);
                    newEntry.setNextCol(current);
                } else {  // if the entry is at the end
                    previous.setNextCol(newEntry);
                }
            } else {  // if the row node head is empty
                rows[row] = newEntry;
            }
            if (cols[col] == null) {
                cols[col] = newEntry;
            } else {
                MatrixEntry current = cols[col];
                MatrixEntry previous = null;
                while (current != null && current.getRow() < row) {
                    previous = current;
                    current = current.getNextRow();
                }
                if (current != null && current.getRow() == row) {
                    //element already exists
                    current.setData(data);
                } else if (previous == null) {
                    //entry before first entry
                    newEntry.setNextRow(cols[col]);
                    cols[col] = newEntry;
                } else if (current != null) {
                    // entry in middle
                    previous.setNextRow(newEntry);
                    newEntry.setNextRow(current);
                } else {
                    // entry at end
                    previous.setNextRow(newEntry);
                }
            }
            return true;
        }
    }


    /*
    * Method that removes a Matrix Entry at the given row and column. If the
    *   given row and column element does not exist or is not valid, it returns false.
    *
    *   @params row, col, data : integers with the location of the element to remove,
    *       data at the location that will be removed (not used)
    *
    *   @returns false if the input is invalid or the element doesn't exist, true otherwise.
     */
    public boolean removeElement(int row, int col, int data) {
        // false if out of range or element doesn't exist
        // true if it was there and was removed
        if(rows[row]==null || row>numRows || row<0) {  // element doesn't exist
            return false;
        }
        else if(cols[col]==null || col>numCols || col<0) {
            return false;
        }
        else {
            MatrixEntry current = rows[row];
            MatrixEntry previous = null;
            MatrixEntry next = null;
//         ______________________________________________________________________________
            // removing row linked list
            while (current != null && current.getColumn() < col) {
                previous = current;
                current = current.getNextCol();
            }
            if(current != null && current.getColumn()==col){
                if(current.getNextCol() == null && previous==null) {
                    // current is the only element
                    rows[row] = null;
                }
                else if(previous == null) {
                    // element is first element
                    rows[row] = current.getNextCol();
                }
                else {
                    // element is in the middle of the list
                    next = current.getNextCol();
                    previous.setNextCol(next);
                    current=null;
                }
            }
            else {
                // current = null or column>current.getColumn
                if(current != null && current.getColumn()!=col) {
                    // element doesn't exist
                    return false;
                }
                previous.setNextCol(null);
            }
//          _______________________________________________________________________________

            current = cols[col];
            previous = null;
            next = null;
//         ______________________________________________________________________________
            // removing col linked list
            while (current != null && current.getRow() < row) {
                previous = current;
                current = current.getNextRow();
            }
            if(current != null && current.getRow()==row){
                if(current.getNextRow() == null && previous==null) {
                    // current is the only element
                    cols[col] = null;
                }
                else if(previous == null) {
                    // element is first element
                    cols[col] = current.getNextRow();
                }
                else {
                    // element is in the middle of the list
                    next = current.getNextRow();
                    previous.setNextRow(next);
                    current=null;
                }
            }
            else {
                // goes here if current = null or column>current.getRow
                if(current != null && current.getRow()!=row) {
                    // element doesn't exist
                    return false;
                }
                previous.setNextRow(null);
            }
            return true;
//          _______________________________________________________________________________
        }
    }

    public int getNumCols() {
        return numCols;
    }
    public int getNumRows() {
        return numRows;
    }


    /*
    * Method that adds a given Sparse Integer Matrix to the current Sparse integer matrix
    *
    * @param otherMat : SparseIntMatrix element to be added to the current one
    *
    * @returns false if the given matrix does not have the same dimensions as the current one, true otherwise
     */
    public boolean plus(SparseIntMatrix otherMat) {
        if((otherMat.numRows != this.numRows) || (otherMat.numCols != this.numCols)) {
            return false;
        } else {
            for(int i = 0; i<numRows; i++) {
                for(int j = 0; j<numCols; j++) {
                        int data = this.getElement(i,j) + otherMat.getElement(i,j);
                        if(data<=0) {
                            removeElement(i,j,data);
                        } else {
                            this.setElement(i, j, data);
                        }
                }
            }
            return true;
        }
    }

    /*
    * Method that subtracts the given matrix from the current matrix at each element.
    *   If this returns a value less than or equal to zero it removes the element.
    *
    *   @param otherMat : SparseIntMatrix element to be subtracted from the current one
    *
    *   @returns false if dimensions are not the same, true if they are and the operation is successful
     */
    public boolean minus(SparseIntMatrix otherMat) {
        if((otherMat.numRows != this.numRows) || (otherMat.numCols != this.numCols)) {
            return false;
        } else {
            for(int i = 0; i<numRows; i++) {
                for(int j = 0; j<numCols; j++) {
                    int data = this.getElement(i,j) - otherMat.getElement(i,j);
                    if(data<=0) {
                        removeElement(i,j,data);
                    } else {
                        this.setElement(i, j, data);
                    }
                }
            }
            return true;
        }
    }

}
