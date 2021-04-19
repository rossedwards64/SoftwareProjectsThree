package sample;

import database.SQLiteDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import student.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.awt.*;
import java.io.*;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Controller {

    @FXML
    private AnchorPane LoginPage;

    @FXML
    private Button ClockIn;

    @FXML
    private Button ViewTimeTable;

    @FXML
    private Button ViewGrades;

    @FXML
    private Button AccountButton;

    @FXML
    private Button Campus;

    @FXML
    private Button StudentActivity;

    @FXML
    private Button StudentUnion;

    @FXML
    private Button VirtualTour;

    @FXML
    private AnchorPane AccountAnchorPane;

    @FXML
    private Button LogoutButton;

    @FXML
    private Button ChangePasswordButton;

    @FXML
    private AnchorPane SigninPage;

    @FXML
    private PasswordField Password;

    @FXML
    private TextField StudentId;

    @FXML
    private Button signInButtonOnAction;

    @FXML
    private Label signInLabel;

    @FXML
    private AnchorPane attendpage;

    @FXML
    private Button LeaveButton;

    @FXML
    private Button AttendButton;

    @FXML
    private Button XButton;

    @FXML
    private AnchorPane absentWindow;
    @FXML
    private ChoiceBox absentReason;
    @FXML
    private Button absentSubmit;
    @FXML
    private Button absentButton;

    @FXML
    private AnchorPane studentTabs;
    @FXML
    private AnchorPane teacherTabs;
    @FXML
    private AnchorPane homePage;
    @FXML
    private AnchorPane attendancePage;
    @FXML
    private Label absentName;
    @FXML
    private Label absentAttendance;
    @FXML
    private Label absentReasonTeacher;
    @FXML
    private Label reasonID;
    @FXML
    private Button MainButton;
    @FXML
    private AnchorPane ResetStudent;
    @FXML
    private TextArea OtherReason;



    ObservableList <String> choices = FXCollections.observableArrayList("Sick", "Late", "Medical", "Family", "Job" , "Commitment", "Other");


    @FXML
    public void Logout(ActionEvent event){
        SigninPage.toFront();
    }

    @FXML
    public void recordAbsent(ActionEvent event){
        absentReason.setItems(choices);
        absentWindow.toFront();
    }
    @FXML
    public void backtomain(ActionEvent event) throws IOException {
        LoginPage.toFront();
        Desktop.getDesktop().open(new File("C:\\Users\\kiere\\Documents\\S\\absentReasons.csv")); //move else where later
        ResetStudent.toFront();
    }

    public void Teacherchangepassword(ActionEvent event){
        Student user = loginInfo();

        String username = user.getStudentID();


        SQLiteDatabase.UpdatePassword(username);


    }
    @FXML
    public void closeWindow(ActionEvent event){
        absentWindow.toBack();
        attendancePage.toBack();

    }

    @FXML
    public void markAsRead(ActionEvent event){
        Student student = loginInfo();
        String filePath = "absentReasons.csv";
        String removeTerm = reasonID.getText();
        removeRecord(filePath, removeTerm, 5);
        String searchTerm = student.getStudentCourse();
        String[] studentData = (fetchStudentData(searchTerm, filePath));
        try{
            absentName.setText(studentData[0]);
            absentAttendance.setText(studentData[1]);
            absentReasonTeacher.setText(studentData[2]);
            System.out.println(studentData[3]);
            reasonID.setText(studentData[4]);
        }
        catch (Exception var15){
        }

    }

    public void removeRecord(String filePath, String removeTerm, int positionOfTerm) {
        int position = positionOfTerm - 1;
        String tempFile = "temp.csv";
        File oldFile = new File(filePath);
        File newFile = new File(tempFile);

        try {
            FileWriter fw = new FileWriter(tempFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);

            String currentLine;
            while((currentLine = br.readLine()) != null) {
                String[] data = currentLine.split(",");
                System.out.println(data[position]);
                System.out.println(removeTerm);
                String ID = data[position];
                if (!ID.equals(String.valueOf(removeTerm))) {
                    pw.println(currentLine);
                    System.out.println("not same");
                }
            }

            pw.flush();
            pw.close();
            fr.close();
            br.close();
            bw.close();
            fw.close();
            oldFile.delete();
            File dump = new File(filePath);
            newFile.renameTo(dump);
        } catch (Exception var15) {
            var15.printStackTrace();
        }

    }

    public static String[] fetchStudentData(String searchTerm, String filePath){
        ArrayList<String> records = new ArrayList<String>();

        String name = ""; String attendance = ""; String reason = ""; String course = ""; String status = "";
        boolean found = false;

        try {
            Scanner x;
            x = new Scanner(new File(filePath));
            x.useDelimiter("[,\n]");

            while (x.hasNext() && !found){
                name = x.next();
                attendance = x.next();
                reason = x.next();
                course = x.next();
                status = x.next();

                if (course.equals(searchTerm)){
                    System.out.println(course);
                    System.out.println(searchTerm);
                    records.add(name);
                    records.add(attendance);
                    records.add(reason);
                    records.add(course);
                    records.add(status);
                    found = true;

                }
            }
            x.close();
        }
        catch (Exception e){
            System.out.println("Error");
        }

        String[] studentData = new String[records.size()];
        records.toArray(studentData);
        return studentData;
    }

    public void viewAttendance(ActionEvent event){
        Student student = loginInfo();
        attendancePage.toFront();
        String searchTerm = student.getStudentCourse();
        String filePath = "absentReasons.csv";
        String[] studentData = (fetchStudentData(searchTerm, filePath));
        try{
            absentName.setText(studentData[0]);
            absentAttendance.setText(studentData[1]);
            absentReasonTeacher.setText(studentData[2]);
            System.out.println(studentData[3]);
            reasonID.setText(studentData[4]);
        }
        catch (Exception var15){
        }
    }


    public void submitAbsent(ActionEvent event){
        Student student = loginInfo();
        ArrayList<String> absentString = new ArrayList();
        absentString.add(student.getStudentID());
        absentString.add((student.getStudentFName()) + " " + (student.getStudentLName()));
        absentString.add(student.getStudentAttendance());
        absentString.add(student.getStudentCourse());

        String value = (String) absentReason.getValue();
        if (value.equals("Other")) {
            value = OtherReason.getText();
        }
        absentString.add(value);
        String absentList = String.join(",", absentString);
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter("absentReasons.csv", true));
            writer.write(absentList);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //absentWindow.toBack();
        //absentReason.clear();
    }

    public void signInButtonOnAction(ActionEvent event){
        if (StudentId.getText().isEmpty() || Password.getText().isEmpty()){
            signInLabel.setText("Enter a Password and Email");
        }
        else{
            String studentID = StudentId.getText();
            String studentPassword = Password.getText();
            System.out.println("Welcome," + studentID);
            validateLogin(studentID, studentPassword);
        }
    }

    public void validateLogin(String studentID, String studentPassword) {
        boolean validate = SQLiteDatabase.verifyLogin(studentID, studentPassword);
        if(validate) {
            SigninPage.toBack();
            LoginPage.toFront();
            //homePage.toFront();
            Student student = loginInfo();
            if(student.getStudentStatus().equals("Teacher")){
                //teacherTabs.toFront();
            }
            else {
                //studentTabs.toFront();
            }
        }
    }

    public void checkdetails(){
        SigninPage.toBack();
        LoginPage.toFront();
    }

    @FXML
    void Account (ActionEvent event){
        AccountAnchorPane.toFront();

    }

    @FXML
    void RecordAttendance(ActionEvent event){
        Student student = loginInfo();
        String id = StudentId.getText();//For now this is static but we can retrive this
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");// time
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
        String Attendacemark = "On time"; //CAN BE LATE(5min past lesson time), Absence(Didnt attend)
        System.out.println("Student id is " + student.getStudentID() + ", Date and time is " + dtf.format(now) + ", Student was " + Attendacemark);

        ArrayList<String> attendanceInfo = new ArrayList();
        attendanceInfo.add(student.getStudentID());
        attendanceInfo.add(student.getStudentFName());
        attendanceInfo.add(student.getStudentLName());
        attendanceInfo.add(student.getStudentCourse());
        attendanceInfo.add(student.getStudentAttendance());
        String attendanceInfoString = String.join(",", attendanceInfo);

        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter("attendance_info.csv", true));
            writer.write(attendanceInfoString);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        LeaveButton.toFront();
    }

    public Student loginInfo() {
        String studentID = StudentId.getText();
        String studentFName = SQLiteDatabase.studentFName(studentID);
        String studentLName = SQLiteDatabase.studentLName(studentID);
        String studentPassword = SQLiteDatabase.studentPassword(studentID);
        String studentCourse = SQLiteDatabase.studentCourse(studentID);
        String studentAttendance = SQLiteDatabase.studentAttendance(studentID);
        String studentStatus = SQLiteDatabase.studentStatus(studentID);

        return new Student(studentID, studentFName, studentLName, studentPassword, studentCourse, studentAttendance, studentStatus);
    }

    @FXML
    void RecordTimeLeft(ActionEvent event){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");// time
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Student left at " + dtf.format((now)));
        AttendButton.toFront();
    }

    @FXML
    void Attendpage(ActionEvent event){
        Student student = loginInfo();
        System.out.println(student.getStudentStatus());
        attendpage.toFront();
    }

    @FXML
    void UniversityLogin (ActionEvent event){
        try {

            URI Campus= new URI("https://www.bue.edu.eg/pages/login-page/");
            java.awt.Desktop.getDesktop().browse(Campus);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    @FXML
    void CampusMap (ActionEvent event){
        try {

            URI Campus= new URI("https://www.bue.edu.eg/campus-map/");

            java.awt.Desktop.getDesktop().browse(Campus);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @FXML
    void StudentUnionWebsite (ActionEvent event){
        try {

            URI Union= new URI("https://www.bue.edu.eg/student-activities-final/#1535914711582-d7b3101d-fe08");

            java.awt.Desktop.getDesktop().browse(Union);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @FXML
    void StudentActivityWebsite (ActionEvent event){
        try {

            URI Activity= new URI("https://www.bue.edu.eg/student-activities-final/");

            java.awt.Desktop.getDesktop().browse(Activity);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @FXML
    void VirtualTourWebsite (ActionEvent event){
        try {

            URI Campus= new URI("https://www.bue.edu.eg/virtual-tour/");

            java.awt.Desktop.getDesktop().browse(Campus);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

}
