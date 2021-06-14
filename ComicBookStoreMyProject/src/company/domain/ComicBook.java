package company.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static company.domain.GenreComics.COMEDY;

/**
 * Класс комикс хранит в себе всю информацию о комиксе:
 * id комикса(idComic), название(comicBookName), ФИО автора(fullNameAuthor), название издательства(comicBookPublisher),
 * количество страниц(numberOfPages), жанр комикса(genreComics), себестоймость комикса(Себестоймость комикса),
 * цена продажи(Цена продажи), год издания(yearPublication), серию комиксов(Серия комиксов),
 * количество комиксов в магазине(numberOfComics), дата появления в магазине(inputData).
 * Основные методы реализованные в данном классе это сеттеры и гетеры и переопределённые методы toString() и clone().
 */

public class ComicBook implements Cloneable, Serializable {
    private static int id = 1;                         //Статический индификатор занесённых комиксов
    private final int idComic;                         // Индификатор конкретного комикса
    private String comicBookName;                      // Название коммикса
    private String fullNameAuthor;                     // ФИО автора
    private String comicBookPublisher;                 // Издательство коммикса
    private int numberOfPages;                         // Количестов страниц
    private Enum<GenreComics> genreComics;             // Жанр комикса
    private BigDecimal costPrice;                      // Себестоймость комикса
    private BigDecimal salePrice;                      // Цена продажи
    private int yearPublication;                       // Год издания
    private String comicBookSeries;                    // Серия комиксов
    private int numberOfComics;                        // Количество комиксов в магазине / количество проданных комиксов
    private LocalDate inputData;                       // Дата появления в магазине / дата продажи

    /**
     * Конструктор для чтения из файла.
     */

    public ComicBook(String comicBookName, String fullNameAuthor, String comicBookPublisher, int numberOfPages,
                     Enum<GenreComics> genreComics, BigDecimal costPrice, BigDecimal salePrice, int yearPublication,
                     String comicBookSeries, int numberOfComics, String inputData) {
        this.comicBookName = comicBookName;
        this.fullNameAuthor = fullNameAuthor;
        this.comicBookPublisher = comicBookPublisher;
        this.numberOfPages = numberOfPages;
        this.genreComics = genreComics;
        this.costPrice = costPrice;
        this.salePrice = salePrice;
        this.yearPublication = yearPublication;
        this.comicBookSeries = comicBookSeries;
        this.numberOfComics = numberOfComics;
        try {
            this.inputData = LocalDate.parse(inputData, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        } catch (DateTimeParseException e) {
            this.inputData = LocalDate.now();        }
        id++;
        idComic = id;
    }

    public ComicBook(String comicBookName, int numberOfComics){
        this.comicBookName = comicBookName;
        this.numberOfComics = numberOfComics;
        fullNameAuthor = "fullNameAuthor";
        comicBookPublisher = "comicBookPublisher";
        numberOfPages = 125;
        genreComics = COMEDY;
        costPrice = BigDecimal.valueOf(15);
        salePrice = BigDecimal.valueOf(15);
        yearPublication = 1999;
        comicBookSeries = "other";
        inputData = LocalDate.parse("15.02.2020", DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        id++;
        idComic = id;
    }


    /**
     * Контруктор при создании объекта через ввод данных.
     */

    public ComicBook() {
        id++;
        idComic = id;
    }

    public static int getId() {                                         // Получение общего id(общий id изменять нельзя)
        return id;
    }

    public int getIdComic() {                                          // Id у комикса устанавливается один раз и неизм.
        return idComic;
    }

    public String getComicBookName() {                                 // Получение названия коммикса
        return comicBookName;
    }

    public void setComicBookName(String comicBookName) {               // Изменение названия коммикса
        this.comicBookName = comicBookName;
    }

    public String getFullNameAuthor() {                                // Получение ФИО автора
        return fullNameAuthor;
    }

    public void setFullNameAuthor(String fullNameAuthor) {             // Изменение ФИО автора
        this.fullNameAuthor = fullNameAuthor;
    }

    public void setComicBookPublisher(String comicBookPublisher) {    // Изменение названия издательства
        this.comicBookPublisher = comicBookPublisher;
    }

    public void setNumberOfPages(int numberOfPages) {                // Изменение количества страниц
        this.numberOfPages = numberOfPages;
    }

    public Enum<GenreComics> getGenreComics() {                     // Получение Жанра комиксов
        return genreComics;
    }

    public void setGenreComics(Enum<GenreComics> genreComics) {     // Изменение Жанра комиксов
        this.genreComics = genreComics;
    }

    public void setCostPrice(BigDecimal costPrice) {               // Изменение цены себестоймости комикса
        this.costPrice = costPrice;
    }

    public BigDecimal getSalePrice() {                            // Получение цены продажи комикса
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {              // Изменение цены продажи комикса
        this.salePrice = salePrice;
    }

    public void setYearPublication(int yearPublication) {     // Изменение года издательства комикса
        this.yearPublication = yearPublication;
    }

    public String getComicBookSeries() {                         // Получение серии комикса
        return comicBookSeries;
    }

    public void setComicBookSeries(String comicBookSeries) {    // Изменение серии комикса
        this.comicBookSeries = comicBookSeries;
    }

    public int getNumberOfComics() {                           // Получение колличества комиксов
        return numberOfComics;
    }

    public void setNumberOfComics(int numberOfComics) {       // Изменение количества комиксов
        this.numberOfComics = numberOfComics;
    }

    public LocalDate getInputData() {
        return inputData;
    }

    public void setInputData(LocalDate inputData) {
        this.inputData = inputData;
    }

    public void warningSetId(int idComic){
      id = idComic;
    }

    @Override
    public String toString() {
        return "Комикс " +
                "ID = " + idComic + ';' +
                ", Название комикса - " + comicBookName + ';' +
                ", Количество комиксов = " + numberOfComics + ';' + '\n' +
                ", ФИО автора - " + fullNameAuthor + ';' +
                ", Название издательства - " + comicBookPublisher + ';' +
                ", Количество страниц = " + numberOfPages + ';' +
                ", Жанр - " + genreComics + ';' + '\n' +
                ", Закупочная цена = " + costPrice.setScale(2, RoundingMode.CEILING) + ';' +
                ", Цена продажи = " + salePrice.setScale(2, RoundingMode.CEILING) + ';' +
                ", Год издания - " + yearPublication + ';' + '\n' +
                ", Серия комиксов - " + comicBookSeries + ';' +
                ", Дата появления - " + inputData.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + ';' + '\n' + '\n';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
