package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class deleteinvoice {
    public static void main(String[] args) {
        // database URL
        final String DATABASE_URL = "jdbc:mysql://localhost:3307/Project";
        Connection connection = null;
        PreparedStatement pstat = null;
        int i = 0;
        int InvoiceID = 3;

        try {
            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, "root", "root");
            // create Prepared Statement for deleting data from the table
            pstat = connection.prepareStatement("DELETE FROM Invoice WHERE InvoiceID = ?");
            pstat.setInt(1, InvoiceID);
            // delete data from the table
            i = pstat.executeUpdate();
            System.out.println(i + " record successfully removed from the table.");
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
    } // end main
} // end class