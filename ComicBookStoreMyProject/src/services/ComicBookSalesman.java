package services;

import domain.Client;
import domain.ComicBook;
import domain.GenreComics;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Основной класс по работе с комиксами.
 * Содержит всё методы обработки комикса: Добавление, удаление, списание, продажа, изменение параметров, резервирование,
 * продажа из списка зарезервированных, поиск по различным параметрам, объявление скидок на комиксы.
 * Содержит три основных списка:
 * Текущий список комиксов в магазине - listComic, Список проданных комиксов - listComicsSold,
 * список клиентов зарезервировавших комиксы в магазине.
 */


public class ComicBookSalesman implements Cloneable, Serializable {
    private List<ComicBook> listComic;                                    // - Список комиксов в магазине
    private List<ComicBook> listComicsSold;                               // - Список проданых комиксов
    private List<Client> clientList;                                      // - Список Клиентов

    public ComicBookSalesman() {
        listComic = new ArrayList<>();
        listComicsSold = new ArrayList<>();
        clientList = new ArrayList<>();
    }

    public ComicBookSalesman(List<ComicBook> listComic, List<ComicBook> listComicsSold, List<Client> clientList) {
        this.listComic = listComic;
        this.listComicsSold = listComicsSold;
        this.clientList = clientList;
    }

    public List<ComicBook> getListComic() {
        return listComic;
    }

    public List<ComicBook> getListComicsSold() {
        return listComicsSold;
    }

    public List<Client> getClientList() {
        return clientList;
    }

    /**
     * Метод добавления комикса с помощью ввода с клавиатуры.
     * Метод проверяет существует ли комикс с таким же названием в магазине и если существует увеличивает количество
     * данного комикса в магазине. Если комикс с таким названием отсутствует в магазине то добавляются остальные данные.
     */

    public void addComic() {
        ComicBook comicBook = new ComicBook();
        comicBook.setComicBookName(PrintHelper.enterComicBookName());                     // - Ввод Названия комикса
        ComicBook finalComicBook = comicBook;
        comicBook = listComic.stream()
                .filter(comicBookName -> comicBookName.getComicBookName().equals(finalComicBook.getComicBookName()))
                .findFirst().orElse(comicBook);
        // - Ввод количества комиксов
        comicBook.setNumberOfComics(comicBook.getNumberOfComics() + PrintHelper.enterNumberOfComics());
        comicBook.setInputData(PrintHelper.enterDateComics());
        if (comicBook.getFullNameAuthor() == null) {                                      // - Проверка добавления
            comicBook.setFullNameAuthor(PrintHelper.enterFullNameAuthor());              // - Ввод ФИО автора
            comicBook.setComicBookPublisher(PrintHelper.enterComicBookPublisher());      // - Ввод издательства
            comicBook.setNumberOfPages(PrintHelper.enterNumberOfPages());                // - Ввод количества страниц
            comicBook.setGenreComics(PrintHelper.enterGenreComics());                    // - Ввод жанра комиксов
            comicBook.setCostPrice(PrintHelper.enterCostPrice());                        // - Ввод себестоимости
            comicBook.setSalePrice(PrintHelper.enterSalePrice());                        // - Ввод стоимости комикса
            comicBook.setYearPublication(PrintHelper.enterYearPublication());            // - Ввод года публикации
            comicBook.setComicBookSeries(PrintHelper.enterComicBookSeries());            // - Ввод названия серии
            listComic.add(comicBook);
        }
    }

    /**
     * Метод добавления комикса в список. Используется при чтении из файла.
     *
     * @param comicBook комикс хранящийся в файле.
     */
    public void addComic(ComicBook comicBook) {
        listComic.add(comicBook);
    }

    /**
     * Метод удаления комиксов предстовляет собой удаление комикса методом клонирования.
     * Методы удаления включают в себя: Удаление комикса по объекту(deletingComic), поиск объекта по имени, поиск объекта
     * * по id.
     */

    private void deletingComic(ComicBook comicBook) {
        ArrayList<ComicBook> list = new ArrayList<ComicBook>();
        list.addAll(listComic);
        list.remove(comicBook);
        listComic = list;
    }

    public void deletingComicByName(String name) {
        try {
            ComicBook comicBook = searchObjectComicBook(name);
            deletingComic(comicBook);
        } catch (NoSuchElementException e) {
            System.out.println("Комикс с таким названием не найден");
        }
    }

