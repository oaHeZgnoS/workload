package com.workload.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBHelper {
    /*String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    String url = "jdbc:sqlserver://127.0.0.1;DatabaseName=mydata";*/
    
    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://127.0.0.1:3306/mydata";
    
    
    Connection con = null;
    ResultSet res = null;

    public void DataBase() {
            try {
                Class.forName(driver);
                con = DriverManager.getConnection(url, "root", "root");
            } catch (ClassNotFoundException e) {
                  System.err.println("װ�� JDBC/ODBC ��������ʧ�ܡ�" );  
                e.printStackTrace();
            } catch (SQLException e) {
                System.err.println("�޷��������ݿ�" ); 
                e.printStackTrace();
            }
    }

    // ��ѯ
    public ResultSet  Search(String sql, String str[]) {
        DataBase();
        try {
            PreparedStatement pst =con.prepareStatement(sql);
            if (str != null) {
                for (int i = 0; i < str.length; i++) {
                    pst.setString(i + 1, str[i]);
                }
            }
            res = pst.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    // ��ɾ�޸�
    public int AddU(String sql, String str[]) {
        int a = 0;
        DataBase();
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            if (str != null) {
                for (int i = 0; i < str.length; i++) {
                    pst.setString(i + 1, str[i]);
                }
            }
            a = pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

}
