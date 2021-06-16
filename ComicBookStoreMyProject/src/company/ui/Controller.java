package company.ui;

import java.io.IOException;

import company.services.PrintHelper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller {

    /**
     * Класс входа в программу. Ввод логина и пароля.
     */

    @FXML
    private Button logInButtonStartScreen;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label invalid_username;

    @FXML
    void initialize() {
        logInButtonStartScreen.setOnAction(actionEvent -> {
            if (loginField.getText().equals("admin") && passwordField.getText().equals("admin")) {
                logInButtonStartScreen.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(PrintHelper.ADDRESS_RETURN_MENU));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Parent parent = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene((parent)));
                stage.show();
            }
        else {invalid_username.setText("Неверный логин или пароль." + '\n' + "Пожалуйста введите данные снова");
            loginField.setText("");
            passwordField.setText("");
        }
        });
    }
}


