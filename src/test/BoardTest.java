package test;

import model.Board;
import model.Position;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Trevor on 5/2/2016.
 */
public class BoardTest {

    private Board testBlankBoard;
    private Board testBoard17;
    private Board testBoard20;
    private Board testBoard79;
    private Board testBoardInvalid80;
    private Board testBoardSolved;

    @Before
    public void setUp() {
        testBlankBoard = new Board();

        Integer[] board17 = {
                0, 0, 0, 7, 0, 0, 0, 0, 0,
                1, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 4, 3, 0, 2, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 6,
                0, 0, 0, 5, 0, 9, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 4, 1, 8,
                0, 0, 0, 0, 8, 1, 0, 0, 0,
                0, 0, 2, 0, 0, 0, 0, 5, 0,
                0, 4, 0, 0, 0, 0, 3, 0, 0};
        testBoard17 = new Board(board17);

        Integer[] board20 = {
                3, 5, 9, 7, 0, 0, 0, 0, 0,
                1, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 4, 3, 0, 2, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 6,
                0, 0, 0, 5, 0, 9, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 4, 1, 8,
                0, 0, 0, 0, 8, 1, 0, 0, 0,
                0, 0, 2, 0, 0, 0, 0, 5, 0,
                0, 4, 0, 0, 0, 0, 3, 0, 0};
        testBoard20 = new Board(board20);

        Integer[] board79 = {
                8, 2, 4, 9, 5, 3, 6, 7, 1,
                6, 3, 5, 8, 1, 7, 9, 2, 4,
                7, 1, 9, 6, 2, 4, 8, 5, 3,
                5, 8, 0, 2, 9, 1, 3, 4, 6,
                1, 4, 2, 7, 3, 6, 5, 8, 9,
                3, 9, 6, 4, 8, 5, 2, 1, 7,
                2, 6, 1, 5, 4, 9, 7, 3, 0,
                4, 7, 8, 3, 6, 2, 1, 9, 5,
                9, 5, 3, 1, 7, 8, 4, 6, 2};
        testBoard79 = new Board(board79);

        Integer[] boardInvalid80 = {
                8, 2, 4, 9, 5, 3, 6, 7, 1,
                6, 3, 5, 8, 1, 7, 9, 2, 4,
                7, 1, 9, 6, 2, 4, 8, 5, 3,
                5, 8, 0, 2, 9, 1, 3, 4, 6,
                1, 4, 2, 7, 3, 6, 5, 8, 9,
                3, 9, 6, 4, 8, 5, 2, 1, 7,
                2, 6, 1, 5, 4, 9, 7, 3, 4,
                4, 7, 8, 3, 6, 2, 1, 9, 5,
                9, 5, 3, 1, 7, 8, 4, 6, 2};
        testBoardInvalid80 = new Board(boardInvalid80);

        Integer[] boardSolved = {
                8, 2, 4, 9, 5, 3, 6, 7, 1,
                6, 3, 5, 8, 1, 7, 9, 2, 4,
                7, 1, 9, 6, 2, 4, 8, 5, 3,
                5, 8, 7, 2, 9, 1, 3, 4, 6,
                1, 4, 2, 7, 3, 6, 5, 8, 9,
                3, 9, 6, 4, 8, 5, 2, 1, 7,
                2, 6, 1, 5, 4, 9, 7, 3, 8,
                4, 7, 8, 3, 6, 2, 1, 9, 5,
                9, 5, 3, 1, 7, 8, 4, 6, 2};
        testBoardSolved = new Board(boardSolved);
    }

    @Test
    public void testConvert() {
        assertEquals(2, Board.indexToRow(17));
        assertEquals(9, Board.indexToCol(17));
        assertEquals(17, Board.posnToIndex(new Position(2, 9)));

        assertEquals(5, Board.indexToRow(40));
        assertEquals(5, Board.indexToCol(40));
        assertEquals(40, Board.posnToIndex(new Position(5, 5)));
    }

    @Test
    public void testConstructor() {
        assertEquals(81, testBlankBoard.getBoard().size());
    }

    @Test
    public void testPutVal() {
        testBlankBoard.putVal(new Position(1, 1), 8);
        assertEquals(81, testBlankBoard.getBoard().size());
        assertEquals(8, testBlankBoard.getVal(new Position(1, 1)));
    }

