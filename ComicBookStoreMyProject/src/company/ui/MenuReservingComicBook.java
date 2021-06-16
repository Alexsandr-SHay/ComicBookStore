package company.ui;

import company.domain.Client;
import company.domain.ComicBook;
import company.services.PrintHelper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * Класс предстовляющий собой инфтерфейс пользователя для резервирования комиксов из коллекции в магазине,
 * а также работы с зарезервированными комиксами
 */

public class MenuReservingComicBook {

    @FXML
    private TextField fieldComicBookName;

    @FXML
    private TextField fieldNameClient;

    @FXML
    private TextField fieldNumberOfComics;

    @FXML
    private Label labelResult;

    @FXML
    private Button buttonReturnMenu;

    @FXML
    private Button buttonResrvingComicBook;

    @FXML
    private Button buttonSaleResrvingComicBook;

    @FXML
    private Button buttonReservation;

    @FXML
    private Button buttonShowClient;

    @FXML
    private TextArea textArea;

    @FXML
    private Button buttonClear;

    @FXML
    void initialize() {
        buttonResrvingComicBook.setOnAction(actionEvent -> reversingComicBook());

        buttonShowClient.setOnAction(actionEvent -> showClient());

        buttonReservation.setOnAction(actionEvent -> reservation());

        buttonSaleResrvingComicBook.setOnAction(actionEvent -> saleResrvingComicBook());

        buttonClear.setOnAction(actionEvent -> clearText());

        buttonReturnMenu.setOnAction(actionEvent -> returnMenu());
    }

    private void showClient() {
        textArea.appendText(PrintHelper.editingTheOutputToTheScreen(Main.serviceWorkingWithComics.getClientList()));
    }

    private void reversingComicBook() {
        try {
            Main.serviceWorkingWithComics.comicBookReserving(checkDataComic(), checkDataClient(),
                    PrintHelper.checkNumberOfComic(fieldNumberOfComics.getText()));
            labelResult.setText(PrintHelper.COMIC_BOOK_RESERVED);
            clearField();
        } catch (NullPointerException e) {
            textArea.appendText(PrintHelper.DISCOUNT_INVALID_VALUE);
        }
    }

    private void saleResrvingComicBook() {
        try {
            Main.serviceWorkingWithComics.saleComicBookReserving(checkingComicBookWithClient(), checkDataClient(),
                    PrintHelper.checkNumberOfComic(fieldNumberOfComics.getText()));
            labelResult.setText(PrintHelper.COMIC_BOOK_RESERVED_SALE);
            clearField();
        } catch (NullPointerException e) {
            textArea.appendText(PrintHelper.DISCOUNT_INVALID_VALUE);
        }
    }

    private void reservation() {
        try {
            Main.serviceWorkingWithComics.returnComicBookReserving(checkingComicBookWithClient(), checkDataClient(),
                    PrintHelper.checkNumberOfComic(fieldNumberOfComics.getText()));
            labelResult.setText(PrintHelper.COMIC_BOOK_RESERVATION);
            clearField();
        } catch (NullPointerException e) {
            textArea.appendText(PrintHelper.DISCOUNT_INVALID_VALUE);
        }
    }

    private void clearText() {
        textArea.clear();
        labelResult.setText(PrintHelper.SELECT_ONE_OF_THE_MENU_ITEMS);
    }

    private void clearField() {
        fieldComicBookName.clear();
        fieldNameClient.clear();
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

    private ComicBook checkDataComic() {
        String text = fieldComicBookName.getText();
        try {
            int value = Integer.parseInt(text);
            return Main.serviceWorkingWithComics.searchObjectComicBook(value);
        } catch (NoSuchElementException e) {
            labelResult.setText(PrintHelper.COMIC_BOOK_NOT_FOUND);
        } catch (NumberFormatException e) {
            labelResult.setText(PrintHelper.COMIC_BOOK_NOT_FOUND);
            try {
                return Main.serviceWorkingWithComics.searchObjectComicBook(text);
            } catch (NoSuchElementException ignored) {
                labelResult.setText(PrintHelper.COMIC_BOOK_NOT_FOUND);
            }
        }
        return null;
    }

    private Client checkDataClient() {
        String text = fieldNameClient.getText();
        try {
            int value = Integer.parseInt(text);
            return Main.serviceWorkingWithComics.searchClientOnId(value);
        } catch (NoSuchElementException e) {
            labelResult.setText(PrintHelper.COMIC_BOOK_NOT_FOUND);
        } catch (NumberFormatException e) {
            labelResult.setText(PrintHelper.COMIC_BOOK_NOT_FOUND);
            try {
                return Main.serviceWorkingWithComics.searchClientOnName(text);
            } catch (NoSuchElementException ignored) {
                labelResult.setText(PrintHelper.COMIC_BOOK_NOT_FOUND);
            }
        }
        return new Client();
    }

    private ComicBook checkingComicBookWithClient() {
        Client client = checkDataClient();
        String text = fieldComicBookName.getText();
        try {
            int value = Integer.parseInt(text);
            return Main.serviceWorkingWithComics.searchObjectComicBookClient(value, client);
        } catch (NoSuchElementException e) {
            labelResult.setText(PrintHelper.COMIC_BOOK_NOT_FOUND);
        } catch (NumberFormatException e) {
            labelResult.setText(PrintHelper.COMIC_BOOK_NOT_FOUND);
            try {
                return Main.serviceWorkingWithComics.searchObjectComicBookClient(text, client);
            } catch (NoSuchElementException ignored) {
                labelResult.setText(PrintHelper.COMIC_BOOK_NOT_FOUND);
            }
        }
        return new ComicBook();
    }
}

