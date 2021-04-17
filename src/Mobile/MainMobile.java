package Mobile;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class MainMobile extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MobileDesign.fxml")));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 335, 515));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