    public void deletingComicById(int id) {
        try {
            ComicBook comicBook = searchObjectComicBook(id);
            deletingComic(comicBook);
        } catch (NoSuchElementException e) {
            System.out.println("Комикс с таким ID не найден");
        }
    }

    /**
     * Методы списания комиксов с вводом данных перенолсяться в UI
     */

    public void deletingComicWithDataEntryByName() {                        // Метод удаления по имени комикса с вводом
        deletingComicByName(PrintHelper.enterComicBookName());
    }

    public void deletingComicWithDataEntryById() {                          // Метод удаления по имени комикса с вводом
        deletingComicById(PrintHelper.enterComicId());
    }


    /**
     * Методы продажи комиксов. Продажа комиксов осуществляется путём списания комиксов, а затем переноса их в список
     * проданных магазином комиксов.
     * Методы продажи включают в себя: Продажа комикса по объекту(comicBookSales), поиск объекта по имени, поиск объекта
     * по id.
     */

    private void comicBookSales(ComicBook comicBook) {
        try {
            int valueWriteOffComicBook = writeOffComicBook(comicBook);
            ComicBook cloneComicBook = (ComicBook) comicBook.clone();
            listComicsSold.add(cloneComicBook);
            cloneComicBook.setNumberOfComics(valueWriteOffComicBook);
            cloneComicBook.setInputData(LocalDate.now());
        } catch (CloneNotSupportedException e) {
            System.out.println("Ошибка при клонировании объекта" + e);
        }
    }

    public String comicBookSalesByName(String name) {
        try {
            ComicBook comicBook = searchObjectComicBook(name);
            comicBookSales(comicBook);
            return "Комикс продан";
        } catch (NoSuchElementException e) {
            return "Комикс с таким названием не найден";
        }
    }

    public String comicBookSalesById(int id) {
        try {
            ComicBook comicBook = searchObjectComicBook(id);
            comicBookSales(comicBook);
            return "Комикс продан";
        } catch (NoSuchElementException e) {
            return "Комикс с таким названием не найден";
        }
    }

    /**
     * Метод списания комиксов осуществляется путём проверки количества комиксов в базе. В случае если количество
     * комиксов в базе равно 0 комикс удаляется.
     * Методы списания включают в себя: Продажа комикса по объекту(writeOffComicBook), поиск объекта по имени, поиск
     * объекта по id.
     */

    private int writeOffComicBook(ComicBook comicBook) {
        int valueWriteOffComicBook = PrintHelper.enterNumberOfComics();
        int result = comicBook.getNumberOfComics() - valueWriteOffComicBook;
        if (result > 0) {
            comicBook.setNumberOfComics(result);
        } else if (result == 0) {
            deletingComic(comicBook);
        } else {
            PrintHelper.writeOffComicBook();
            writeOffComicBook(comicBook);
        }
        return valueWriteOffComicBook;
    }

    public void writeOffComicByName(String name) {
        try {
            writeOffComicBook(searchObjectComicBook(name));
        } catch (NoSuchElementException e) {
            System.out.println("Комикс с таким названием не найден");
        }
    }

    public void writeOffComicBookById(int id) {
        try {
            writeOffComicBook(searchObjectComicBook(id));
        } catch (NoSuchElementException e) {
            System.out.println("Комикс с таким ID не найден");
        }
    }

    /**
     * Методы списания комиксов с вводом данных перенолсяться в UI
     */

    public void writeOffComicByIdWithDataEntry() {                            // Метод списания по ID  комикса с вводом
        writeOffComicBookById(PrintHelper.enterComicId());
    }

    public void writeOffComicByNameWithDataEntry() {                         // Метод списания по имени комикса с вводом
        writeOffComicByName(PrintHelper.enterComicBookName());
    }


    /**
     * Внутрение методы перебора коллекции для получения объекта
     * Включают в себя: Получение объекта по названию комикса(searchObjectComicBook), получение объекта по id
     * (searchObjectComicBook), метод получения объекта комикса среди зарезрвированных клиентом по id клиента и id комикса
     */

    private ComicBook searchObjectComicBook(String name) {
        return listComic.stream()
                .filter(ComicBookName -> ComicBookName.getComicBookName().equals(name))
                .findFirst().get();
    }

