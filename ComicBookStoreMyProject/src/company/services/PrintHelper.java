package company.services;

import company.domain.Client;
import company.domain.GenreComics;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс для для проверки даннных а также хранящий в себе всё константы
 */

public class PrintHelper {

    private static List listResult;
    public static String resultString = "";
    public static String separator = File.separator;
    public static final String ADDRESS_START_MENU = "fileFXML/sample.fxml";
    public static final String ADDRESS_RETURN_MENU = "fileFXML/menuScreen.fxml";
    public static final String ADDRESS_ADD_COMIC = "fileFXML/menuAddAndEditComic.fxml";
    public static final String ADDRESS_SHOW_CATALOG = "fileFXML/result.fxml";
    public static final String ADDRESS_DELETE_AND_WRITE_OFF_SALE_COMIC = "fileFXML/menuDeleteWriteOffSalecomic.fxml";
    public static final String ADDRESS_DISCOUNT_COMIC = "fileFXML/menuDiscountOnComic.fxml";
    public static final String ADDRESS_RESERVING_COMIC = "fileFXML/menuReservingComicBook.fxml";
    public static final String ADDRESS_MENU_CLIENT = "fileFXML/menuClient.fxml";
    public static final String ADDRESS_SEARCH_SCREEN = "fileFXML/catalogSearchResult.fxml";

    public static final String SAVE_AND_READ_LIST_COMIC = "ComicBookStoreMyProject" + separator + "src" +
                    separator + "company" + separator + "repository" + separator + "file" + separator + "listComic.bin";
    public static final String SAVE_AND_READ_LIST_COMIC_SOLD = "ComicBookStoreMyProject" + separator + "src" +
            separator + "company" + separator + "repository" + separator + "file" + separator + "listComicSold.bin";
    public static final String SAVE_AND_READ_LIST_CLIENT = "ComicBookStoreMyProject" + separator + "src" +
            separator + "company" + separator + "repository" + separator + "file" + separator + "clientList.bin";

    public static final String COMIC_EXISTS = "Комикс с таким названием существует его количество увеличенно на ";
    public static final String COMIC_BOOK_ADDED = "Комикс добавлен";
    public static final String COMIC_BOOK_WITH_THIS_NAME_WAS_NOT_FOUND = "Комикс с таким названием не найден";
    public static final String COMIC_BOOK_WITH_THIS_ID_WAS_NOT_FOUND = "Комикс с таким ID не найден";
    public static final String INCORRECT_DATA_ENTERED = "Комикс не изменён из-за неверноо введённых данных. " +
            "Проверте данные и нажмите ОК";
    public static final String EDIT_COMIC = "Комикс изменён";
    public static final String COMIC_EXISTS_CORRECT_NAME = "Данный комикс с таким названием существует(исправте имя) ";
    public static final String NUMBER_OF_COMICS_ADDED = "Количество добаленных комиксов - ";
    public static final String EMPTY_DATA = "Неизвестные данные. Пожалуйста отредактируйте комикс";
    public static final String COMIC_WAS_SUCCESSFULLY_DELETED = "Комикс успешно удалён";
    public static final String COMIC_WAS_SUCCESSFULLY_WRITE_OFF = "Комикс успешно списан";
    public static final String COMIC_WAS_SUCCESSFULLY_SALE = "Комикс успешно продан";
    public static final String COMIC_BOOK_NOT_FOUND = "Комикс не найден";
    public static final String CLIENT_NOT_FOUND = "Клиент не найден";
    public static final String INVALID_DATA = "Введены не коректные данные или строка пуста";
    public static final String DISCOUNT_YES = "Скидка проведена";
    public static final String DISCOUNT_INVALID_VALUE = "Указано неверное значение. Проверте введённые данные и " +
            "повторите действие";
    public static final String NO_DISCOUNT_ASSIGNED = "Скидка не назначена. Проверте введённые данные и повторите " +
            "действие";
    public static final String SELECT_ONE_OF_THE_MENU_ITEMS = "Введите данные и выберите один из пунктов меню";
    public static final String COMIC_BOOK_RESERVED = "Комикс зарезервирован";
    public static final String COMIC_BOOK_RESERVED_SALE = "Зарезервированный комикс продан";
    public static final String COMIC_BOOK_RESERVATION = "Зарезервированный комикс возвращён в каталог";
    public static final String FILL_IN_THE_LINE_AND_START_THE_SEARCH = "Заполните строку для данных и нажмите " +
            "запустить поиск ";
    public static final String NOT_OBJECT_NEW_SEARCH = "Комикс с такими параметрами не найден, Пожалуйста запустите " +
            "поиск заново";

