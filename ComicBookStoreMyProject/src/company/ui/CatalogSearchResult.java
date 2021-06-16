package company.ui;

import company.services.PrintHelper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Класс выводящий информацию при реализации поиска.
 */

public class CatalogSearchResult {

    @FXML
    private Button buttonReturnMenu;

    @FXML
    private TextArea textArea;

    @FXML
    void initialize() {
        buttonReturnMenu.setOnAction(actionEvent -> {
            buttonReturnMenu.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("fileFXML/menuScreen.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent parent = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene((parent)));
            stage.show();
        });

        textArea.appendText(PrintHelper.editingTheOutputToTheScreen(PrintHelper.getListResult()) + '\n' +
                PrintHelper.resultString);
        PrintHelper.clearListResult();
        PrintHelper.resultString = "";
    }
}
