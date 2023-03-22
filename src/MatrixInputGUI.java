import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MatrixInputGUI extends JFrame {
    private static JTextField[][] matrixFields;
    private JButton submitButton;
    private static double[][] matrix;

    public MatrixInputGUI(int rows, int cols) {
        super("Matrix Input");

        // Set up the GUI components
        matrixFields = new JTextField[rows][cols];
        JPanel matrixPanel = new JPanel(new GridLayout(rows, cols));
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrixFields[i][j] = new JTextField(5);
                matrixPanel.add(matrixFields[i][j]);
            }
        }
        submitButton = new JButton("Submit");
        submitButton.addActionListener(new SubmitListener());
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(submitButton);

        // Add the components to the frame
        getContentPane().add(matrixPanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        // Set up the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public double[][] getMatrix() {
        return matrix;
    }

    private static class SubmitListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Get the matrix data from the text fields
            matrix = new double[matrixFields.length][matrixFields[0].length];
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    matrix[i][j] = Double.parseDouble(matrixFields[i][j].getText());
                }
            }

            // close the MatrixInputGUI window
            ((JFrame)((JButton)e.getSource()).getTopLevelAncestor()).dispose();
        }
    }
}