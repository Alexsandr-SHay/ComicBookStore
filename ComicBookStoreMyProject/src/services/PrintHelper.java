package services;


import domain.GenreComics;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PrintHelper {                                     // Класс для печати и ввода информации
    static Scanner scanner = new Scanner(System.in);

    private static String dataInputString() {                          // Метод ввода данных по стороке
        return scanner.nextLine();
    }

    private static int dataInputInteger() {                           // Метод ввода данных с проверкой на стоимость
        String string = scanner.nextLine();
        try {
            int value = Integer.parseInt(string);
            if (value > 0) {
                return value;
            } else {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            System.out.println("Введены некоректные данные. Введите значение ещё раз");
        }
        return dataInputInteger();
    }

    private static String checkNumberPhone() {                           // Метод проверки телефона
        String phoneNumber = scanner.nextLine();
        Pattern pattern = Pattern.compile("^((\\+?7)([0-9]{10}))$");
        Matcher matcher = pattern.matcher(phoneNumber);
        if (matcher.matches()){
            return phoneNumber;
        } else {
            System.out.println("Введены некоректные данные. Введите значение ещё раз");
        }
        return checkNumberPhone();
    }

    private static double dataInputDouble() {                           // Метод ввода данных с проверкой на число
        String string = scanner.nextLine();
        try {
            double value = Double.parseDouble(string);
            if (value > 0) {
                return value;
            } else {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            System.out.println("Введены некоректные данные. Введите значение ещё раз");
        }
        return dataInputDouble();
    }

    private static BigDecimal dataInputBigDecimal() {                     // Метод ввода данных с форматом BigDecimal
        double value = dataInputDouble();
        while (value < 0) {
            System.out.println("Введены некоректные данные. Введите значение ещё раз");
            value = dataInputDouble();
        }
        return BigDecimal.valueOf(value);
    }

    private static int checkYear() {                                     // Проверка соответствия года выпуска
        int yearOfPublication = dataInputInteger();
        while (yearOfPublication < 1900 || yearOfPublication > 2021) {
            System.out.println("Неверный год издания");
            yearOfPublication = dataInputInteger();
        }
        return yearOfPublication;
    }

    private static Enum<GenreComics> checkGenreComics() {                // Выбор жанра комикса по вводу
        String genreComic = scanner.nextLine();
        if (genreComic.equalsIgnoreCase("DETECTIVE") ||
                genreComic.equalsIgnoreCase("детектив")) {
            return GenreComics.DETECTIVE;
        } else if (genreComic.equalsIgnoreCase("HORROR") ||
                genreComic.equalsIgnoreCase("ужас")) {
            return GenreComics.HORROR;
        } else if (genreComic.equalsIgnoreCase("SCIENCE_FICTION") ||
                genreComic.equalsIgnoreCase("Научная фантастика")) {
            return GenreComics.SCIENCE_FICTION;
        } else if (genreComic.equalsIgnoreCase("ANIME") ||
                genreComic.equalsIgnoreCase("Аниме")) {
            return GenreComics.ANIME;
        } else if (genreComic.equalsIgnoreCase("ACTION_MOVIE") ||
                genreComic.equalsIgnoreCase("Боевик")) {
            return GenreComics.ACTION_MOVIE;
        } else if (genreComic.equalsIgnoreCase("MYSTICISM") ||
                genreComic.equalsIgnoreCase("Мистика")) {
            return GenreComics.MYSTICISM;
        } else if (genreComic.equalsIgnoreCase("FANTASY") ||
                genreComic.equalsIgnoreCase("Фэнтези")) {
            return GenreComics.FANTASY;
        } else if (genreComic.equalsIgnoreCase("COMEDY") ||
                genreComic.equalsIgnoreCase("Комедия")) {
            return GenreComics.COMEDY;
        } else return GenreComics.OTHER;
    }

    public static String enterComicBookName() {                        // Ввод названия комикса
        System.out.println("Введите название комикса");
        return dataInputString();
    }

    public static String enterFullNameAuthor() {                        // Ввод ФИО автора
        System.out.println("Введите ФИО издателя");
        return dataInputString();
    }

    public static String enterComicBookPublisher() {                     // Ввод издательства
        System.out.println("Введите название издательства комиксов");
        return dataInputString();
    }

    public static int enterNumberOfPages() {                            // Ввод количества страниц
        System.out.println("Введите количество страниц в комиксе");
        return dataInputInteger();
    }

    public static BigDecimal enterCostPrice() {                        // Ввод себестоимости комикса
        System.out.println("Введите себестоймость комикса");
        return dataInputBigDecimal();
    }

    public static BigDecimal enterSalePrice() {                        // Ввод стоимости продажи комикса
        System.out.println("Введите цену продажи комикса");
        return dataInputBigDecimal();
    }

    public static int enterYearPublication() {                         // Ввод года издания
        System.out.println("Введите год издания");
        return checkYear();
    }

    public static String enterComicBookSeries() {                        // Ввод серии комиксов
        System.out.println("Введите серию комиксов");
        return dataInputString();
    }

    public static int enterNumberOfComics() {                            // Ввод количества комиксов
        System.out.println("Введите количество комиксов");
        return dataInputInteger();
    }

    public static Enum<GenreComics> enterGenreComics() {                // Ввод жанра комиксов
        System.out.println("Введите жанр комикса");
        return checkGenreComics();
    }

    public static int enterComicId() {                                   // Ввод количества комиксов
        System.out.println("Введите ID комикса");
        return dataInputInteger();
    }

    public static void writeOffComicBook(){
        System.out.println("Количество комиксов в магазине меньше чем Вы хотите списать");
    }

    public static String enterClient() {                                // Ввод имени клиента
        System.out.println("Введите полную Фамилию, Имя, Отчество(если имеется)");
        return dataInputString();
    }

    public static int enterClientId() {                                // Ввод Id клиента
        System.out.println("Введите ID клиента");
        return dataInputInteger();
    }

    public static String enterClientPhone() {
        System.out.println("Введите телевон клиента");
        return checkNumberPhone();
    }

    public static void closeScanner() {
        scanner.close();
    }


    /**
     * Новые методы
     */

    public static LocalDate enterDateComics() {                     // Ввод даты
        System.out.println("Введите дату в формате dd.MM.yyyy");
        return enterDate();

    }

    public static BigDecimal enterSale(){                                  // Ввод скидки
        System.out.println("Введите % скидки");
        return dataInputBigDecimal();
    }

    public static LocalDate enterDate() {
        try {
            LocalDate date1 = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            LocalDate date2 = LocalDate.now().plusMonths(1);
            if (date1.isBefore(date2)){
                return date1;
            } else {
                System.out.println("Введена неверная дата");
            }
        } catch (DateTimeParseException e) {
            System.out.println("Введена некоретная дата");
        }
        return enterDate();
    }
}