    @Test
    public void testPutManyVal() {
        testBlankBoard.putVal(new Position(1, 1), 8);
        testBlankBoard.putVal(new Position(1, 2), 9);
        testBlankBoard.putVal(new Position(9, 9), 1);
        assertEquals(81, testBlankBoard.getBoard().size());

        assertEquals(8, testBlankBoard.getVal(new Position(1, 1)));
        assertEquals(9, testBlankBoard.getVal(new Position(1, 2)));
        assertEquals(1, testBlankBoard.getVal(new Position(9, 9)));
    }

    @Test
    public void testAddRow() {
        Integer[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        testBlankBoard.addRow(2, new ArrayList<>(Arrays.asList(numbers)));

        for (int i = 0; i < numbers.length; i++) {
            assertEquals(numbers[i].intValue(),
                    testBlankBoard.getVal(new Position(2, i + 1)));
        }
    }

    @Test
    public void testAddColumn() {
        Integer[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        testBlankBoard.addColumn(3, new ArrayList<>(Arrays.asList(numbers)));

        for (int i = 0; i < numbers.length; i++) {
            assertEquals(numbers[i].intValue(),
                    testBlankBoard.getVal(new Position(i + 1, 3)));
        }
    }

    @Test
    public void testEnoughClues() {
        assertFalse(testBlankBoard.hasEnoughClues());
        assertTrue(testBoard17.hasEnoughClues());
    }

    @Test
    public void testGetRow() {
        Integer[] row = {0, 0, 0, 0, 0, 0, 4, 1, 8};
        List<Integer> testRow = new ArrayList<>(Arrays.asList(row));

        assertEquals(testRow, testBoard17.getRow(6));
    }

    @Test
    public void testGetColumn() {
        Integer[] col = {7, 0, 4, 0, 5, 0, 0, 0, 0};
        List<Integer> testCol = new ArrayList<>(Arrays.asList(col));

        assertEquals(testCol, testBoard17.getColumn(4));
    }

    @Test
    public void testHasEmptySpace() {
        assertTrue(testBlankBoard.hasEmptySpace());
        assertTrue(testBoard17.hasEmptySpace());
        assertFalse(testBoardSolved.hasEmptySpace());
    }

    @Test
    public void testGetSquare() {
        Integer[] sqr1 = {8, 2, 4, 6, 3, 5, 7, 1, 9};
        List<Integer> testSqr1 = new ArrayList<>(Arrays.asList(sqr1));

        assertEquals(testSqr1, testBoardSolved.getSquare(1));

        Integer[] sqr5 = {2, 9, 1, 7, 3, 6, 4, 8, 5};
        List<Integer> testSqr5 = new ArrayList<>(Arrays.asList(sqr5));

        assertEquals(testSqr5, testBoardSolved.getSquare(5));
    }

    @Test
    public void testIsSolved() {
        assertFalse(testBlankBoard.isSolved());
        assertFalse(testBoard17.isSolved());
        assertTrue(testBoardSolved.isSolved());
    }

    @Test
    public void testNextBlank() {
        assertEquals(new Position(1, 1), testBlankBoard.nextBlank());
        assertEquals(new Position(1, 1), testBoard17.nextBlank());
        assertEquals(new Position(1, 5), testBoard20.nextBlank());
        assertEquals(new Position(4, 3), testBoard79.nextBlank());
        assertEquals(null, testBoardSolved.nextBlank());
    }

    @Test
    public void testIsValid() {
        assertTrue(testBlankBoard.isValid());
        assertTrue(testBoard17.isValid());
        assertTrue(testBoard20.isValid());
        assertTrue(testBoard79.isValid());
        assertTrue(testBoardSolved.isValid());
        assertFalse(testBoardInvalid80.isValid());
    }

    @Test
    public void testCountFilled() {
        assertEquals(8, Board.countFilled(testBoard79.getRow(7)));
    }

    @Test
    public void testHasDupe() {
        assertTrue(Board.hasDuplicate(testBoardInvalid80.getColumn(9)));
        assertFalse(Board.hasDuplicate(testBoardSolved.getColumn((9))));
    }
}
