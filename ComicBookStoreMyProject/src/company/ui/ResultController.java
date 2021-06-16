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
 * Класс для вывода итоговой информации по магазину. Здесь выводиться каталог комиксов, каталог проданных комиксов и
 * список клиентов
 */

public class ResultController {

    @FXML
    private Button buttonReturnMenu;

    @FXML
    private TextArea textAreaLstComic;

    @FXML
    private TextArea textAreaListSaleComic;

    @FXML
    private TextArea textAreaListClient;

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

        textAreaLstComic.appendText(PrintHelper.editingTheOutputToTheScreen(Main.serviceWorkingWithComics.getListComic()));
        textAreaListClient.appendText(PrintHelper.editingTheOutputToTheScreen(Main.serviceWorkingWithComics.getClientList()));
        textAreaListSaleComic.appendText(PrintHelper.editingTheOutputToTheScreen(Main.serviceWorkingWithComics.getListComicsSold()));
    }
}
