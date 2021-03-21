package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private TextField StudentId;

    @FXML
    private PasswordField Password;

    @FXML
    private Button SigininButton;

    @FXML
    private Label Siginin_Page_Label;

    public void SigininButtonONation(ActionEvent event){
        if (StudentId.getText().isEmpty() && Password.getText().isEmpty()){
            Siginin_Page_Label.setText("Please Enter A Password And Username");
        }
        else{
            Siginin_Page_Label.setText("both are entered");
        }
    }

}
