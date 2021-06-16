package company.ui;

import company.repository.SaveComicBookAndClientRepository;
import company.services.PrintHelper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Класс предстовляющий собой основной инфтерфейс пользователя для работы комиксами(основное меню).
 */

public class MenuController {

    @FXML
    private Button buttonAddComic;

    @FXML
    private Button buttonSaleComic;

    @FXML
    private Button buttonReservingComicName;

    @FXML
    private Button buttonDiscountComic;

    @FXML
    private ChoiceBox<String> checkBoxSearchCatalog;

    @FXML
    private Button buttonSearchCatalog;

    @FXML
    private ChoiceBox<String> checkBoxSearchNewComic;

    @FXML
    private Button buttonSearchNewComic;

    @FXML
    private ChoiceBox<String> checkBoxSearchGenreComic;

    @FXML
    private Button buttonSearchGenre;

    @FXML
    private Button buttonSearchSaleComic;

    @FXML
    private ChoiceBox<String> checkBoxSearchAuthorComic;

    @FXML
    private ChoiceBox<String> checkBoxSearchSaleComic;

    @FXML
    private Button buttonPopularSearchAuthor;

    @FXML
    private TextField dataEntryLine;

    @FXML
    private Label enteringDataFromButton;

    @FXML
    private Button buttonSaveProject;

    @FXML
    private Button buttonShowCatalog;

    @FXML
    private Button buttonMenuClient;


    @FXML
    void initialize() {

        checkBoxSearchCatalog.getItems().addAll(PrintHelper.SEARCH_BY_NAME, PrintHelper.SEARCH_BY_AUTHOR,
                PrintHelper.SEARCH_BY_GENRE);
        checkBoxSearchNewComic.getItems().addAll(PrintHelper.SEARCH_PER_DAY, PrintHelper.SEARCH_PER_WEEK,
                PrintHelper.SEARCH_PER_MONTH, PrintHelper.SEARCH_PER_YEARS);
        checkBoxSearchGenreComic.getItems().addAll(PrintHelper.SEARCH_PER_DAY, PrintHelper.SEARCH_PER_WEEK,
                PrintHelper.SEARCH_PER_MONTH, PrintHelper.SEARCH_PER_YEARS);
        checkBoxSearchAuthorComic.getItems().addAll(PrintHelper.SEARCH_PER_DAY, PrintHelper.SEARCH_PER_WEEK,
                PrintHelper.SEARCH_PER_MONTH, PrintHelper.SEARCH_PER_YEARS);
        checkBoxSearchSaleComic.getItems().addAll(PrintHelper.SEARCH_PER_DAY, PrintHelper.SEARCH_PER_WEEK,
                PrintHelper.SEARCH_PER_MONTH, PrintHelper.SEARCH_PER_YEARS);

        buttonShowCatalog.setOnAction(actionEvent -> showCatalog());

        buttonAddComic.setOnAction(actionEvent -> addComic());

        buttonSaleComic.setOnAction(actionEvent -> deleteWriteOffSaleComic());

        buttonSearchCatalog.setOnAction(actionEvent -> searchCatalog());

        buttonSearchNewComic.setOnAction(actionEvent -> searchPopularNewComics());

        buttonPopularSearchAuthor.setOnAction(actionEvent -> searchPopularSearchAuthorComic());

        buttonSearchGenre.setOnAction((actionEvent -> searchPopularSearchGenre()));

        buttonDiscountComic.setOnAction(actionEvent -> discountComic());

        buttonReservingComicName.setOnAction(actionEvent -> reservingComic());

        buttonMenuClient.setOnAction(actionEvent -> menuClient());

        buttonSaveProject.setOnAction(actionEvent -> saveProject());

        buttonSearchSaleComic.setOnAction(actionEvent -> searchSaleComic());
    }

    private void saveProject() {
        SaveComicBookAndClientRepository saveComicBookAndClientRepository = new SaveComicBookAndClientRepository();
        saveComicBookAndClientRepository.saveAll();
    }

