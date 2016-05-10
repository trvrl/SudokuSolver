package UI;

import model.Position;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Trevor on 5/7/2016.
 */
public class SolverUI {

    private static Map<Position, JFormattedTextField> sudokuGrid = new HashMap<>();

    public static void main(String[] args) {

        createGrid();

        JPanel buttonPanel = new JPanel();
        JPanel boardPanel = new JPanel();

        for (Map.Entry<Position, JFormattedTextField> entry : sudokuGrid.entrySet()) {
            boardPanel.add(entry.getValue());
        }

        JButton solveButton = new JButton("Solve");
        JButton clearButton = new JButton("Clear");

        buttonPanel.add(solveButton);
        buttonPanel.add(clearButton);

        JFrame sudokuFrame = new JFrame();
        sudokuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        sudokuFrame.add(boardPanel);
        sudokuFrame.setSize(1280, 720);
        sudokuFrame.setVisible(true);

    }

    private static void createGrid() {

        for (int row = 1; row < 10; row++) {
            for (int col = 1; col < 10; col++) {
                JFormattedTextField textField = new JFormattedTextField(" ");
                textField.setColumns(5);
                textField.setFocusLostBehavior(1);
                sudokuGrid.put(new Position(row, col), textField);
            }
        }
    }

}
