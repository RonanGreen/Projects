package project;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertProduct {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3307/Project";

    public static void insertProduct(double cost, String item,Date date ) {
        Connection connection = null;
        PreparedStatement pstat = null;

        try {
            connection = DriverManager.getConnection(DATABASE_URL, "root", "root");
            pstat = connection.prepareStatement("INSERT INTO Product (Cost, item,Date) VALUES (?, ?, ?)");
            pstat.setDouble(1, cost);
            pstat.setString(2, item);
            pstat.setDate(3, date);
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