    private ComicBook searchObjectComicBook(int id) {
        return listComic.stream()
                .filter(ComicBookId -> ComicBookId.getIdComic() == id)
                .findFirst().get();
    }

    private ComicBook searchObjectComicBookClient(int id, int clientId) {     // Метод перебора коллекции клиента по ID
        Client client = clientList.stream()
                .filter(c -> c.getId() == clientId)
                .findFirst().get();
        return client.getReservingComics().stream()
                .filter(ComicBookId -> ComicBookId.getIdComic() == id)
                .findFirst().get();
    }

    /**
     * Редакция комиксов произходит путём замены параметров.
     * Включают в себя: редакция комикса(editComic), редакция комикса по имени(editComicByNameDataEntry), редакция
     * комикса по id (editComicByIdDataEntry)
     */

    private void editComic(ComicBook comicBook) {
        comicBook.setFullNameAuthor(PrintHelper.enterFullNameAuthor());            // - Ввод ФИО автора
        comicBook.setComicBookPublisher(PrintHelper.enterComicBookPublisher());    // - Ввод издательства
        comicBook.setNumberOfPages(PrintHelper.enterNumberOfPages());              // - Ввод количества страниц
        comicBook.setGenreComics(PrintHelper.enterGenreComics());                  // - Ввод жанра комиксов
        comicBook.setCostPrice(PrintHelper.enterCostPrice());                      // - Ввод себестоимости
        comicBook.setSalePrice(PrintHelper.enterSalePrice());                      // - Ввод стоимости комикса
        comicBook.setYearPublication(PrintHelper.enterYearPublication());          // - Ввод года публикации
        comicBook.setComicBookSeries(PrintHelper.enterComicBookSeries());          // - Ввод названия серии
        comicBook.setInputData(PrintHelper.enterDateComics());                     // - Ввод даты
    }

    public void editComicByName(String name) {
        try {
            ComicBook comicBook = searchObjectComicBook(name);
            String nameComicBook = comicBook.getComicBookName();
            comicBook.setComicBookName("NoN");
            String checkName = PrintHelper.enterComicBookName();
            boolean value;
            value = listComic.stream()
                    .anyMatch(ComicBookName -> ComicBookName.getComicBookName().equals(checkName));
            if (!value) {
                comicBook.setComicBookName(checkName);
                editComic(comicBook);
            } else {
                System.out.println("Комикс с таким названием существует");
                comicBook.setComicBookName(nameComicBook);
            }
        } catch (NoSuchElementException e) {
            System.out.println("Комикс с таким названием не найден");
        }
    }

    public void editComicById(int id) {
        try {
            ComicBook comicBook = searchObjectComicBook(id);
            editComic(comicBook);
        } catch (NoSuchElementException e) {
            System.out.println("Комикс с таким названием не найден");
        }
    }

    /**
     * Методы редоктирования комиксов с вводом данных перенолсяться в UI
     */

    public void editComicByIdDataEntry() {                            // Метод редоктирования по ID комикса с вводом
        editComicById(PrintHelper.enterComicId());
    }

    public void editComicByNameDataEntry() {                            // Метод списания по ID  комикса с вводом
        editComicByName(PrintHelper.enterComicBookName());
    }

    /**
     * Методы поиска комиксов в магазине по различным параметрам
     */

    /**
     * Поиск комикса по названию включая сопадения
     */
    public void searchForComicByTitle(String name) {                           //
        List<ComicBook> comicBook = listComic.stream()
                .filter(ComicBookName -> ComicBookName.getComicBookName().contains(name))
                .collect(Collectors.toList());
        comicBook.forEach(System.out::println);
    }

    /**
     * Поиск комикса по автору включая сопадения
     */

    public void searchForComicByAuthor(String name) {
        List<ComicBook> comicBook = listComic.stream()
                .filter(ComicBookName -> ComicBookName.getFullNameAuthor().contains(name))
                .collect(Collectors.toList());
        comicBook.forEach(System.out::println);
    }

    /**
     * Поиск комикса по жанру
     */

    public void searchForComicsByGenre(Enum<GenreComics> genreComics) {
        List<ComicBook> comicBook = listComic.stream()
                .filter(ComicBookName -> ComicBookName.getGenreComics().equals(genreComics))
                .collect(Collectors.toList());
        comicBook.forEach(System.out::println);
    }

