package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;


public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println("Hello World !");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            System.out.println("SQLException: " + ex.getMessage());
        }
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://10.186.61.20:3306/sbtest?user=root&password=123456");
            conn.setAutoCommit(false);
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        Statement stmt = null;
        ResultSet rs = null;
        int up = 0;
        try {
            assert conn != null;
            stmt = conn.createStatement();
//            rs = stmt.executeQuery("SELECT * from t1");
            up = stmt.executeUpdate("insert into t1 values(11111111,'fsvssb')");
            conn.commit();
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            up = stmt.executeUpdate("insert into t1 values(111111111,'fsvssb')");
            conn.commit();
//            while(rs.next()){
//                System.out.println(rs.getInt(1));
//                System.out.println(rs.getString(2));
//            }
//            System.out.println("ResultSet: "+ rs);
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

    }
}
