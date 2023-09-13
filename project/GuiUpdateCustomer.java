package project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class GuiUpdateCustomer extends JFrame {
    private JTextField IDField;
    private JTextField FirstNameField;
    private JTextField LastNameField;
    private JTextField DOBField;
    private JTextField AddressField;
    private JTextField emailField;
    private JTextField phoneField;
    private JButton updateButton;

    public GuiUpdateCustomer() {
        super("Update Customer");
        setLayout(null);
        
        int labelX = 30;
        int fieldX = 130;
        int rowHeight = 30;
        int startY = 30;

        IDField = new JTextField();
        FirstNameField = new JTextField();
        LastNameField = new JTextField();
        DOBField = new JTextField();
        AddressField = new JTextField();
        emailField = new JTextField();
        phoneField = new JTextField();

        add(new JLabel("ID:")).setBounds(labelX, startY, 100, 20);
        add(IDField).setBounds(fieldX, startY, 200, 20);
        startY += rowHeight;

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

        updateButton = new JButton("Update");
        updateButton.setBounds(180, 260, 100, 30);
        add(updateButton);

      ButtonHandler handler = new ButtonHandler();
        updateButton.addActionListener(handler);
    }

    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == updateButton) {
                int ID = Integer.parseInt(IDField.getText());
                String FirstName = FirstNameField.getText();
                String LastName = LastNameField.getText();
                String DOBString = DOBField.getText();
                String Address = AddressField.getText();
                String email = emailField.getText();
                String phone = phoneField.getText();

                Date DOB; // Declare DOB as java.sql.Date

                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date parsedDate = dateFormat.parse(DOBString);
                    DOB = new Date(parsedDate.getTime()); // Assign the parsed date to DOB
                } catch (ParseException e) {
                    JOptionPane.showMessageDialog(null, "Invalid date format. Please enter date as YYYY-MM-DD");
                    return;
                }

                try {
                    UpdateCustomer.updateCustomer(ID, FirstName, LastName, DOB, Address, email, phone);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                JOptionPane.showMessageDialog(null, "Customer information updated!");
            }
        }
    }
    public static void main(String[] args) {
        GuiUpdateCustomer GuiupdateCustomer = new GuiUpdateCustomer();
        GuiupdateCustomer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GuiupdateCustomer.setSize(400, 380);
        GuiupdateCustomer.setVisible(true);
    }
}

