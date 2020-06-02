package com.company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBCPUtilsTestDemo {
    public static void main(String[] args) throws SQLException {

//		insert();
//		update();
        query();
        insert();
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        insert2();
    }

    public static void insert() throws SQLException{
        // 1.获取连接
        Connection conn = DBCPUtils.getConnection();
        // 2.获取执行对象
        String sql = "insert into t1 (id,k) values (?, ?)";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setObject(1, 2222);
        pst.setObject(2, "wwwww");
        // 3.执行语句
        int rows = pst.executeUpdate();
        System.out.println(rows);
        DBCPUtils.closeAll(conn, pst, null);
    }

    public static void insert2() throws SQLException{
        // 1.获取连接
        Connection conn = DBCPUtils.getConnection();
        // 2.获取执行对象
        String sql = "insert into t1 (id,k) values (?, ?)";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setObject(1, 2222222);
        pst.setObject(2, "qqqqq");
        // 3.执行语句
        int rows = pst.executeUpdate();
        System.out.println(rows);
        DBCPUtils.closeAll(conn, pst, null);
    }

    public static void update() throws SQLException{
        // 1.获取连接
        Connection conn = DBCPUtils.getConnection();
        // 2.获取执行对象
        Statement st = conn.createStatement();
        // 3.执行sql
        int rows = st.executeUpdate("update users set passwd = 'co' where uid = 6");
        System.out.println(rows);
        DBCPUtils.closeAll(conn, st, null);
    }

    public static void query() throws SQLException{
        // 1.获取连接
        Connection conn = DBCPUtils.getConnection();
        // 2.获取执行对象
        Statement st = conn.createStatement();
        // 3.执行查询
        ResultSet rs = st.executeQuery("select * from t1 where id = 1");
        // 4.处理结果集
        while(rs.next()){
            System.out.println(rs.getObject("id") + "\t" + rs.getObject("k") + "\t");
        }
        DBCPUtils.closeAll(conn, st, rs);

    }
}
