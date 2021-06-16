package company.ui;

import company.services.PrintHelper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * Класс предстовляющий собой инфтерфейс пользователя для добавления и редактирования коллекции комиксов в магазине
 */

public class AddAndEditComicController {

    @FXML
    private TextField fieldCostPrice;

    @FXML
    private Button logInButtonStartAdd;

    @FXML
    private Label labelComicBookName;

    @FXML
    private TextField fieldComicBookName;

    @FXML
    private TextField fieldFullNameAuthor;

    @FXML
    private TextField fieldComicBookPublisher;

    @FXML
    private TextField fieldNumberOfPages;

    @FXML
    private TextField fieldComicBookId;

    @FXML
    private TextField fieldSalePrice;

    @FXML
    private TextField fieldInputData;

    @FXML
    private TextField fieldYearPublication;

    @FXML
    private TextField fieldGenreComics;

    @FXML
    private TextField fieldComicBookSeries;

    @FXML
    private Label labelINumberOfComics;

    @FXML
    private TextField fieldNumberOfComics;

    @FXML
    private Button buttonReturnMenu;

    @FXML
    private Label labelResult;

    @FXML
    private Button logInButtonStartEdit;

    @FXML
    private Button logInButtonStartEditName;

    @FXML
    void initialize() {

        logInButtonStartAdd.setOnAction(actionEvent -> addComicBook());

        logInButtonStartEdit.setOnAction(actionEvent -> editComicId());

        logInButtonStartEditName.setOnAction(actionEvent -> editComicName());

        buttonReturnMenu.setOnAction(actionEvent -> returnMenu());
    }

    private void addComicBook() {
        String comicName = fieldComicBookName.getText();
        int numberOfComic = checkNumberOfComic(fieldNumberOfComics.getText().trim());
        if (Main.serviceWorkingWithComics.addComic(comicName, numberOfComic)) {
            labelComicBookName.setText(PrintHelper.COMIC_EXISTS + numberOfComic);
            labelComicBookName.setText(PrintHelper.COMIC_BOOK_ADDED);
            labelINumberOfComics.setText(PrintHelper.NUMBER_OF_COMICS_ADDED + numberOfComic);
            clearField();
        } else {
            if (checkComic()) {
                labelComicBookName.setText(PrintHelper.COMIC_BOOK_WITH_THIS_NAME_WAS_NOT_FOUND);
                Main.serviceWorkingWithComics.addComic(checkData(fieldComicBookName),
                        checkData(fieldFullNameAuthor),
                        checkData(fieldComicBookPublisher),
                        PrintHelper.dataInputInteger(fieldNumberOfPages.getText().trim()),
                        PrintHelper.checkGenreComics(fieldGenreComics.getText().trim()),
                        PrintHelper.dataInputBigDecimal(fieldCostPrice.getText().trim()),
                        PrintHelper.dataInputBigDecimal(fieldSalePrice.getText().trim()),
                        PrintHelper.checkYear(fieldYearPublication.getText().trim()),
                        checkData(fieldComicBookSeries),
                        PrintHelper.dataInputInteger(fieldNumberOfComics.getText().trim()),
                        fieldInputData.getText().trim());
                labelResult.setText(PrintHelper.COMIC_BOOK_ADDED);
                clearField();
            } else {
                labelResult.setText(PrintHelper.INCORRECT_DATA_ENTERED);
            }
        }
    }

    private void editComicName() {
        String comicName = fieldComicBookName.getText();
        if (Main.serviceWorkingWithComics.checkComicBookName(comicName)) {
            if (checkComic()) {
                Main.serviceWorkingWithComics.editComicByName(
                        checkData(fieldComicBookName),
                        checkData(fieldFullNameAuthor),
                        checkData(fieldComicBookPublisher),
                        PrintHelper.dataInputInteger(fieldNumberOfPages.getText().trim()),
                        PrintHelper.checkGenreComics(fieldGenreComics.getText().trim()),
                        PrintHelper.dataInputBigDecimal(fieldCostPrice.getText().trim()),
                        PrintHelper.dataInputBigDecimal(fieldSalePrice.getText().trim()),
                        PrintHelper.checkYear(fieldYearPublication.getText().trim()),
                        checkData(fieldComicBookSeries),
                        PrintHelper.dataInputInteger(fieldNumberOfComics.getText().trim()),
                        fieldInputData.getText().trim());
                labelResult.setText(PrintHelper.EDIT_COMIC);
                clearField();
            } else {
                labelResult.setText(PrintHelper.INCORRECT_DATA_ENTERED);
            }
        } else {
            labelResult.setText(PrintHelper.COMIC_BOOK_WITH_THIS_ID_WAS_NOT_FOUND);
        }
    }

    private void editComicId() {
        int comicId = checkNumberOfComic(fieldComicBookId.getText());
        if (Main.serviceWorkingWithComics.checkComicBookId(comicId)) {
            if (checkComic()) {
                Main.serviceWorkingWithComics.editComicById(
                        comicId,
                        checkData(fieldComicBookName),
                        checkData(fieldFullNameAuthor),
                        checkData(fieldComicBookPublisher),
                        PrintHelper.dataInputInteger(fieldNumberOfPages.getText().trim()),
                        PrintHelper.checkGenreComics(fieldGenreComics.getText().trim()),
                        PrintHelper.dataInputBigDecimal(fieldCostPrice.getText().trim()),
                        PrintHelper.dataInputBigDecimal(fieldSalePrice.getText().trim()),
                        PrintHelper.checkYear(fieldYearPublication.getText().trim()),
                        checkData(fieldComicBookSeries),
                        PrintHelper.dataInputInteger(fieldNumberOfComics.getText().trim()),
                        fieldInputData.getText().trim());
                labelResult.setText(PrintHelper.EDIT_COMIC);
                clearField();
            } else {
                labelResult.setText(PrintHelper.INCORRECT_DATA_ENTERED);
            }
        } else {
            labelResult.setText(PrintHelper.COMIC_BOOK_WITH_THIS_ID_WAS_NOT_FOUND);
        }
    }

    private int checkNumberOfComic(String string) {
        return Math.max(PrintHelper.dataInputInteger(string), 0);
    }

    private boolean checkComic() {
        return checkNumberOfComic(fieldNumberOfPages.getText()) >= 0 &&
                PrintHelper.dataInputBigDecimal(fieldCostPrice.getText().trim()).compareTo(BigDecimal.ZERO) >= 0 &&
                PrintHelper.dataInputBigDecimal(fieldSalePrice.getText().trim()).compareTo(BigDecimal.ZERO) >= 0 &&
                PrintHelper.checkYear(fieldYearPublication.getText().trim()) >= 1901 &&
                checkNumberOfComic(fieldNumberOfComics.getText().trim()) >= 0;
    }

    private String checkData(TextField textField) {
        return textField.getText().isEmpty() ? PrintHelper.EMPTY_DATA : textField.getText().trim();
    }

    private void clearField() {
        fieldCostPrice.clear();
        fieldComicBookName.clear();
        fieldFullNameAuthor.clear();
        fieldComicBookPublisher.clear();
        fieldNumberOfPages.clear();
        fieldComicBookId.clear();
        fieldSalePrice.clear();
        fieldInputData.clear();
        fieldYearPublication.clear();
        fieldGenreComics.clear();
        fieldComicBookSeries.clear();
        fieldNumberOfComics.clear();
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
}


