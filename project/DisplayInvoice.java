package project;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DisplayInvoice extends JFrame {
    private JTextField LastNameField;
    private JTextField AddressField;
    private JTextField itemField;
    private JTextField costField;
    

    public DisplayInvoice(String LastName, String Address, String item, String Cost) {
        super("Invoice Information");
        setLayout(null);

        int labelX = 30;
        int fieldX = 130;
        int rowHeight = 30;
        int startY = 30;

        add(new JLabel("LastName:")).setBounds(labelX, startY, 100, 20);
        LastNameField = new JTextField(LastName, 10);
        LastNameField.setBounds(fieldX, startY, 200, 20);
        LastNameField.setEditable(false);
        add(LastNameField);
        startY += rowHeight;
        
        add(new JLabel("Address:")).setBounds(labelX, startY, 100, 20);
        AddressField = new JTextField(Address, 10);
        AddressField.setBounds(fieldX, startY, 200, 20);
        AddressField.setEditable(false);
        add(AddressField);
        startY += rowHeight;

        add(new JLabel("Item:")).setBounds(labelX, startY, 100, 20);
        itemField = new JTextField(item, 10);
        itemField.setBounds(fieldX, startY, 200, 20);
        itemField.setEditable(false);
        add(itemField);
        startY += rowHeight;

        add(new JLabel("Cost:")).setBounds(labelX, startY, 100, 20);
        costField = new JTextField(Cost, 10);
        costField.setBounds(fieldX, startY, 200, 20);
        costField.setEditable(false);
        add(costField);
        startY += rowHeight;
        
        

        
    }

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            String DATABASE_URL = "jdbc:mysql://localhost:3307/Project";
            connection = DriverManager.getConnection(DATABASE_URL, "root", "root");
            statement = connection.createStatement();

            String query = "SELECT * FROM Invoice WHERE InvoiceID = 2"; // You can change the condition to fetch a specific customer
            resultSet = statement.executeQuery(query);

            if (resultSet.next()) {   
            	String LastName = resultSet.getString("LastName");             
                String Address = resultSet.getString("Address");
                String item = resultSet.getString("item");
                String cost = resultSet.getString("Cost");


                DisplayInvoice displayInvoice = new DisplayInvoice(LastName, Address, item, cost);
                displayInvoice.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                displayInvoice.setSize(400, 250);
                displayInvoice.setVisible(true);
            } else {
                System.out.println("No product found.");
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
