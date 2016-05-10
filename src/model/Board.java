package model;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by Trevor on 5/2/2016.
 */
public class Board implements Iterable<Integer> {

    private List<Integer> board;

    public Board() {
        Integer[] empty = {
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0};
        board = new ArrayList<>(Arrays.asList(empty));
    }

    public Board(Integer[] b) {
        board = new ArrayList<>(Arrays.asList(b));
    }

    public Board(List<Integer> board) {
        this.board = board;
    }

    public static int posnToIndex(Position p) {
        return (p.getRow() - 1) * 9 + (p.getCol() - 1);
    }

    public static Position indexToPosn(int index) {
        return new Position((index / 9) + 1, (index % 9) + 1);
    }

    public static int indexToRow(int index) {
        return (index / 9) + 1;
    }

    public static int indexToCol(int index) {
        return (index % 9) + 1;
    }

    public int getVal(Position p) {
        return board.get(posnToIndex(p));
    }

    public void putVal(Position p, int val) {
        board.set(posnToIndex(p), val);
    }

    public void addRow(int row, ArrayList<Integer> numbers) {
        for (int i = 0; i < numbers.size(); i++) {
            putVal(new Position(row, i + 1), numbers.get(i));
            //putVal(row, i + 1, numbers.get(i));
        }
    }

    public void addColumn(int col, ArrayList<Integer> numbers) {
        for (int i = 0; i < numbers.size(); i++) {
            putVal(new Position(i + 1, col), numbers.get(i));
            //putVal(i + 1, col, numbers.get(i));
        }
    }

    public List<Integer> getRow(int row) {
        List<Integer> rowList = board.subList(
                posnToIndex(new Position(row, 1)), posnToIndex(new Position(row, 10)));
        return Collections.unmodifiableList(rowList);
    }

    public List<Integer> getColumn(int col) {
        List<Integer> colList = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            colList.add(getVal(new Position(i + 1, col)));
        }
        return colList;
    }

    public List<Integer> getSquare(int sqr) {

        int hStart;
        int hEnd;
        int vStart;
        int vEnd;

        switch (sqr) {
            case 1:
                hStart = 0;
                hEnd = 2;
                vStart = 0;
                vEnd = 2;
                break;
            case 2:
                hStart = 3;
                hEnd = 5;
                vStart = 0;
                vEnd = 2;
                break;
            case 3:
                hStart = 6;
                hEnd = 8;
                vStart = 0;
                vEnd = 2;
                break;
            case 4:
                hStart = 0;
                hEnd = 2;
                vStart = 3;
                vEnd = 5;
                break;
            case 5:
                hStart = 3;
                hEnd = 5;
                vStart = 3;
                vEnd = 5;
                break;
            case 6:
                hStart = 6;
                hEnd = 8;
                vStart = 3;
                vEnd = 5;
                break;
            case 7:
                hStart = 0;
                hEnd = 2;
                vStart = 6;
                vEnd = 8;
                break;
            case 8:
                hStart = 3;
                hEnd = 5;
                vStart = 6;
                vEnd = 8;
                break;
            case 9:
                hStart = 6;
                hEnd = 8;
                vStart = 6;
                vEnd = 8;
                break;
            default:
                hStart = 0;
                hEnd = 8;
                vStart = 0;
                vEnd = 8;
                break;
        }

        List<Integer> sqrList = new ArrayList<>();

        for (int row = vStart; row <= vEnd; row++) {
            for (int col = hStart; col <= hEnd; col++) {
                sqrList.add(getVal(new Position(row + 1, col + 1)));
            }
        }

        return sqrList;
    }

    public List<Integer> getBoard() {
        return new ArrayList<>(board);
    }

    public boolean hasEnoughClues() {
        int numClues = 0;
        for (Integer num : this) {
            if (num != 0) numClues++;
        }
        return numClues >= 17;
    }

    public boolean hasEmptySpace() {
        return board.contains(0);
    }

    public boolean isSolved() {
        if (hasEmptySpace()) return false;

        Integer[] d = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        List<Integer> digits = new ArrayList<>(Arrays.asList(d));

        for (int i = 0; i < 9; i++) {
            if (!getRow(i + 1).containsAll(digits)) return false;
            if (!getColumn(i + 1).containsAll(digits)) return false;
            if (!getSquare(i + 1).containsAll(digits)) return false;
        }

        return true;
    }

    public boolean isValid() {
        for (int i = 0; i < 9; i++) {
            if (hasDuplicate(getRow(i + 1)) ||
                    hasDuplicate(getColumn(i + 1)) ||
                    hasDuplicate(getSquare(i + 1))) return false;
        }

        return true;
    }

    public Position nextBlank() {
        for (int i = 0; i < board.size(); i++) {
            if (getVal(indexToPosn(i)) == 0) return indexToPosn(i);
        }

        return null;
    }

    public static int countFilled(List<Integer> unit) {
        int nonZeroCount = 0;

        for (Integer i : unit) {
            if (i != 0) nonZeroCount++;
        }

        return nonZeroCount;
    }

    public static boolean hasDuplicate(List<Integer> unit) {
        for (Integer i : unit) {
            int count = 0;
            if (i == 0) continue;
            for (Integer j : unit) {
                if (i.equals(j)) count++;
            }
            if (count > 1) return true;
        }
        return false;
    }

    public Iterator<Integer> iterator() {
        return board.iterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Board integers = (Board) o;

        return board.equals(integers.board);

    }

    @Override
    public int hashCode() {
        return board.hashCode();
    }
}
