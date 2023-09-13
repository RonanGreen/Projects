package project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class GuiInsertProduct extends JFrame {

    private JTextField DateField;
    private JTextField ItemField;
    private JTextField CostField;
    private JButton submitButton;
    private JButton clearButton;

    

    public GuiInsertProduct() {
        super("Product Information Form");
        setLayout(null); // Set layout to null for absolute positioning

        DateField = new JTextField();
        ItemField = new JTextField();
        CostField = new JTextField();
        submitButton = new JButton("Submit");
        clearButton = new JButton("Clear");

        // Set bounds for JLabels, JTextFields, and JButtons
        int labelX = 30;
        int fieldX = 130;
        int rowHeight = 30;
        int startY = 30;

        add(new JLabel("Date of Sale:")).setBounds(labelX, startY, 100, 20);
        add(DateField).setBounds(fieldX, startY, 200, 20);
        startY += rowHeight;

        add(new JLabel("Item:")).setBounds(labelX, startY, 100, 20);
        add(ItemField).setBounds(fieldX, startY, 200, 20);
        startY += rowHeight;

        add(new JLabel("Cost:")).setBounds(labelX, startY, 100, 20);
        add(CostField).setBounds(fieldX, startY, 200, 20);
        startY += rowHeight;

        add(submitButton).setBounds(fieldX, startY, 100, 30);
        add(clearButton).setBounds(fieldX + 110, startY, 100, 30);

        ButtonHandler handler = new ButtonHandler();
        submitButton.addActionListener(handler);
        clearButton.addActionListener(handler);
    }

    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == submitButton) {
                String dateString = DateField.getText();
                String item = ItemField.getText();
                String cost = CostField.getText();

                double costCheck;
                try {
                	costCheck = Double.parseDouble(cost);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid cost format. Please enter a valid number.");
                    return;
                }
                Date Date = null;
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date parsedDate = dateFormat.parse(dateString);
                    Date = new Date(parsedDate.getTime());
                } catch (ParseException e) {
                    JOptionPane.showMessageDialog(null, "Invalid date format. Please enter date as YYYY-MM-DD");
                    return;
                }
                InsertProduct.insertProduct(costCheck, item, Date);

                JOptionPane.showMessageDialog(null, "Information submitted!");
            } else if (event.getSource() == clearButton) {
                DateField.setText("");
                ItemField.setText("");
                CostField.setText("");
            }
        }
    }

    public static void main(String[] args) {
        GuiInsertProduct gui = new GuiInsertProduct();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setSize(500, 200);
        gui.setVisible(true);
    }
}