    /**
     * Поиск новинок за период (день, неделя, месяц, год)
     */

    private void popularNewComics(LocalDate localDate) {                  //Метод поиска новинок относительно периода
        List<ComicBook> comicBook = listComic.stream()
                .filter(ComicBookName -> ComicBookName.getInputData().isAfter(localDate))
                .collect(Collectors.toList());
        comicBook.forEach(System.out::println);
    }

    /**
     * Методы редоктирования комиксов с вводом данных перенолсяться в UI
     */

    public void popularNewComicsForTheDay() {                             //Список комиксов появившихся магазине за день
        LocalDate localDate = LocalDate.now().minusDays(2);
        popularNewComics(localDate);
    }

    public void popularNewComicsForTheWeek() {                           //Список комиксов появившихся магазине за неделю
        LocalDate localDate = LocalDate.now().minusWeeks(1);
        popularNewComics(localDate);
    }

    public void popularNewComicsForTheMonth() {                         //Список комиксов появившихся магазине за месяц
        LocalDate localDate = LocalDate.now().minusMonths(1);
        popularNewComics(localDate);
    }

    public void popularNewComicsForTheYear() {                         //Список комиксов появившихся магазине за год
        LocalDate localDate = LocalDate.now().minusYears(1);
        popularNewComics(localDate);
    }

    public void searchForComicByTitleDataEntry() {                             // Метод поиска комикса по имени с вводом
        searchForComicByTitle(PrintHelper.enterComicBookName());
    }

    public void searchForComicByAuthorDataEntry() {                           // Метод поиска комикса по автору с вводом
        searchForComicByAuthor(PrintHelper.enterFullNameAuthor());
    }

    public void searchForComicsByGenreDataEntry() {                          // Метод поиска комикса по жанру с вводом
        Enum<GenreComics> genreComics = PrintHelper.enterGenreComics();
        searchForComicsByGenre(genreComics);
    }


    /**
     * Скидки на комиксы
     */

    private void makeDiscountOnComic(ComicBook comicBook) {                  // объявление скидки на комикс
        BigDecimal sale = PrintHelper.enterSale();
        comicBook.setSalePrice(comicBook.getSalePrice().subtract(comicBook.getSalePrice().multiply(sale.multiply
                (BigDecimal.valueOf(0.01)))));
    }

    private void makeDiscountOnListComics(ComicBook comicBook, BigDecimal sale) {         // объявление скидки на комикс
        comicBook.setSalePrice(comicBook.getSalePrice().subtract(comicBook.getSalePrice().multiply(sale.multiply
                (BigDecimal.valueOf(0.01)))));
    }

    public void makeDiscountOnComicByName() {                              // скидка на комикс поиск по имени
        makeDiscountOnComic(searchObjectComicBook(PrintHelper.enterComicBookName()));
    }

    public void makeDiscountOnComicByID() {                                // скидка на комикс поиск по ID
        makeDiscountOnComic(searchObjectComicBook(PrintHelper.enterComicId()));
    }

    public void makeDiscountOnALLComic() {                               // скидка на всё комиксы(чёрная пятница)
        BigDecimal sale = PrintHelper.enterSale();
        for (ComicBook comicBook : listComic) {
            makeDiscountOnListComics(comicBook, sale);
        }
    }

    public void makeDiscountOncomicBookSeries() {                       // скидка на комиксы по серии
        String series = PrintHelper.enterComicBookSeries();
        BigDecimal sale = PrintHelper.enterSale();
        for (ComicBook comicBook : listComic) {
            if (comicBook.getComicBookSeries().equals(series)) {
                makeDiscountOnListComics(comicBook, sale);
            }
        }
    }

    public void makeDiscountOncomicBookGenre() {                       // скидка на комиксы по жанру
        Enum<GenreComics> genre = PrintHelper.enterGenreComics();
        BigDecimal sale = PrintHelper.enterSale();
        for (ComicBook comicBook : listComic) {
            if (comicBook.getGenreComics().equals(genre)) {
                makeDiscountOnListComics(comicBook, sale);
            }
        }
    }

    /**
     * Резервирование комиксов по покупателю.
     */

