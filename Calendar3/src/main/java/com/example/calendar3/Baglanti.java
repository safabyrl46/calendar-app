package com.example.calendar3;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Baglanti {
    private static String username = "root";
    private static String password = "123456";
    private static String url = "jdbc:mysql://localhost:3306/deneme";
    private static String driver = "com.mysql.cj.jdbc.Driver";
    public static Connection myConn;
    private static Statement myStat;
    private static ResultSet myRes;
    Baglanti() {

    }

    static {
        try {
            Class.forName(driver);
            myConn = (Connection) DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    static ResultSet yap() {
        try {

            myStat = myConn.createStatement();
            myRes = myStat.executeQuery("select * from calendar.user");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myRes;
    }
    static ResultSet yap(String sqlQuery) {
        try {

            myStat = myConn.createStatement();
            myRes = myStat.executeQuery(sqlQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myRes;
    }
    static int Login (String username, String password) {
        ResultSet res = null;
        int girdiMi = 0;
        String sqlQuery = "SELECT count(idTC) as giris FROM calendar.user WHERE username=? and password=?";
        PreparedStatement ps;
        try {
            ps = myConn.prepareStatement(sqlQuery);
            ps.setString(1, username);
            ps.setString(2, password);
            res = ps.executeQuery();
            if (res.next()) {
                girdiMi = res.getInt("giris");

            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return girdiMi;
    }
    static void planEkle(String comment, String tip, String time, LocalDate date) {
        String sql_sorgu;
        sql_sorgu = "INSERT INTO calendar.user (username,password,date,comment,tip,saat) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement ps = myConn.prepareStatement(sql_sorgu);
            // ps.setInt(1, id);
            ps.setString(1, LoginController.username);
            ps.setString(3, LoginController.password);
            ps.setString(4, String.valueOf(date));
            ps.setString(5, tip);
            ps.setString(6, String.valueOf(time));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


