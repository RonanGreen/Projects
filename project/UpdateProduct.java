package project;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateProduct {
    public static void updateProduct(int ID, String cost, String item,String date) {
        final String DATABASE_URL = "jdbc:mysql://localhost:3307/project";
        Connection connection = null;
        PreparedStatement pstat = null;
        int i = 0;

        try {
            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, "root", "root");
            // create Prepared Statement for updating data in the table
            pstat = connection.prepareStatement("UPDATE Product SET  cost=?, item=?,  Date=? WHERE ProductID=?");;
            pstat.setString(1, cost);
            pstat.setString(2, item);
            pstat.setString(3, date);
            pstat.setInt(4, ID); // Set the ID in the PreparedStatement
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
