package project;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DisplayCustomer extends JFrame {
    private JTextField FirstNameField;
    private JTextField LastNameField;
    private JTextField DOBField;
    private JTextField AddressField;
    private JTextField emailField;
    private JTextField phoneField;

    public DisplayCustomer(String firstName, String lastName, String dob, String address, String email, String phone) {
        super("Customer Information");
        setLayout(null);

        int labelX = 30;
        int fieldX = 130;
        int rowHeight = 30;
        int startY = 30;

        add(new JLabel("First Name:")).setBounds(labelX, startY, 100, 20);
        FirstNameField = new JTextField(firstName, 10);
        FirstNameField.setBounds(fieldX, startY, 200, 20);
        FirstNameField.setEditable(false);
        add(FirstNameField);
        startY += rowHeight;

        add(new JLabel("Last Name:")).setBounds(labelX, startY, 100, 20);
        LastNameField = new JTextField(lastName, 10);
        LastNameField.setBounds(fieldX, startY, 200, 20);
        LastNameField.setEditable(false);
        add(LastNameField);
        startY += rowHeight;

        add(new JLabel("Date of Birth:")).setBounds(labelX, startY, 100, 20);
        DOBField = new JTextField(dob, 10);
        DOBField.setBounds(fieldX, startY, 200, 20);
        DOBField.setEditable(false);
        add(DOBField);
        startY += rowHeight;

        add(new JLabel("Address:")).setBounds(labelX, startY, 100, 20);
        AddressField = new JTextField(address, 10);
        AddressField.setBounds(fieldX, startY, 200, 20);
        AddressField.setEditable(false);
        add(AddressField);
        startY += rowHeight;

        add(new JLabel("Email:")).setBounds(labelX, startY, 100, 20);
        emailField = new JTextField(email, 10);
        emailField.setBounds(fieldX, startY, 200, 20);
        emailField.setEditable(false);
        add(emailField);
        startY += rowHeight;

        add(new JLabel("Phone:")).setBounds(labelX, startY, 100, 20);
        phoneField = new JTextField(phone, 10);
        phoneField.setBounds(fieldX, startY, 200, 20);
        phoneField.setEditable(false);
        add(phoneField);
    }

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            String DATABASE_URL = "jdbc:mysql://localhost:3307/Project";
            connection = DriverManager.getConnection(DATABASE_URL, "root", "root");
            statement = connection.createStatement();

            String query = "SELECT * FROM Customer WHERE CustomerID = 3"; // You can change the condition to fetch a specific customer
            resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                String dob = resultSet.getDate("DOB").toString();
                String address = resultSet.getString("Address");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("telephone");

                DisplayCustomer displayCustomer = new DisplayCustomer(firstName, lastName, dob, address, email, phone);
                displayCustomer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                displayCustomer.setSize(400, 250);
                displayCustomer.setVisible(true);
            } else {
                System.out.println("No customer found.");
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }
}