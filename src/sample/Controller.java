package sample;

import database.SQLiteDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    private TextField absentReason;
    @FXML
    private Button absentSubmit;
    @FXML
    private Button absentButton;

    @FXML
    public void Logout(ActionEvent event){
        SigninPage.toFront();
    }

    @FXML
    public void recordAbsent(ActionEvent event){
        absentWindow.toFront();
    }

    @FXML
    public void closeWindow(ActionEvent event){
        absentWindow.toBack();
    }


    public void signInButtonOnAction(ActionEvent event){
        if (StudentId.getText().isEmpty() && Password.getText().isEmpty()){
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
        String id = StudentId.getText();//For now this is static but we can retrive this
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");// time
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
        String Attendacemark = "On time"; //CAN BE LATE(5min past lesson time), Absence(Didnt attend)
        System.out.println("Student id is " + id + ", Date and time is " + dtf.format(now) + ", Student was " + Attendacemark);
        LeaveButton.toFront();
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
        attendpage.toFront();
    }
    @FXML
    void UniversityLogin (ActionEvent event){
        try {

            URI Campus= new URI("https://www.bue.edu.eg/pages/login-page/");
            java.awt.Desktop.getDesktop().browse(Campus);
        } catch (Exception e) {

            e.printStackTrace();
        }}
    @FXML
    void CampusMap (ActionEvent event){
        try {

            URI Campus= new URI("https://www.bue.edu.eg/campus-map/");

            java.awt.Desktop.getDesktop().browse(Campus);
        } catch (Exception e) {

            e.printStackTrace();
        }}

    @FXML
    void StudentUnionWebsite (ActionEvent event){
        try {

            URI Union= new URI("https://www.bue.edu.eg/student-activities-final/#1535914711582-d7b3101d-fe08");

            java.awt.Desktop.getDesktop().browse(Union);
        } catch (Exception e) {

            e.printStackTrace();
        }}

    @FXML
    void StudentActivityWebsite (ActionEvent event){
        try {

            URI Activity= new URI("https://www.bue.edu.eg/student-activities-final/");

            java.awt.Desktop.getDesktop().browse(Activity);
        } catch (Exception e) {

            e.printStackTrace();
        }}

    @FXML
    void VirtualTourWebsite (ActionEvent event){
        try {

            URI Campus= new URI("https://www.bue.edu.eg/virtual-tour/");

            java.awt.Desktop.getDesktop().browse(Campus);
        } catch (Exception e) {

            e.printStackTrace();
        }}

}
