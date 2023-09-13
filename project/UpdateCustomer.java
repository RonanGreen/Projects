package project;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateCustomer {
    public static void updateCustomer(int ID, String FirstName, String LastName, Date DOB, String Address, String email, String phone) {
        final String DATABASE_URL = "jdbc:mysql://localhost:3307/project";
        Connection connection = null;
        PreparedStatement pstat = null;
        int i = 0;

        try {
            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, "root", "root");
            // create Prepared Statement for updating data in the table
            pstat = connection.prepareStatement("UPDATE Customer SET FirstName=?, LastName=?, DOB=?, Address=?, email=?, telephone=? WHERE CustomerID=?");
            pstat.setString(1, FirstName);
            pstat.setString(2, LastName);
            pstat.setDate(3, DOB); // Set the date of birth in the PreparedStatement
            pstat.setString(4, Address);
            pstat.setString(5, email);
            pstat.setString(6, phone);
            pstat.setInt(7, ID); // Set the ID in the PreparedStatement
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