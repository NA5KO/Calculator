import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class Calculator implements ActionListener {
    private JFrame frame;
    private JTextField resultField;
    private JButton[] buttons;
    private String[] buttonLabels = {
        "7", "8", "9", "/",
        "4", "5", "6", "*",
        "1", "2", "3", "-",
        "0", ".", "=", "+"
    };
    private double currentNumber = 0.0;
    private String currentOperator = "";
    private boolean newNumber = true;

    public Calculator() {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the text field to display the result
        resultField = new JTextField((50));
        resultField.setEditable(false);
        resultField.setBackground(Color.WHITE);
        resultField.setHorizontalAlignment(JTextField.RIGHT);

        // Create the buttons for the calculator
        buttons = new JButton[buttonLabels.length];
        for (int i = 0; i < buttonLabels.length; i++) {
            buttons[i] = new JButton(buttonLabels[i]);
            buttons[i].addActionListener(this);
        }

        // Create the button panel and add the buttons
        JPanel buttonPanel = new JPanel(new GridLayout(4, 4));
        for (JButton button : buttons) {
            buttonPanel.add(button);
        }

        // Create the top panel and add the result field
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel.add(resultField);
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if (actionCommand.equals("C")) {
            currentNumber = 0.0;
            currentOperator = "";
            resultField.setText("");
            newNumber = true;
        } else if (actionCommand.equals("+") ||
                   actionCommand.equals("-") ||
                   actionCommand.equals("*") ||
                   actionCommand.equals("/")) {
            // Apply the operator to the current number
            if (!currentOperator.equals("")) {
                calculate();
            }
            currentNumber = Double.parseDouble(resultField.getText());
            currentOperator = actionCommand;
            newNumber = true;
        } else if (actionCommand.equals("=")) {
            // Calculate the result and display it
            calculate();
            currentOperator = "";
            newNumber = true;
        } else {
            // Append the digit to the current number
            if (newNumber) {
                resultField.setText("");
                newNumber = false;
            }
            resultField.setText(resultField.getText() + actionCommand);
        }
    }

    private void calculate() {
        double newNumber = Double.parseDouble(resultField.getText());
        if (currentOperator.equals("+")) {
            currentNumber += newNumber;
        } else if (currentOperator.equals("-")) {
            currentNumber -= newNumber;
        } else if (currentOperator.equals("*")) {
            currentNumber *= newNumber;
        } else if (currentOperator.equals("/")) {
                currentNumber /= newNumber;
            
        }
        resultField.setText(Double.toString(currentNumber));
    }{
}
}