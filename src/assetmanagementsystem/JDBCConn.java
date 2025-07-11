/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assetmanagementsystem;

/**
 *
 * @author dhanukarishika
 */
import java.sql.*;

public class JDBCConn {
    public static void main(String[] args) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521/XEPDB1", "asset", "asset");

            System.out.println("Connected to Oracle DB successfully! 🎉");

            con.close();
        } catch (Exception e) {
            System.out.println("Connection failed 😢");
            e.printStackTrace();
        }
    }
}
