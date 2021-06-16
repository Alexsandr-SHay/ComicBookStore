package company.ui;

import company.domain.GenreComics;
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
import java.math.BigDecimal;

/**
 * Класс предстовляющий собой инфтерфейс пользователя для объявления скидок на комиксы из коллекции в магазине.
 * По названию, по Id, по серии, по жанру, общая скидка(черная пятница).
 */

public class MenuDiscountOnComic {

    @FXML
    private TextField fieldComicBookName;

    @FXML
    private TextField fieldDiscount;

    @FXML
    private Label labelResult;

    @FXML
    private Button buttonReturnMenu;

    @FXML
    private Button buttonDiscountOnComicByName;

    @FXML
    private Button buttonDiscountOnComicById;

    @FXML
    private Button buttonDiscountOnComicBySeries;

    @FXML
    private Button buttonDiscountOnComicByGenre;

    @FXML
    private Button buttonDiscountALL;

    @FXML
    void initialize() {
        buttonDiscountOnComicByName.setOnAction(actionEvent -> discountOnComicByName());

        buttonDiscountOnComicById.setOnAction(actionEvent -> discountOnComicById());

        buttonDiscountALL.setOnAction(actionEvent -> discountALL());

        buttonDiscountOnComicBySeries.setOnAction(actionEvent -> discountOnComicBySeries());

        buttonDiscountOnComicByGenre.setOnAction(actionEvent -> discountOnComicByGenre());

        buttonReturnMenu.setOnAction(actionEvent -> returnMenu());
    }

    private void discountOnComicByName() {
        String name = fieldComicBookName.getText();
        BigDecimal value = checkDiscountComic(fieldDiscount.getText());
        if (checkInputData(name, value)) {
            Main.serviceWorkingWithComics.makeDiscountOnComicByName(name, value);
            labelResult.setText(PrintHelper.DISCOUNT_YES);
            clearField();
        }
    }

    private void discountOnComicById() {
        String name = fieldComicBookName.getText();
        BigDecimal value = checkDiscountComic(fieldDiscount.getText());
        int id = checkIdComic(name);
        if (checkInputData(name, value)) {
            Main.serviceWorkingWithComics.makeDiscountOnComicByID(id, value);
            labelResult.setText(PrintHelper.DISCOUNT_YES);
            clearField();
        }
    }

    private void discountALL() {
        BigDecimal value = checkDiscountComic(fieldDiscount.getText());
        if (value.compareTo(BigDecimal.ZERO) > 0) {
            Main.serviceWorkingWithComics.makeDiscountOnALLComic(value);
            labelResult.setText(PrintHelper.DISCOUNT_YES);
            clearField();
        } else {
            labelResult.setText(PrintHelper.DISCOUNT_INVALID_VALUE);
        }
    }

    private void discountOnComicBySeries() {
        String series = fieldComicBookName.getText();
        BigDecimal value = checkDiscountComic(fieldDiscount.getText());
        if (checkInputData(series, value)) {
            Main.serviceWorkingWithComics.makeDiscountOncomicBookSeries(series, value);
            labelResult.setText(PrintHelper.DISCOUNT_YES);
            clearField();
        }
    }

    private void discountOnComicByGenre() {
        String name = fieldComicBookName.getText();
        BigDecimal value = checkDiscountComic(fieldDiscount.getText());
        Enum<GenreComics> genreComicsEnum = PrintHelper.checkGenreComics(name);
        if (checkInputData(name, value)) {
            Main.serviceWorkingWithComics.makeDiscountOncomicBookGenre(genreComicsEnum, value);
            labelResult.setText(PrintHelper.DISCOUNT_YES);
            clearField();
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


    private BigDecimal checkDiscountComic(String string) {
        BigDecimal value = PrintHelper.dataInputBigDecimal(string);
        if (value.compareTo(BigDecimal.ZERO) > 0 && value.compareTo(BigDecimal.valueOf(100)) <= 0)
            return value;
        else {
            return BigDecimal.ZERO;
        }
    }

    private int checkIdComic(String string) {
        return Math.max(PrintHelper.dataInputInteger(string), 0);
    }

    private boolean checkInputData(String name, BigDecimal value) {
        if (!name.isEmpty()) {
            if (value.compareTo(BigDecimal.ZERO) > 0) {
                return true;
            } else {
                labelResult.setText(PrintHelper.DISCOUNT_INVALID_VALUE);
            }
        } else {
            labelResult.setText(PrintHelper.NO_DISCOUNT_ASSIGNED);
        }
        return false;
    }

    private void clearField() {
        fieldComicBookName.clear();
        fieldDiscount.clear();
    }
}