    public static final String SEARCH_PER_DAY = "За день";
    public static final String SEARCH_PER_WEEK = "За неделю";
    public static final String SEARCH_PER_MONTH = "За месяц";
    public static final String SEARCH_PER_YEARS = "За год";
    public static final String SEARCH_BY_NAME = "По названию";
    public static final String SEARCH_BY_AUTHOR = "По художнику";
    public static final String SEARCH_BY_GENRE = "По жанру";

    public static final String CLIENT_EXISTS = "Клиент существует ";
    public static final String CLIENT_ADDED = "Клиент добавлен";
    public static final String CLIENT_EDITED = "Данные клиента изменены";
    public static final String CLIENT_REPLACE_INPUT_DATA = "Клиент найден заполните строки снова и нажмите кнопку " +
            "-' Отредактировать параметры клиента'";
    public static final String FILL_NAME_CLIENTS_AND_PHONE = "Заполните имя и телефон клиента";


    public static List getListResult() {
        return listResult;
    }

    public static void setListResult(List listResult) {
        PrintHelper.listResult = listResult;
    }

    public static void clearListResult() {
        listResult.clear();
    }

    public static String editingTheOutputToTheScreen(List list) {
        return list.toString().replaceAll(",", "").replace("[", "").
                replace("]", "");
    }

    public static String editingTheOutputToTheScreenClient(Client client) {
        return client.toString().replaceAll(",", "").replace("[", "").
                replace("]", "");
    }

    public static int dataInputInteger(String string) {
        try {
            int value = Integer.parseInt(string);
            if (value > 0) {
                return value;
            }
        } catch (NumberFormatException e) {
            return -1;
        }
        return -1;
    }

    public static BigDecimal dataInputBigDecimal(String string) {
        try {
            BigDecimal value = BigDecimal.valueOf(Double.parseDouble(string));
            if (value.compareTo(BigDecimal.ZERO) >= 0) {
                return value;
            }
        } catch (NumberFormatException e) {
            return BigDecimal.valueOf(-1);
        }
        return BigDecimal.valueOf(-1);
    }

    public static int checkYear(String string) {
        int yearOfPublication = dataInputInteger(string);
        if (yearOfPublication < 1900 || yearOfPublication > 2021) {
            return 1900;
        }
        return yearOfPublication;
    }

    public static int checkNumberOfComic(String string) {
        return Math.max(PrintHelper.dataInputInteger(string), 0);
    }

    public static Enum<GenreComics> checkGenreComics(String genreComic) {
        switch (genreComic.toUpperCase(Locale.ROOT)) {
            case "DETECTIVE", "ДЕТЕКТИВ" -> {
                return GenreComics.DETECTIVE;
            }
            case "HORROR", "УЖАС" -> {
                return GenreComics.HORROR;
            }
            case "SCIENCE_FICTION", "SCIENCE FICTION", "SCIENCEFICTION", "НАУЧНАЯ ФАНТАСТИКА", "НАУЧНАЯ_ФАНТАСТИКА",
                    "НАУЧНАЯФАНТАСТИКА" -> {
                return GenreComics.SCIENCE_FICTION;
            }
            case "ANIME", "АНИМЕ" -> {
                return GenreComics.ANIME;
            }
            case "ACTION_MOVIE", "ACTION MOVIE", "ACTIONMOVIE", "БОЕВИК" -> {
                return GenreComics.ACTION_MOVIE;
            }
            case "MYSTICISM", "МИСТИКА" -> {
                return GenreComics.MYSTICISM;
            }
            case "fantasy", "ФЭНТЕЗИ" -> {
                return GenreComics.FANTASY;
            }
            case "FANTASY", "комедия" -> {
                return GenreComics.COMEDY;
            }
            default -> {
                return GenreComics.OTHER;
            }
        }
    }

    public static String checkNumberPhone(String phoneNumber) {                           // Метод проверки телефона
        Pattern pattern = Pattern.compile("^((\\+?7)([0-9]{10}))$");
        Matcher matcher = pattern.matcher(phoneNumber);
        if (matcher.matches()) {
            return phoneNumber;
        } else {
            return "Неизвестный номер";
        }
    }
}
