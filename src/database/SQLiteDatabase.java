package database;

import java.sql.*;

public class SQLiteDatabase {
    public static Connection connect() {
        Connection conn = null;
        try {
            String fileName = "src/database/student_table.db";
            String url = "jdbc:sqlite:" + fileName;
            conn = DriverManager.getConnection(url);
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static boolean verifyLogin(String studentID, String studentPassword) {
        Connection conn = connect();
        String SQL = "SELECT * FROM StudentInfo WHERE StudentID = ? AND StudentPassword = ? OR (StudentID IS NULL AND StudentPassword IS NULL)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, studentID);
            pstmt.setString(2, studentPassword);
            ResultSet result = pstmt.executeQuery();

            if(result.next()) {
                System.out.println("Student ID and password are correct");
                conn.close();
                return true;
            } else {
                System.out.println("Student ID and password are incorrect");
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static String studentFName(String studentID) {
        Connection conn = connect();
        try {
            String SQL = "SELECT StudentFName FROM StudentInfo WHERE StudentID = ?";
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, studentID);
            ResultSet result = pstmt.executeQuery();
            studentID = result.getString("StudentFName");
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return studentID;
    }
}
