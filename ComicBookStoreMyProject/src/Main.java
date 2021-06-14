import domain.Client;
import domain.ComicBook;
import domain.GenreComics;
import services.ComicBookSalesman;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ComicBookSalesman comicBookSalesman = new ComicBookSalesman(getComicBook(), new ArrayList<>(), getClient());

        comicBookSalesman.addComic();                                // Добаление комикса с клавиатуры
        comicBookSalesman.addComic();                                // Добаление комикса с клавиатуры
        comicBookSalesman.deletingComicByName("Бэтмен");             // Удаление комикса по имени
        comicBookSalesman.deletingComicById(5);                      // Удаление комикса по ID
        comicBookSalesman.writeOffComicByIdWithDataEntry();          // Метод списания комикса при вводе ID с клавиатуры
        comicBookSalesman.searchForComicByTitle("Бэтмен");     // Поиск комиксов по названию
        comicBookSalesman.searchForComicByAuthorDataEntry();         // Поиск комиксов по автору с вводом с клавиатуры
        comicBookSalesman.searchForComicsByGenreDataEntry();         // Поиск комиксов по жанру с вводом с клавиатуры
        comicBookSalesman.editComicByNameDataEntry();                // Редоктирование комикса по имени

        System.out.println(comicBookSalesman.getListComic());
    }


    private static List<ComicBook> getComicBook() {
        return List.of(
                new ComicBook("Могучие Рейнджеры", "Михаэль Иванович Шумахер",
                        "Росма", 45, GenreComics.FANTASY, BigDecimal.valueOf(152),
                        BigDecimal.valueOf(180), 1999, "Реиджеры", 5,
                        "12.01.2021"),
                new ComicBook("Бэтмен", "Грант Моррисон",
                        "DC Comics", 102, GenreComics.ACTION_MOVIE, BigDecimal.valueOf(300),
                        BigDecimal.valueOf(450), 2002, "Batman", 3,
                        "25.12.2020"),
                new ComicBook("Бэтмен возвращается", "Грант Моррисон",
                        "DC Comics", 91, GenreComics.ACTION_MOVIE, BigDecimal.valueOf(280),
                        BigDecimal.valueOf(400), 2005, "Batman", 6,
                        "06.03.2021"),
                new ComicBook("Бэтмен и Робин", "Грант Моррисон",
                        "DC Comics", 54, GenreComics.ACTION_MOVIE, BigDecimal.valueOf(120),
                        BigDecimal.valueOf(180), 2006, "Batman", 4,
                        "01.02.2019"),
                new ComicBook("Бэтмен и джокер", "Грант Моррисон",
                        "DC Comics", 91, GenreComics.ACTION_MOVIE, BigDecimal.valueOf(280),
                        BigDecimal.valueOf(400), 2005, "Batman", 2,
                        "15.01.2021"),
                new ComicBook("Джокер", "Френк Синатра",
                        "DC Comics", 10, GenreComics.ACTION_MOVIE, BigDecimal.valueOf(80),
                        BigDecimal.valueOf(100), 2010, "joker", 1,
                        "12.04.2021"),
                new ComicBook("Первый русский комикс", "Михаэль Иванович Шумахер",
                        "Росма", 30, GenreComics.MYSTICISM, BigDecimal.valueOf(50),
                        BigDecimal.valueOf(60), 2009, "Other", 10,
                        "13.04.2021"),
                new ComicBook("История в лесу", "Грант Моррисон",
                        "Росма", 90, GenreComics.MYSTICISM, BigDecimal.valueOf(80),
                        BigDecimal.valueOf(100), 2017, "Other", 8,
                        "01.05.2021"),
                new ComicBook("История в лесу продолжение", "Неизвестен",
                        "Росма", 61, GenreComics.MYSTICISM, BigDecimal.valueOf(40),
                        BigDecimal.valueOf(60), 2018, "Other", 2,
                        "07.07.2020"),
                new ComicBook("Могучие Рейнджеры возвращаются", "Михаэль Иванович Шумахер",
                        "Росма", 28, GenreComics.FANTASY, BigDecimal.valueOf(32),
                        BigDecimal.valueOf(45), 2019, "Реиджеры", 1,
                        "08.05.2021"),
                new ComicBook("Могучие Рейнджеры возвращаются111", "Михаэль Иванович Шумахер",
                        "Росма", 28, GenreComics.OTHER, BigDecimal.valueOf(32),
                        BigDecimal.valueOf(45), 2019, "Реиджеры", 1,
                        "08.05.2021")
        );
    }

    private static List<Client> getClient(){
        return List.of(
                new Client("Сидоров Алексей", new ArrayList<>(), "89991234567"),
                new Client("Иванов Иван", new ArrayList<>(), "89992345678"),
                new Client("Петров Пётр", new ArrayList<>(), "89993456789"));
    }


}