    private void changingTheScreen(String string) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(string));
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

    private void showCatalog() {
        buttonShowCatalog.getScene().getWindow().hide();
        changingTheScreen(PrintHelper.ADDRESS_SHOW_CATALOG);
    }

    /**
     * Методы переводят на другое окно с вводом данных.
     */
    private void addComic() {
        buttonAddComic.getScene().getWindow().hide();
        changingTheScreen(PrintHelper.ADDRESS_ADD_COMIC);
    }

    private void deleteWriteOffSaleComic() {
        buttonSaleComic.getScene().getWindow().hide();
        changingTheScreen(PrintHelper.ADDRESS_DELETE_AND_WRITE_OFF_SALE_COMIC);
    }

    private void discountComic() {
        buttonDiscountComic.getScene().getWindow().hide();
        changingTheScreen(PrintHelper.ADDRESS_DISCOUNT_COMIC);
    }

    private void reservingComic() {
        buttonReservingComicName.getScene().getWindow().hide();
        changingTheScreen(PrintHelper.ADDRESS_RESERVING_COMIC);
    }

    private void menuClient() {
        buttonMenuClient.getScene().getWindow().hide();
        changingTheScreen(PrintHelper.ADDRESS_MENU_CLIENT);
    }

    private void goToSearchScreen() {
        if (PrintHelper.getListResult().size() != 0) {
            buttonSearchCatalog.getScene().getWindow().hide();
            changingTheScreen(PrintHelper.ADDRESS_SEARCH_SCREEN);
        } else {
            enteringDataFromButton.setText(PrintHelper.NOT_OBJECT_NEW_SEARCH);
        }
    }

    /**
     * Методы поиска по каталогу.
     */
    private void searchCatalog() {
        String string = dataEntryLine.getText().trim();
        if (!string.isEmpty()) {
            switch (checkBoxSearchCatalog.getValue()) {
                case (PrintHelper.SEARCH_BY_NAME) -> {
                    Main.serviceWorkingWithComics.searchForComicByTitle(string);
                    goToSearchScreen();
                }
                case (PrintHelper.SEARCH_BY_AUTHOR) -> {
                    Main.serviceWorkingWithComics.searchForComicByAuthor(string);
                    goToSearchScreen();
                }
                case (PrintHelper.SEARCH_BY_GENRE) -> {
                    Main.serviceWorkingWithComics.searchForComicsByGenre(PrintHelper.checkGenreComics(string));
                    goToSearchScreen();
                }
            }
        } else {
            enteringDataFromButton.setText(PrintHelper.FILL_IN_THE_LINE_AND_START_THE_SEARCH);
        }
    }

    private void searchPopularNewComics() {
        switch (checkBoxSearchNewComic.getValue()) {
            case (PrintHelper.SEARCH_PER_DAY) -> {
                Main.serviceWorkingWithComics.popularNewComicsForTheDay();
                goToSearchScreen();
            }
            case (PrintHelper.SEARCH_PER_WEEK) -> {
                Main.serviceWorkingWithComics.popularNewComicsForTheWeek();
                goToSearchScreen();
            }
            case (PrintHelper.SEARCH_PER_MONTH) -> {
                Main.serviceWorkingWithComics.popularNewComicsForTheMonth();
                goToSearchScreen();
            }
            case (PrintHelper.SEARCH_PER_YEARS) -> {
                Main.serviceWorkingWithComics.popularNewComicsForTheYear();
                goToSearchScreen();
            }
        }
    }

    private void searchPopularSearchAuthorComic() {
        switch (checkBoxSearchAuthorComic.getValue()) {
            case (PrintHelper.SEARCH_PER_DAY) -> {
                Main.serviceWorkingWithComics.popularArtistForTheDay();
                goToSearchScreen();
            }
            case (PrintHelper.SEARCH_PER_WEEK) -> {
                Main.serviceWorkingWithComics.popularArtistForTheWeek();
                goToSearchScreen();
            }
            case (PrintHelper.SEARCH_PER_MONTH) -> {
                Main.serviceWorkingWithComics.popularArtistForTheMonth();
                goToSearchScreen();
            }
            case (PrintHelper.SEARCH_PER_YEARS) -> {
                Main.serviceWorkingWithComics.popularArtistForTheYear();
                goToSearchScreen();
            }
        }
    }

    private void searchPopularSearchGenre() {
        switch (checkBoxSearchGenreComic.getValue()) {
            case (PrintHelper.SEARCH_PER_DAY) -> {
                Main.serviceWorkingWithComics.popularGenreForTheDay();
                goToSearchScreen();
            }
            case (PrintHelper.SEARCH_PER_WEEK) -> {
                Main.serviceWorkingWithComics.popularGenreForTheWeek();
                goToSearchScreen();
            }
            case (PrintHelper.SEARCH_PER_MONTH) -> {
                Main.serviceWorkingWithComics.popularGenreForTheMonth();
                goToSearchScreen();
            }
            case (PrintHelper.SEARCH_PER_YEARS) -> {
                Main.serviceWorkingWithComics.popularGenreForTheYear();
                goToSearchScreen();
            }
        }
    }

    private void searchSaleComic() {
        switch (checkBoxSearchSaleComic.getValue()) {
            case (PrintHelper.SEARCH_PER_DAY) -> {
                Main.serviceWorkingWithComics.popularNewComicsSaleForTheDay();
                goToSearchScreen();
            }
            case (PrintHelper.SEARCH_PER_WEEK) -> {
                Main.serviceWorkingWithComics.popularNewComicsSaleForTheWeek();
                goToSearchScreen();
            }
            case (PrintHelper.SEARCH_PER_MONTH) -> {
                Main.serviceWorkingWithComics.popularNewComicsSaleForTheMonth();
                goToSearchScreen();
            }
            case (PrintHelper.SEARCH_PER_YEARS) -> {
                Main.serviceWorkingWithComics.popularNewComicsSaleForTheYear();
                goToSearchScreen();
            }
        }
    }

}
