package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertInvoice {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3307/Project";

    public static void insertInvoice(String lastname, String address, String Item, double Cost) {
        Connection connection = null;
        PreparedStatement pstat = null;

        try {
            connection = DriverManager.getConnection(DATABASE_URL, "root", "root");
            pstat = connection.prepareStatement("INSERT INTO Invoice (LastName, Address, Item, Cost) VALUES (?, ?, ?, ?)");
            pstat.setString(1, lastname);
            pstat.setString(2, address);
            pstat.setString(3, Item);
            pstat.setDouble(4, Cost);

            int i = pstat.executeUpdate();
            System.out.println(i + " record successfully added to the table.");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                if (pstat != null) {
                    pstat.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
}