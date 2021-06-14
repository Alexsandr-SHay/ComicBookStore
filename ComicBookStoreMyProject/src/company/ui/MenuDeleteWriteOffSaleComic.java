package company.ui;

import company.services.PrintHelper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Класс предстовляющий собой инфтерфейс пользователя для удаления, списания и продажи комиксов из коллекции в магазине
 */

public class MenuDeleteWriteOffSaleComic {

    @FXML
    private TextField fieldComicBookName;

    @FXML
    private TextField fieldComicBookId;

    @FXML
    private TextField fieldNumberOfComics;

    @FXML
    private Label labelResult;

    @FXML
    private Button buttonReturnMenu;

    @FXML
    private Button buttonSaleComicId;

    @FXML
    private Button buttonSaleComicName;

    @FXML
    private Button buttonWriteOffComicId;

    @FXML
    private Button buttonWriteOffComicName;

    @FXML
    private Button buttonDeleteComicId;

    @FXML
    private Button buttonDeleteComicName;

    @FXML
    void initialize() {
        buttonDeleteComicId.setOnAction(actionEvent -> deleteComicId());

        buttonDeleteComicName.setOnAction(actionEvent -> deleteComicName());

        buttonWriteOffComicId.setOnAction(actionEvent -> writeOffComicId());

        buttonWriteOffComicName.setOnAction(actionEvent -> writeOffComicName());

        buttonSaleComicId.setOnAction(actionEvent -> saleComicBookId());

        buttonSaleComicName.setOnAction(actionEvent -> saleComicBookName());

        buttonReturnMenu.setOnAction(actionEvent -> returnMenu());
    }

    private void deleteComicId() {
        int id = checkNumberOfComic(fieldComicBookId.getText().trim());
        if (id > 0) {
            if (Main.comicBookSalesman.deletingComicById(id)) {
                labelResult.setText(PrintHelper.COMIC_WAS_SUCCESSFULLY_DELETED);
            } else {
                labelResult.setText(PrintHelper.COMIC_BOOK_NOT_FOUND);
            }
            clearField();
        } else {
            labelResult.setText(PrintHelper.INVALID_DATA);
        }
    }

    private void deleteComicName() {
        if (Main.comicBookSalesman.deletingComicByName(fieldComicBookName.getText().trim())) {
            labelResult.setText(PrintHelper.COMIC_WAS_SUCCESSFULLY_DELETED);
        } else {
            labelResult.setText(PrintHelper.COMIC_BOOK_NOT_FOUND);
        }
        clearField();
    }

    private void writeOffComicId() {
        int id = checkNumberOfComic(fieldComicBookId.getText().trim());
        int numberOfComics = checkNumberOfComic(fieldNumberOfComics.getText().trim());
        if (id > 0 && numberOfComics > 0) {
            if (Main.comicBookSalesman.writeOffComicBookById(id, numberOfComics)) {
                labelResult.setText(PrintHelper.COMIC_WAS_SUCCESSFULLY_WRITE_OFF);
            } else {
                labelResult.setText(PrintHelper.COMIC_BOOK_NOT_FOUND);
            }
            clearField();
        } else {
            labelResult.setText(PrintHelper.INVALID_DATA);
        }
    }

    private void writeOffComicName() {
        int numberOfComics = checkNumberOfComic(fieldNumberOfComics.getText().trim());
        if (numberOfComics > 0) {
            if (Main.comicBookSalesman.writeOffComicByName(fieldComicBookName.getText().trim(), numberOfComics)) {
                labelResult.setText(PrintHelper.COMIC_WAS_SUCCESSFULLY_WRITE_OFF);
            } else {
                labelResult.setText(PrintHelper.COMIC_BOOK_NOT_FOUND);
            }
            clearField();
        } else {
            labelResult.setText(PrintHelper.INVALID_DATA);
        }
    }

    private void saleComicBookId() {
        int id = checkNumberOfComic(fieldComicBookId.getText().trim());
        int numberOfComics = checkNumberOfComic(fieldNumberOfComics.getText().trim());
        if (id > 0 && numberOfComics > 0) {
            if (Main.comicBookSalesman.comicBookSalesById(id, numberOfComics)) {
                labelResult.setText(PrintHelper.COMIC_WAS_SUCCESSFULLY_SALE);
            } else {
                labelResult.setText(PrintHelper.COMIC_BOOK_NOT_FOUND);
            }
            clearField();
        } else {
            labelResult.setText(PrintHelper.INVALID_DATA);
        }
    }

    private void saleComicBookName() {
        int numberOfComics = checkNumberOfComic(fieldNumberOfComics.getText().trim());
        if (numberOfComics > 0) {
            if (Main.comicBookSalesman.comicBookSalesByName(fieldComicBookName.getText().trim(), numberOfComics)) {
                labelResult.setText(PrintHelper.COMIC_WAS_SUCCESSFULLY_SALE);
            } else {
                labelResult.setText(PrintHelper.COMIC_BOOK_NOT_FOUND);
            }
            clearField();
        } else {
            labelResult.setText(PrintHelper.INVALID_DATA);
        }
    }

    private void returnMenu() {
        buttonReturnMenu.getScene().getWindow().hide();
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

    private int checkNumberOfComic(String string) {
        return Math.max(PrintHelper.dataInputInteger(string), 0);
    }

    private void clearField() {
        fieldComicBookName.clear();
        fieldComicBookId.clear();
        fieldNumberOfComics.clear();
    }
}

