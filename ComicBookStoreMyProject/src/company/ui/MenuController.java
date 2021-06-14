package company.ui;

import company.domain.Client;
import company.domain.ComicBook;
import company.domain.GenreComics;
import company.repository.SaveComicBookAndClient;
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
import java.math.BigDecimal;
import java.util.ArrayList;

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

//        ComicBook comicBook1 = new ComicBook("Человек паук", "Стэн Ли",
//                "Marvel", 45, GenreComics.FANTASY, BigDecimal.valueOf(152),
//                BigDecimal.valueOf(180), 1962, "Spider-Man", 5,
//                "14.06.2021");
//        ComicBook comicBook2 = new ComicBook("Человек паук против зелёного гоблина", "Стэн Ли",
//                "DC Comics", 102, GenreComics.FANTASY, BigDecimal.valueOf(300),
//                BigDecimal.valueOf(450), 1960, "Spider-Man", 3,
//                "14.06.2021");
//        ComicBook comicBook3 = new ComicBook("Бэтмен", "Боб Кейн",
//                "DC Comics", 91, GenreComics.DETECTIVE, BigDecimal.valueOf(280),
//                BigDecimal.valueOf(400), 1939, "Batman", 6,
//                "06.03.2019");
//        ComicBook comicBook4 = new ComicBook("Бэтмен и Робин", "Фингер Билл",
//                "DC Comics", 54, GenreComics.DETECTIVE, BigDecimal.valueOf(120),
//                BigDecimal.valueOf(180), 1946, "Batman", 4,
//                "01.06.2020");
//        ComicBook comicBook5 = new ComicBook("Бэтмен и джокер", "Фингер Билл",
//                "DC Comics", 91, GenreComics.DETECTIVE, BigDecimal.valueOf(280),
//                BigDecimal.valueOf(400), 2046, "Batman", 2, "15.01.2021");
//        ComicBook comicBook6 = new ComicBook("Тетрадь смерти", "Цугуми Оба",
//                " Weekly Shonen Jump ", 80, GenreComics.MYSTICISM, BigDecimal.valueOf(120),
//                BigDecimal.valueOf(180), 2003, " Death Note", 1, "12.06.2021");
//        ComicBook comicBook7 = new ComicBook("Первый русский комикс", "Андрей Иванов",
//                "Росма", 30, GenreComics.OTHER, BigDecimal.valueOf(50),
//                BigDecimal.valueOf(60), 2009, "Russian comic", 10, "01.05.2021");
//        ComicBook comicBook8 = new ComicBook("Берсерк", "Кэнтаро Миура",
//                "Hakusensha", 90, GenreComics.HORROR, BigDecimal.valueOf(80),
//                BigDecimal.valueOf(100), 1997, "Berserk", 8, "07.04.2021");
//        ComicBook comicBook9 = new ComicBook("Берсерк 2", "Кэнтаро Миура",
//                "Росма", 61, GenreComics.MYSTICISM, BigDecimal.valueOf(40),
//                BigDecimal.valueOf(60), 1999, "Berserk", 2, "08.05.2021");
//        ComicBook comicBook10 = new ComicBook("Халк", "Стэн Ли",
//                "Marvel", 28, GenreComics.FANTASY, BigDecimal.valueOf(32),
//                BigDecimal.valueOf(45), 1982, "Hulk", 2, "08.05.2021");
//
//
//        Client client1 = new Client("Сидоров Алексей", new ArrayList<>(), "89991234567");
//        Client client2 = new Client("Иванов Иван", new ArrayList<>(), "89992345678");
//        Client client3 = new Client("Петров Пётр", new ArrayList<>(), "89993456789");
//        Client client4 = new Client("Семенов Андрей", new ArrayList<>(), "89994567891");
//        Client client5 = new Client("Алексеенко Роман", new ArrayList<>(), "89995678912");
//
//        Main.comicBookSalesman.addComic(comicBook1);
//        Main.comicBookSalesman.addComic(comicBook2);
//        Main.comicBookSalesman.addComic(comicBook3);
//        Main.comicBookSalesman.addComic(comicBook4);
//        Main.comicBookSalesman.addComic(comicBook5);
//        Main.comicBookSalesman.addComic(comicBook6);
//        Main.comicBookSalesman.addComic(comicBook7);
//        Main.comicBookSalesman.addComic(comicBook8);
//        Main.comicBookSalesman.addComic(comicBook9);
//        Main.comicBookSalesman.addComic(comicBook10);
//        Main.comicBookSalesman.addClient(client1);
//        Main.comicBookSalesman.addClient(client2);
//        Main.comicBookSalesman.addClient(client3);
//        Main.comicBookSalesman.addClient(client4);
//        Main.comicBookSalesman.addClient(client5);
    }

    private void saveProject() {
        SaveComicBookAndClient saveComicBookAndClient = new SaveComicBookAndClient();
        saveComicBookAndClient.saveAll();
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
                    Main.comicBookSalesman.searchForComicByTitle(string);
                    goToSearchScreen();
                }
                case (PrintHelper.SEARCH_BY_AUTHOR) -> {
                    Main.comicBookSalesman.searchForComicByAuthor(string);
                    goToSearchScreen();
                }
                case (PrintHelper.SEARCH_BY_GENRE) -> {
                    Main.comicBookSalesman.searchForComicsByGenre(PrintHelper.checkGenreComics(string));
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
                Main.comicBookSalesman.popularNewComicsForTheDay();
                goToSearchScreen();
            }
            case (PrintHelper.SEARCH_PER_WEEK) -> {
                Main.comicBookSalesman.popularNewComicsForTheWeek();
                goToSearchScreen();
            }
            case (PrintHelper.SEARCH_PER_MONTH) -> {
                Main.comicBookSalesman.popularNewComicsForTheMonth();
                goToSearchScreen();
            }
            case (PrintHelper.SEARCH_PER_YEARS) -> {
                Main.comicBookSalesman.popularNewComicsForTheYear();
                goToSearchScreen();
            }
        }
    }

    private void searchPopularSearchAuthorComic() {
        switch (checkBoxSearchAuthorComic.getValue()) {
            case (PrintHelper.SEARCH_PER_DAY) -> {
                Main.comicBookSalesman.popularArtistForTheDay();
                goToSearchScreen();
            }
            case (PrintHelper.SEARCH_PER_WEEK) -> {
                Main.comicBookSalesman.popularArtistForTheWeek();
                goToSearchScreen();
            }
            case (PrintHelper.SEARCH_PER_MONTH) -> {
                Main.comicBookSalesman.popularArtistForTheMonth();
                goToSearchScreen();
            }
            case (PrintHelper.SEARCH_PER_YEARS) -> {
                Main.comicBookSalesman.popularArtistForTheYear();
                goToSearchScreen();
            }
        }
    }

    private void searchPopularSearchGenre() {
        switch (checkBoxSearchGenreComic.getValue()) {
            case (PrintHelper.SEARCH_PER_DAY) -> {
                Main.comicBookSalesman.popularGenreForTheDay();
                goToSearchScreen();
            }
            case (PrintHelper.SEARCH_PER_WEEK) -> {
                Main.comicBookSalesman.popularGenreForTheWeek();
                goToSearchScreen();
            }
            case (PrintHelper.SEARCH_PER_MONTH) -> {
                Main.comicBookSalesman.popularGenreForTheMonth();
                goToSearchScreen();
            }
            case (PrintHelper.SEARCH_PER_YEARS) -> {
                Main.comicBookSalesman.popularGenreForTheYear();
                goToSearchScreen();
            }
        }
    }

    private void searchSaleComic() {
        switch (checkBoxSearchSaleComic.getValue()) {
            case (PrintHelper.SEARCH_PER_DAY) -> {
                Main.comicBookSalesman.popularNewComicsSaleForTheDay();
                goToSearchScreen();
            }
            case (PrintHelper.SEARCH_PER_WEEK) -> {
                Main.comicBookSalesman.popularNewComicsSaleForTheWeek();
                goToSearchScreen();
            }
            case (PrintHelper.SEARCH_PER_MONTH) -> {
                Main.comicBookSalesman.popularNewComicsSaleForTheMonth();
                goToSearchScreen();
            }
            case (PrintHelper.SEARCH_PER_YEARS) -> {
                Main.comicBookSalesman.popularNewComicsSaleForTheYear();
                goToSearchScreen();
            }
        }
    }

}