    private void comicBookReserving(ComicBook comicBook, Client client) {    // Метод добавления резервированного
        try {                                                                // комикса в список клиента
            int valueWriteOffComicBook = writeOffComicBook(comicBook);
            ComicBook cloneComicBook = (ComicBook) comicBook.clone();
            client.getReservingComics().add(cloneComicBook);
            cloneComicBook.setNumberOfComics(valueWriteOffComicBook);
        } catch (CloneNotSupportedException e) {
            System.out.println("Ошибка при клонировании объекта" + e);
        }
    }

    public void comicBookReservingById(int id, int clientId) {               // Метод резервирования комикса по ID
        try {
            ComicBook comicBook = searchObjectComicBook(id);
            Client client = searchClient(clientId);
            comicBookReserving(comicBook, client);
        } catch (NoSuchElementException e) {
            System.out.println("Комикс с таким названием не найден");
        }
    }

    public void comicBookReservingDataEntryByIdd() {           // Метод резервирования по клиенту комикса и ID с вводом
        comicBookReservingById(PrintHelper.enterComicId(), PrintHelper.enterClientId());
    }

    public Map<String, List<ComicBook>> listOfAllReservedComics() {           //Вывод всех зарезервированных комиксов
        List<ComicBook> list;
        Map<String, List<ComicBook>> map = new LinkedHashMap<>();
        for (Client client : clientList) {
            list = new ArrayList<>();
            for (ComicBook comic : client.getReservingComics()) {
                list.add(comic);
                if (map.containsKey(client.getFullName())) {
                    map.replace(client.getFullName(), list);
                } else {
                    map.put(client.getFullName(), list);
                }
            }
        }
        return map;
    }

    public int saleComicBookReserving(ComicBook comicBook, Client client) { //Продажа комикса из списка зарезервированных
        int result = 0;
        int numberOfComics = 0;
        for (ComicBook comicBookClient : client.getReservingComics()) {
            if (comicBookClient.getIdComic() == comicBook.getIdComic()) {
                numberOfComics = comicBookClient.getNumberOfComics();
                result = comicBook.getNumberOfComics() - PrintHelper.enterNumberOfComics();
                if (result > 0) {
                    comicBook.setNumberOfComics(result);
                    return result;
                } else {
                    client.getReservingComics().remove(comicBookClient);
                    break;
                }
            } else {
                System.out.println("Комикс не найден");
            }
        }
        return numberOfComics;
    }

    public void salecomicBookReservingById(int id, int clientId) {               // Метод продажи комикса по ID
        try {
            saleComicBookReserving(searchObjectComicBookClient(id, clientId), searchClient(clientId));
        } catch (NoSuchElementException e) {
            System.out.println("Комикс с таким названием не найден");
        }
    }

    public void saleComicBookReservingDataEntryByIdd() {           // Метод резервирования по клиенту комикса и ID с вводом
        comicBookReservingById(PrintHelper.enterComicId(), PrintHelper.enterClientId());
    }

    public void returnComicBookReserving(ComicBook comicBook, Client client) {  //Возврат из списка зарезервированных в список комиксов
        int numberOfComic = saleComicBookReserving(comicBook, client);
        System.out.println(numberOfComic);
        for (ComicBook comicBoks : listComic) {
            if (comicBoks.getIdComic() == comicBook.getIdComic()) {
                comicBoks.setNumberOfComics(comicBoks.getNumberOfComics() + numberOfComic);
                break;
            } else {
                addComic(comicBook);
                break;
            }
        }
    }

    public void returncComicBookReservingById(int id, int clientId) {               // Метод продажи комикса по ID
        try {
            returnComicBookReserving(searchObjectComicBookClient(id, clientId), searchClient(clientId));
        } catch (NoSuchElementException e) {
            System.out.println("Комикс с таким названием не найден");
        }
    }

    public void returnComicBookReservingDataEntryByIdd() {           // Метод резервирования по клиенту комикса и ID с вводом
        comicBookReservingById(PrintHelper.enterComicId(), PrintHelper.enterClientId());
    }

    /**
     * Работа с клиентом
     */

    private Client searchClient(int id) {                              // Метод перебора коллекции по ID
        return clientList.stream()
                .filter(clientId -> clientId.getId() == id)
                .findFirst().get();
    }

    public void clientAdd(String васечкин_вася) {
        clientList.add(new Client(PrintHelper.enterClient(), new ArrayList<>(), PrintHelper.enterClientPhone()));
    }

