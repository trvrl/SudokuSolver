package test;

import model.Board;
import model.Exceptions.UnsolveableException;
import model.SudokuSolver;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.statements.Fail;

import static org.junit.Assert.*;

/**
 * Created by Trevor on 5/5/2016.
 */
public class SolverTest {

    private Board testBlankBoard;
    private Board testBoard17;
    private Board testBoard20;
    private Board testBoard30;
    private Board testBoard79;
    private Board testBoard80;
    private Board testBoardInvalid80;
    private Board testBoardSolved;
    private Board testBoardSolved17;
    private Board testBoardSolved30;

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

        Integer[] boardSolved17 = {
                2, 6, 4, 7, 1, 5, 8, 3, 9,
                1, 3, 7, 8, 9, 2, 6, 4, 5,
                5, 9, 8, 4, 3, 6, 2, 7, 1,
                4, 2, 3, 1, 7, 8, 5, 9, 6,
                9, 1, 6, 5, 4, 9, 7, 2, 3,
                7, 5, 9, 6, 2, 3, 4, 1, 8,
                3, 7, 5, 2, 8, 1, 9, 6, 4,
                9, 8, 2, 3, 6, 4, 1, 5, 7,
                6, 4, 1, 9, 5, 7, 3, 8, 2};
        testBoardSolved17 = new Board(boardSolved17);

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

        Integer[] board30 = {
                0, 0, 7, 4, 1, 2, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 4, 0, 3, 0, 9, 0, 6, 5,
                0, 0, 5, 0, 0, 8, 7, 0, 1,
                4, 1, 0, 0, 0, 0, 0, 5, 8,
                2, 0, 8, 1, 0, 0, 9, 0, 0,
                9, 8, 0, 6, 0, 1, 0, 2, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 7, 9, 3, 5, 0, 0};
        testBoard30 = new Board(board30);

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

        Integer[] board80 = {
                8, 2, 4, 9, 5, 3, 6, 7, 1,
                6, 3, 5, 8, 1, 7, 9, 2, 4,
                7, 1, 9, 6, 2, 4, 8, 5, 3,
                5, 8, 0, 2, 9, 1, 3, 4, 6,
                1, 4, 2, 7, 3, 6, 5, 8, 9,
                3, 9, 6, 4, 8, 5, 2, 1, 7,
                2, 6, 1, 5, 4, 9, 7, 3, 0,
                4, 7, 8, 3, 6, 2, 1, 9, 5,
                9, 5, 3, 1, 7, 8, 4, 6, 2};
        testBoard80 = new Board(board80);

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

        Integer[] boardSolved30 = {
                5, 6, 7, 4, 1, 2, 8, 9, 3,
                1, 3, 9, 5, 8, 6, 4, 7, 2,
                8, 4, 2, 3, 7, 9, 1, 6, 5,
                3, 9, 5, 2, 6, 8, 7, 4, 1,
                4, 1, 6, 9, 3, 7, 2, 5, 8,
                2, 7, 8, 1, 4, 5, 9, 3, 6,
                9, 8, 4, 6, 5, 1, 3, 2, 7,
                7, 5, 3, 8, 2, 4, 6, 1, 9,
                6, 2, 1, 7, 9, 3, 5, 8, 4};
        testBoardSolved30 = new Board(boardSolved30);
    }

    @Test (expected = UnsolveableException.class)
    public void testBlankSolve() throws UnsolveableException {
        SudokuSolver.setCurrent(testBlankBoard);
        SudokuSolver.solve();
    }

    @Test
    public void testBoardSolved() {
        SudokuSolver.setCurrent(testBoardSolved);
        Board solution = null;
        try {
            solution = SudokuSolver.solve();
        } catch (UnsolveableException e) {
            Assert.fail();
        }
        assertEquals(testBoardSolved, solution);
    }

    @Test (expected = UnsolveableException.class)
    public void testInvalidSolve() throws UnsolveableException {
        SudokuSolver.setCurrent(testBoardInvalid80);
        SudokuSolver.solve();
    }

    @Test
    public void testEasySolve() {
        Board solution = null;
        try {
            solution = SudokuSolver.solve(testBoard79);
        } catch (UnsolveableException e) {
            Assert.fail();
        }
        assertEquals(testBoardSolved, solution);
    }

    @Test
    public void testMedSolve() {
        Board solution = null;
        try {
            solution = SudokuSolver.solve(testBoard30);
        } catch (UnsolveableException e) {
            Assert.fail();
        }
        assertEquals(testBoardSolved30, solution);
    }

    @Test
    public void testHardSolve() {
        Board solution = null;
        try {
            solution = SudokuSolver.solve(testBoard17);
        } catch (UnsolveableException e) {
            System.out.println(e.getMessage());
        }
        assertEquals(testBoardSolved17, solution);
    }

    @Test
    public void testGenerateBoards() {
        Integer[] board1 = {
                1, 0, 0, 7, 0, 0, 0, 0, 0,
                1, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 4, 3, 0, 2, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 6,
                0, 0, 0, 5, 0, 9, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 4, 1, 8,
                0, 0, 0, 0, 8, 1, 0, 0, 0,
                0, 0, 2, 0, 0, 0, 0, 5, 0,
                0, 4, 0, 0, 0, 0, 3, 0, 0};
        Board testBoard1 = new Board(board1);

        assertFalse(testBoard1.isValid());

        Integer[] board2 = {
                2, 0, 0, 7, 0, 0, 0, 0, 0,
                1, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 4, 3, 0, 2, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 6,
                0, 0, 0, 5, 0, 9, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 4, 1, 8,
                0, 0, 0, 0, 8, 1, 0, 0, 0,
                0, 0, 2, 0, 0, 0, 0, 5, 0,
                0, 4, 0, 0, 0, 0, 3, 0, 0};
        Board testBoard2 = new Board(board2);

        assertTrue(testBoard2.isValid());

        Integer[] board3 = {
                3, 0, 0, 7, 0, 0, 0, 0, 0,
                1, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 4, 3, 0, 2, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 6,
                0, 0, 0, 5, 0, 9, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 4, 1, 8,
                0, 0, 0, 0, 8, 1, 0, 0, 0,
                0, 0, 2, 0, 0, 0, 0, 5, 0,
                0, 4, 0, 0, 0, 0, 3, 0, 0};
        Board testBoard3 = new Board(board3);

        assertTrue(testBoard3.isValid());

        Integer[] board7 = {
                7, 0, 0, 7, 0, 0, 0, 0, 0,
                1, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 4, 3, 0, 2, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 6,
                0, 0, 0, 5, 0, 9, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 4, 1, 8,
                0, 0, 0, 0, 8, 1, 0, 0, 0,
                0, 0, 2, 0, 0, 0, 0, 5, 0,
                0, 4, 0, 0, 0, 0, 3, 0, 0};
        Board testBoard7 = new Board(board7);

        assertFalse(testBoard7.isValid());
    }
}
