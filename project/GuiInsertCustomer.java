package project;


import javax.swing.*;
import javax.swing.plaf.TextUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;


public class GuiInsertCustomer extends JFrame {
    private JTextField FirstNameField;
    private JTextField LastNameField;
    private JTextField DOBField;
    private JTextField AddressField;
    private JTextField emailField;
    private JTextField phoneField;
    private JButton submitButton;
    private JButton clearButton;

    private InsertCustomer insertcustomer = new InsertCustomer();

    public GuiInsertCustomer() {
        super("User Information Form");
        setLayout(null); // Set layout to null for absolute positioning

        FirstNameField = new JTextField();
        LastNameField = new JTextField();
        DOBField = new JTextField();
        AddressField = new JTextField();
        emailField = new JTextField();
        phoneField = new JTextField();
        submitButton = new JButton("Submit");
        clearButton = new JButton("Clear");

        // Set bounds for JLabels, JTextFields, and JButtons
        int labelX = 30;
        int fieldX = 130;
        int rowHeight = 30;
        int startY = 30;

        add(new JLabel("First Name:")).setBounds(labelX, startY, 100, 20);
        add(FirstNameField).setBounds(fieldX, startY, 200, 20);
        startY += rowHeight;

        add(new JLabel("Last Name:")).setBounds(labelX, startY, 100, 20);
        add(LastNameField).setBounds(fieldX, startY, 200, 20);
        startY += rowHeight;

        add(new JLabel("Date of Birth:")).setBounds(labelX, startY, 100, 20);
        add(DOBField).setBounds(fieldX, startY, 200, 20);
        startY += rowHeight;

        add(new JLabel("Address:")).setBounds(labelX, startY, 100, 20);
        add(AddressField).setBounds(fieldX, startY, 200, 20);
        startY += rowHeight;

        add(new JLabel("Email:")).setBounds(labelX, startY, 100, 20);
        add(emailField).setBounds(fieldX, startY, 200, 20);
        startY += rowHeight;

        add(new JLabel("Phone:")).setBounds(labelX, startY, 100, 20);
        add(phoneField).setBounds(fieldX, startY, 200, 20);
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
                String FirstName = FirstNameField.getText();
                String LastName = LastNameField.getText();
                String DOBString = DOBField.getText();
                String Address = AddressField.getText();
                String email = emailField.getText();
                String phone = phoneField.getText();

                // Convert the date string to a java.sql.Date object
                Date DOB = null;
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date parsedDate = dateFormat.parse(DOBString);
                    DOB = new Date(parsedDate.getTime());
                } catch (ParseException e) {
                    JOptionPane.showMessageDialog(null, "Invalid date format. Please enter date as YYYY-MM-DD");
                    return;
                }

                InsertCustomer.insertCustomer(FirstName, LastName, DOB, Address, email, phone);

                JOptionPane.showMessageDialog(null, "Information submitted!");
            } else if (event.getSource() == clearButton) {
                FirstNameField.setText("");
                LastNameField.setText("");
                DOBField.setText("");
                AddressField.setText("");
                emailField.setText("");
                phoneField.setText("");
            }
        }
    }

    public static void main(String[] args) {
        GuiInsertCustomer gui = new GuiInsertCustomer();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setSize(500, 300);
        gui.setVisible(true);
    }
}