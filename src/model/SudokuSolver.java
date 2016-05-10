package model;

import model.Exceptions.UnsolveableException;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Trevor on 5/4/2016.
 */
public class SudokuSolver {

    private static Board current;
    private static List<Board> todo = new LinkedList<>();

    public static void main(String[] args) {
        try {
            solve();
        } catch (UnsolveableException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Board solve() throws UnsolveableException {
        Board board = current;
        if (!board.hasEnoughClues()) {
            throw new UnsolveableException("Not enough clues!");
        } else if (board.isSolved()) {
            return current;
        } else {
            generateBoards(board);
            if (todo.isEmpty()) throw new UnsolveableException();
            current = todo.get(0);
            todo.remove(0);
            solve();
        }

        return current;
    }

    public static Board solve(Board given) throws UnsolveableException {
        return solveBoard(given);
    }

    public static Board solveBoard(Board given) throws UnsolveableException {
        if (given.isSolved()) {
            return given;
        } else if (!given.hasEnoughClues()) {
            throw new UnsolveableException("Not enough clues!");
        } else {
            return solveNextBoards(generateBoards(given));
        }
    }

    public static Board solveNextBoards(List<Board> nextBoards) throws UnsolveableException {
        if (nextBoards.isEmpty()) {
            throw new UnsolveableException("No Legal Moves Left!");
        } else {
            try {
                return solveBoard(nextBoards.get(0));
            } catch (UnsolveableException e) {
                return solveNextBoards(nextBoards.subList(1, nextBoards.size()));
            } catch (NullPointerException e) {
                System.out.println(nextBoards.get(0).getBoard().toString());
                return null;
            }
        }

    }

    public static List<Board> generateBoards(Board current) throws UnsolveableException {
        if (!current.hasEmptySpace()) throw new UnsolveableException("No more blanks!");

        Position blankPosn = current.nextBlank();
        List<Board> nextBoards = new ArrayList<>();

        for (int d = 1; d <= 9 ; d++) {
            Board nextBoard = new Board(current.getBoard());
            nextBoard.putVal(blankPosn, d);
            if (nextBoard.isValid()) nextBoards.add(nextBoard);
        }

        return nextBoards;

    }

    public static void setCurrent(Board board) {
        current = board;
    }

}
