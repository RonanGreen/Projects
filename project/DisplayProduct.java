package project;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DisplayProduct extends JFrame {
    private JTextField DateField;
    private JTextField itemField;
    private JTextField costField;
    

    public DisplayProduct(String Date,String item,String cost) {
        super("Product Information");
        setLayout(null);

        int labelX = 30;
        int fieldX = 130;
        int rowHeight = 30;
        int startY = 30;

        add(new JLabel("Date of sale:")).setBounds(labelX, startY, 100, 20);
        DateField = new JTextField(Date, 10);
        DateField.setBounds(fieldX, startY, 200, 20);
        DateField.setEditable(false);
        add(DateField);
        startY += rowHeight;

        add(new JLabel("Item:")).setBounds(labelX, startY, 100, 20);
        itemField = new JTextField(item, 10);
        itemField.setBounds(fieldX, startY, 200, 20);
        itemField.setEditable(false);
        add(itemField);
        startY += rowHeight;

        add(new JLabel("Cost:")).setBounds(labelX, startY, 100, 20);
        costField = new JTextField(cost, 10);
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

            String query = "SELECT * FROM Product WHERE ProductID = 2"; // You can change the condition to fetch a specific customer
            resultSet = statement.executeQuery(query);

            if (resultSet.next()) {                
                String Date = resultSet.getDate("Date").toString();
                String item = resultSet.getString("item");
                String cost = resultSet.getString("Cost");


                DisplayProduct displayProduct = new DisplayProduct(Date, item, cost);
                displayProduct.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                displayProduct.setSize(400, 250);
                displayProduct.setVisible(true);
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