    public void clientDelete() {
        try {
            clientList.remove(searchClient(PrintHelper.enterClientId()));
        } catch (NoSuchElementException e) {
            System.out.println("Такого клиента не существует");
        }
    }

    public void editClient() {
        try {
            Client client = searchClient(PrintHelper.enterClientId());
            client.setFullName(PrintHelper.enterClient());
        } catch (NoSuchElementException e) {
            System.out.println("Такого клиента не существует");
        }
    }

    public void popularArtistForTheDay() {                             //Список комиксов появившихся магазине за день
        LocalDate localDate = LocalDate.now().minusDays(2);
        searchListPopularArtists(localDate);
    }

    public void popularArtistForTheWeek() {                           //Список комиксов появившихся магазине за неделю
        LocalDate localDate = LocalDate.now().minusWeeks(1);
        searchListPopularArtists(localDate);
    }

    public void popularArtistForTheMonth() {                         //Список комиксов появившихся магазине за месяц
        LocalDate localDate = LocalDate.now().minusMonths(1);
        searchListPopularArtists(localDate);
    }

    public void popularArtistForTheYear() {                         //Список комиксов появившихся магазине за год
        LocalDate localDate = LocalDate.now().minusYears(1);
        searchListPopularArtists(localDate);
    }

    private void searchListPopularArtists(LocalDate localDate) {                  //Метод поиска новинок относительно периода
        searchPopularArtists(listComic.stream()
                .filter(ComicBookName -> ComicBookName.getInputData().isAfter(localDate))
                .collect(Collectors.toList()));

    }

    private void searchPopularArtists(List<ComicBook> comicBook) {                       //Поиск по самому популярному художнику
        Map<String, Integer> list = new HashMap<>();
        int count = 1;
        AtomicInteger place = new AtomicInteger(1);
        for (int i = 0; i < comicBook.size(); i++) {
            String nameAuthor = comicBook.get(i).getFullNameAuthor();
            if (list.containsKey(nameAuthor)) {
                list.put(nameAuthor, list.get(nameAuthor) + 1);
            } else {
                list.put(nameAuthor, count);
            }
        }
        list.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(p -> System.out.println(place.getAndIncrement() + " Автор - " + p.getKey() + " Количество комиксов " + p.getValue()));
    }


    public void popularGenreForTheDay() {                             //Список популярных жанров появившихся магазине за день
        LocalDate localDate = LocalDate.now().minusDays(2);
        searchListPopularGenre(localDate);
    }

    public void popularGenreForTheWeek() {                           //Список комиксов появившихся магазине за неделю
        LocalDate localDate = LocalDate.now().minusWeeks(1);
        searchListPopularGenre(localDate);
    }

    public void popularGenreForTheMonth() {                         //Список комиксов появившихся магазине за месяц
        LocalDate localDate = LocalDate.now().minusMonths(1);
        searchListPopularGenre(localDate);
    }

    public void popularGenreForTheYear() {                         //Список популярных жанров появившихся магазине за год
        LocalDate localDate = LocalDate.now().minusYears(1);
        searchListPopularGenre(localDate);
    }

    private void searchListPopularGenre(LocalDate localDate) {                  //Метод поиска новинок относительно периода
        searchPopularGenre(listComic.stream()
                .filter(ComicBookName -> ComicBookName.getInputData().isAfter(localDate))
                .collect(Collectors.toList()));

    }

    private void searchPopularGenre(List<ComicBook> comicBook) {                       //Поиск по самому популярному жанру
        Map<String, Integer> list = new HashMap<>();
        int count = 1;
        AtomicInteger place = new AtomicInteger(1);
        for (int i = 0; i < comicBook.size(); i++) {
            String genreComics = comicBook.get(i).getGenreComics().toString();
            if (list.containsKey(genreComics)) {
                list.put(genreComics, list.get(genreComics) + 1);
            } else {
                list.put(genreComics, count);
            }
        }

        list.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(p -> System.out.println(place.getAndIncrement() + " Жанр комикса - " + p.getKey() + " Количество комиксов " + p.getValue()));
    }


    /**
     * ПРоданные комиксы
     */

