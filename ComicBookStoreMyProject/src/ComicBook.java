import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;



public class ComicBook implements Cloneable {
    private static int id = 1;                         //Статический индификатор занесённых комиксов
    private final int idComic;                               // Индификатор конкретного комикса
    private String comicBookName;                      // Название коммикса
    private String fullNameAuthor;                     // ФИО автора
    private String comicBookPublisher;                 // Издательство коммикса
    private int numberOfPages;                         // Количестов страниц
    private Enum<GenreComics> genreComics;             // Жанр комикса
    private BigDecimal costPrice;                      // Себестоймость комикса
    private BigDecimal salePrice;                      // Цена продажи
    private int yearPublication;                       // Год издания
    private String comicBookSeries;                    // Серия комиксов Todo Может сделать Enum или даже списком??
    private int numberOfComics;                        // Количество комиксов в магазине / количество проданных комиксов
    private LocalDate inputData;                       // Дата появления в магазине / дата продажи

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
            System.out.println("Введена некоректная дата" + e);
            this.inputData = LocalDate.now();
        }
        id++;
        idComic = id;
    }

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

    public String getComicBookPublisher() {                           // Получение названия издательства
        return comicBookPublisher;
    }

    public void setComicBookPublisher(String comicBookPublisher) {    // Изменение названия издательства
        this.comicBookPublisher = comicBookPublisher;
    }

    public int getNumberOfPages() {                                  // Получение количества страниц
        return numberOfPages;
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

    public BigDecimal getCostPrice() {                              // Получение цены себестоймости комикса
        return costPrice;
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

    public int getYearPublication() {                         // Получение года издательства комикса
        return yearPublication;
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

    @Override
    public String toString() {
        return "ComicBook{" +
                "ID Комикса = " + idComic +
                ", Название комикса - " + comicBookName +
                ", Количество комиксов = " + numberOfComics +
                ", ФИО автора - " + fullNameAuthor +
                ", Название издательства - " + comicBookPublisher +
                ", Количество страниц = " + numberOfPages +
                ", Жанр - " + genreComics +
                ", Закупочная цена = " + costPrice.setScale(2, RoundingMode.CEILING) +
                ", Цена продажи = " + salePrice.setScale(2, RoundingMode.CEILING) +
                ", Год издания - " + yearPublication +
                ", Серия комиксов - " + comicBookSeries +
                ", Дата появления - " + inputData.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) +
                '}' + '\n';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}