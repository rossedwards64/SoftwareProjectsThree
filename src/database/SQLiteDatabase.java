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
                result.close();
                pstmt.close();
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

    public static String studentID(String studentID) {
        Connection conn = connect();
        try {
            String SQL = "SELECT StudentID FROM StudentInfo WHERE StudentID = ?";
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, studentID);
            ResultSet result = pstmt.executeQuery();
            studentID = result.getString("StudentID");
            result.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return studentID;
    }

    public static String studentFName(String studentID) {
        Connection conn = connect();
        try {
            String SQL = "SELECT StudentFName FROM StudentInfo WHERE StudentID = ?";
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, studentID);
            ResultSet result = pstmt.executeQuery();
            studentID = result.getString("StudentFName");
            result.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return studentID;
    }

    public static String studentLName(String studentID) {
        Connection conn = connect();
        try {
            String SQL = "SELECT StudentLName FROM StudentInfo WHERE StudentID = ?";
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, studentID);
            ResultSet result = pstmt.executeQuery();
            studentID = result.getString("StudentLName");
            result.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return studentID;
    }

    public static String studentCourse(String studentID) {
        Connection conn = connect();
        try {
            String SQL = "SELECT StudentCourse FROM StudentInfo WHERE StudentID = ?";
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, studentID);
            ResultSet result = pstmt.executeQuery();
            studentID = result.getString("StudentCourse");
            result.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return studentID;
    }

    public static String studentAttendance(String studentID) {
        Connection conn = connect();
        try {
            String SQL = "SELECT StudentAttendance FROM StudentInfo WHERE StudentID = ?";
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, studentID);
            ResultSet result = pstmt.executeQuery();
            studentID = result.getString("StudentAttendance");
            result.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return studentID;
    }


    public static String studentPassword(String studentID) {
        Connection conn = connect();
        try {
            String SQL = "SELECT StudentPassword FROM StudentInfo WHERE StudentID = ?";
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, studentID);
            ResultSet result = pstmt.executeQuery();
            studentID = result.getString("StudentPassword");
            result.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return studentID;
    }

    public static String studentStatus(String studentID) {
        Connection conn = connect();
        try {
            String SQL = "SELECT Status FROM StudentInfo WHERE StudentID = ?";
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, studentID);
            ResultSet result = pstmt.executeQuery();
            studentID = result.getString("Status");
            result.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return studentID;
    }
    public static void StudentUpdatePassword(String Id, String newpassword){
        String SQL = "UPDATE StudentInfo SET StudentPassword = ? WHERE StudentId = ?";
        try(Connection conn = connect();

            PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            // set parameters
            pstmt.setString(1, newpassword);
            pstmt.setString(2, Id);
            // save changes
            pstmt.executeUpdate();


            conn.close();
            System.out.println("Update successful!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void UpdatePassword(String accountUsername, String newPassword){

        String SQL = "UPDATE StudentInfo SET StudentPassword = ? WHERE StudentId = ?";
        try(Connection conn = connect();

            PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            // set parameters
            pstmt.setString(1, newPassword);
            pstmt.setString(2, accountUsername);
            // save changes
            pstmt.executeUpdate();


            conn.close();
            System.out.println("Update successful!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