    public void salePopularArtistForTheDay() {                             //Список комиксов появившихся магазине за день
        LocalDate localDate = LocalDate.now().minusDays(2);
        searchListPopularArtists(localDate);
    }

    public void salePopularArtistForTheWeek() {                           //Список комиксов появившихся магазине за неделю
        LocalDate localDate = LocalDate.now().minusWeeks(1);
        searchListPopularArtists(localDate);
    }

    public void salePopularArtistForTheMonth() {                         //Список комиксов появившихся магазине за месяц
        LocalDate localDate = LocalDate.now().minusMonths(1);
        searchListPopularArtists(localDate);
    }

    public void salePopularArtistForTheYear() {                         //Список комиксов появившихся магазине за год
        LocalDate localDate = LocalDate.now().minusYears(1);
        searchListPopularArtists(localDate);
    }

    private void searchListdSalePopularArtists(LocalDate localDate) {                  //Метод поиска новинок относительно периода
        searchPopularArtists(listComicsSold.stream()
                .filter(ComicBookName -> ComicBookName.getInputData().isAfter(localDate))
                .collect(Collectors.toList()));
    }

    public void salePopularGenreForTheDay() {                             //Список популярных жанров появившихся магазине за день
        LocalDate localDate = LocalDate.now().minusDays(2);
        searchListPopularGenre(localDate);
    }

    public void salePopularGenreForTheWeek() {                           //Список комиксов появившихся магазине за неделю
        LocalDate localDate = LocalDate.now().minusWeeks(1);
        searchListPopularGenre(localDate);
    }

    public void salePopularGenreForTheMonth() {                         //Список комиксов появившихся магазине за месяц
        LocalDate localDate = LocalDate.now().minusMonths(1);
        searchListPopularGenre(localDate);
    }

    public void salePopularGenreForTheYear() {                         //Список популярных жанров появившихся магазине за год
        LocalDate localDate = LocalDate.now().minusYears(1);
        searchListPopularGenre(localDate);
    }

    private void searchListSalePopularGenre(LocalDate localDate) {                  //Метод поиска новинок относительно периода
        searchPopularGenre(listComicsSold.stream()
                .filter(ComicBookName -> ComicBookName.getInputData().isAfter(localDate))
                .collect(Collectors.toList()));
    }

    /**
     * Сохранение методов
     */
    public void saveListComics() {
        saveComicBook(listComic, "listComic.bin");
    }

    public void saveListComicsSold() {
        saveComicBook(listComicsSold, "listComicSold.bin");
    }

    private void saveComicBook(List<ComicBook> arrays, String way) {
        ComicBook[] comicBooks = new ComicBook[arrays.size()];
        for (int i = 0; i < comicBooks.length; i++) {
            comicBooks[i] = arrays.get(i);
        }
        try {
            FileOutputStream fos = new FileOutputStream(way);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeInt(comicBooks.length);
            for (ComicBook comicBook : comicBooks) {
                oos.writeObject(comicBook);
            }
            fos.close();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveListClient() {
        saveClient(clientList, "clientList.bin");
    }

    private void saveClient(List<Client> arrays, String way) {
        Client[] clients = new Client[arrays.size()];
        for (int i = 0; i < clients.length; i++) {
            clients[i] = arrays.get(i);
        }
        try {
            FileOutputStream fos = new FileOutputStream(way);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeInt(clients.length);
            for (Client client : clients) {
                oos.writeObject(client);
            }
            fos.close();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readListComics() {
        readComicBook(listComic, "listComic.bin");
    }

    public void readListComicsSold() {
        readComicBook(listComicsSold, "listComicSold.bin");
    }


    private void readComicBook(List<ComicBook> arrays, String way) {
        try {
            FileInputStream fis = new FileInputStream(way);
            ObjectInputStream ois = new ObjectInputStream(fis);
            int count = ois.readInt();
            for (int i = 0; i < count; i++) {
                arrays.add((ComicBook) ois.readObject());
            }
            fis.close();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void readListClient() {
        readClient(clientList, "clientList.bin");
    }

    private void readClient(List<Client> arrays, String way) {
        try {
            FileInputStream fis = new FileInputStream(way);
            ObjectInputStream ois = new ObjectInputStream(fis);
            int count = ois.readInt();
            for (int i = 0; i < count; i++) {
                arrays.add((Client) ois.readObject());
            }
            fis.close();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}