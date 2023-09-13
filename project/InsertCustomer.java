package project;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertCustomer {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3307/Project";

    public static void insertCustomer(String firstname, String lastname, Date dateOfBirth, String address, String email, String telephone) {
        Connection connection = null;
        PreparedStatement pstat = null;

        try {
            connection = DriverManager.getConnection(DATABASE_URL, "root", "root");
            pstat = connection.prepareStatement("INSERT INTO Customer (FirstName, LastName, DOB, Address, email, telephone) VALUES (?, ?, ?, ?, ?, ?)");
            pstat.setString(1, firstname);
            pstat.setString(2, lastname);
            pstat.setDate(3, dateOfBirth);
            pstat.setString(4, address);
            pstat.setString(5, email);
            pstat.setString(6, telephone);

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