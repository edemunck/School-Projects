// Elizabeth DeMunck
// demun004

import org.junit.*;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.rules.Timeout;

public class MatrixEntryTest {

    @Rule
    public Timeout timeout = Timeout.seconds(5);
    private MatrixEntry mat;
    private final int row = 4;
    private final int col = 3;
    private final int data = 6;

    @Before
    public void setUp() {
        mat = new MatrixEntry(row, col, data);
    }

    @Test
    public void TestConstructor() {
        assertEquals("Row was incorrectly assigned",row,mat.getRow());
        assertEquals("Column was incorrectly assigned",col,mat.getColumn());
        assertEquals("Data was incorrectly assigned",data,mat.getData());
    }

    @Test
    public void testSetters() {
        int expected = 10;
        mat.setRow(expected);
        mat.setColumn(expected);
        mat.setData(expected);
        assertEquals("Incorrect row",expected,mat.getRow());
        assertEquals("Incorrect column",expected,mat.getColumn());
        assertEquals("incorrect data",expected,mat.getData());
    }

    @Test
    public void testGetAndSetNextRow() {
        MatrixEntry newMat = new MatrixEntry(row+1, col, data);
        mat.setNextRow(newMat);
        assertEquals(newMat,mat.getNextRow());
    }

    @Test
    public void testGetAndSetNextCol() {
        MatrixEntry newMat = new MatrixEntry(row, col+1, data);
        mat.setNextCol(newMat);
        assertEquals(newMat,mat.getNextCol());
    }

}