package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateInvoice {
    public static void updateInvoice(int ID, String LastName, String Address, String Item, String cost) {
        final String DATABASE_URL = "jdbc:mysql://localhost:3307/project";
        Connection connection = null;
        PreparedStatement pstat = null;
        int i = 0;

        try {
            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, "root", "root");
            // create Prepared Statement for updating data in the table
            pstat = connection.prepareStatement("UPDATE Invoice SET LastName=?, Address=?, item=?, cost=? WHERE InvoiceID=?");;
            pstat.setString(1, LastName);
            pstat.setString(2, Address);
            pstat.setString(3, Item);
            pstat.setString(4, cost);
            pstat.setInt(5, ID); // Set the ID in the PreparedStatement
            // Update data in the table
            i = pstat.executeUpdate();
            System.out.println(i + " record successfully updated in the table.");
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
    } // end method updateCustomer
} // end class UpdateCustomer