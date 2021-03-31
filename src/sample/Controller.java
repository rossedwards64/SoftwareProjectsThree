package sample;

import database.SQLiteDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class Controller {

    @FXML
    private AnchorPane LoginPage;

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


    public void signInButtonOnAction(ActionEvent event){
        if (StudentId.getText().isEmpty() && Password.getText().isEmpty()){
            signInLabel.setText("Please Enter A Password And Username");
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

}
