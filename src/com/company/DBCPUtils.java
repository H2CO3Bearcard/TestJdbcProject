package com.company;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSourceFactory;

public class DBCPUtils {
    // 工具类的作用：获取一个数据库连接
    private static DataSource ds = null;

    //静态代码块,设置ds的四大要素
    static{
        Properties ps = new Properties();
        try {
            // dbcpconfig.properties在当前工程的根目录使用
			ps.load(new FileInputStream("dbcpconfig.properties"));
            // dbcpconfig.properties在src目录下时使用	类加载器
            // ps.load(DBCPUtils02.class.getClassLoader().getResourceAsStream("dbcpconfig.properties"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ds = BasicDataSourceFactory.createDataSource(ps);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException{
        //返回一个连接对象,不要用DriverManager获取,而是连接池中获取
        return ds.getConnection();
    }

    //关闭所有资源的统一代码
    public static void closeAll(Connection conn, Statement st, ResultSet rs){
        //负责关闭
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if(st != null){
            try {
                st.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}