package company.services;

import company.domain.Client;
import company.domain.ComicBook;
import company.domain.GenreComics;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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

    public boolean addComic(String comicBookName, int getNumberOfComic) {
        try {
            ComicBook comicBook = listComic.stream()
                    .filter(comicName -> comicName.getComicBookName().equals(comicBookName))
                    .findFirst().get();
            int result = comicBook.getNumberOfComics() + getNumberOfComic;
            comicBook.setNumberOfComics(result);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void addComic(String comicBookName,
                         String fullNameAuthor,
                         String comicBookPublisher,
                         int numberOfPages,
                         Enum<GenreComics> genreComics,
                         BigDecimal costPrice,
                         BigDecimal salePrice,
                         int yearPublication,
                         String comicBookSeries,
                         int numberOfComics,
                         String inputData) {

        listComic.add(new ComicBook(comicBookName,
                fullNameAuthor,
                comicBookPublisher,
                numberOfPages,
                genreComics,
                costPrice,
                salePrice,
                yearPublication,
                comicBookSeries,
                numberOfComics,
                inputData));
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

    public boolean deletingComicByName(String name) {
        try {
            ComicBook comicBook = searchObjectComicBook(name);
            deletingComic(comicBook);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean deletingComicById(int id) {
        try {
            ComicBook comicBook = searchObjectComicBook(id);
            deletingComic(comicBook);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Редакция комиксов произходит путём замены параметров.
     * Включают в себя: редакция комикса(editComic), редакция комикса по имени(editComicByNameDataEntry), редакция
     * комикса по id (editComicByIdDataEntry)
     */

    public boolean checkComicBookId(int id) {
        return listComic.stream()
                .anyMatch(ComicBookName -> ComicBookName.getIdComic() == id);
    }

    public boolean checkComicBookName(String name) {
        return listComic.stream()
                .anyMatch(ComicBookName -> ComicBookName.getComicBookName().equals(name));
    }

    private void editComic(ComicBook comicBook,
                           String comicName,
                           String fullNameAuthor,
                           String comicBookPublisher,
                           int numberOfPages,
                           Enum<GenreComics> genreComics,
                           BigDecimal costPrice,
                           BigDecimal salePrice,
                           int yearPublication,
                           String comicBookSeries,
                           int numberOfComic,
                           String dateComics) {

        comicBook.setComicBookName(comicName);                  // - Дата комикса
        comicBook.setFullNameAuthor(fullNameAuthor);            // - ФИО автора
        comicBook.setComicBookPublisher(comicBookPublisher);    // - Издательства
        comicBook.setNumberOfPages(numberOfPages);              // - Количества страниц
        comicBook.setGenreComics(genreComics);                  // - Жанра комиксов
        comicBook.setCostPrice(costPrice);                      // - Себестоимость
        comicBook.setSalePrice(salePrice);                      // - Стоимость комикса
        comicBook.setYearPublication(yearPublication);          // - Год публикации
        comicBook.setComicBookSeries(comicBookSeries);          // - Название серии
        comicBook.setNumberOfComics(numberOfComic);             // - Количество комиксов
        try {
            comicBook.setInputData(LocalDate.parse(dateComics, DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        } catch (DateTimeParseException e) {
            comicBook.setInputData(LocalDate.now());
        }
    }

    public void editComicByName(
            String comicName,
            String fullNameAuthor,
            String comicBookPublisher,
            int numberOfPages,
            Enum<GenreComics> genreComics,
            BigDecimal costPrice,
            BigDecimal salePrice,
            int yearPublication,
            String comicBookSeries,
            int numberOfComic,
            String dateComics) {
        ComicBook comicBook = searchObjectComicBook(comicName);
        editComic(comicBook,
                comicName,
                fullNameAuthor,
                comicBookPublisher,
                numberOfPages,
                genreComics,
                costPrice,
                salePrice,
                yearPublication,
                comicBookSeries,
                numberOfComic,
                dateComics);
    }

    public void editComicById(int id,
                              String comicName,
                              String fullNameAuthor,
                              String comicBookPublisher,
                              int numberOfPages,
                              Enum<GenreComics> genreComics,
                              BigDecimal costPrice,
                              BigDecimal salePrice,
                              int yearPublication,
                              String comicBookSeries,
                              int numberOfComic,
                              String dateComics) {
        ComicBook comicBook = searchObjectComicBook(id);
        if (checkComicBookName(comicName)) {
            comicName = PrintHelper.COMIC_EXISTS_CORRECT_NAME + comicName;
        }
        editComic(comicBook,
                comicName,
                fullNameAuthor,
                comicBookPublisher,
                numberOfPages,
                genreComics,
                costPrice,
                salePrice,
                yearPublication,
                comicBookSeries,
                numberOfComic,
                dateComics);
    }

    /**
     * Методы продажи комиксов. Продажа комиксов осуществляется путём списания комиксов, а затем переноса их в список
     * проданных магазином комиксов.
     * Методы продажи включают в себя: Продажа комикса по объекту(comicBookSales), поиск объекта по имени, поиск объекта
     * по id.
     */

    private void comicBookSales(ComicBook comicBook, int value) {
        try {
            int valueWriteOffComicBook = writeOffComicBook(comicBook, value);
            ComicBook cloneComicBook = (ComicBook) comicBook.clone();
            listComicsSold.add(cloneComicBook);
            cloneComicBook.setNumberOfComics(valueWriteOffComicBook);
            cloneComicBook.setInputData(LocalDate.now());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    public boolean comicBookSalesByName(String name, int valueSaleComicBook) {
        try {
            ComicBook comicBook = searchObjectComicBook(name);
            comicBookSales(comicBook, valueSaleComicBook);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean comicBookSalesById(int id, int valueSaleComicBook) {
        try {
            ComicBook comicBook = searchObjectComicBook(id);
            comicBookSales(comicBook, valueSaleComicBook);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Метод списания комиксов осуществляется путём проверки количества комиксов в базе. В случае если количество
     * комиксов в базе равно 0 комикс удаляется.
     * Методы списания включают в себя: Продажа комикса по объекту(writeOffComicBook), поиск объекта по имени, поиск
     * объекта по id.
     */

    private int writeOffComicBook(ComicBook comicBook, int valueWriteOffComicBook) throws NullPointerException {
        int result = comicBook.getNumberOfComics() - valueWriteOffComicBook;
        if (result > 0) {
            comicBook.setNumberOfComics(result);
        } else {
            deletingComic(comicBook);
        }
        return valueWriteOffComicBook;
    }

    public boolean writeOffComicByName(String name, int valueWriteOffComicBook) {
        try {
            writeOffComicBook(searchObjectComicBook(name), valueWriteOffComicBook);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean writeOffComicBookById(int id, int valueWriteOffComicBook) {
        try {
            writeOffComicBook(searchObjectComicBook(id), valueWriteOffComicBook);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Внутрение методы перебора коллекции для получения объекта
     * Включают в себя: Получение объекта по названию комикса(searchObjectComicBook), получение объекта по id
     * (searchObjectComicBook), метод получения объекта комикса среди зарезрвированных клиентом по id клиента и id комикса
     */

    public ComicBook searchObjectComicBook(String name) {
        return listComic.stream()
                .filter(ComicBookName -> ComicBookName.getComicBookName().equals(name))
                .findFirst().get();
    }

    public ComicBook searchObjectComicBook(int id) {
        return listComic.stream()
                .filter(ComicBookId -> ComicBookId.getIdComic() == id)
                .findFirst().get();
    }

    public ComicBook searchObjectComicBookClient(int id, Client client) {
        return client.getReservingComics().stream()
                .filter(ComicBookId -> ComicBookId.getIdComic() == id)
                .findFirst().get();
    }

    public ComicBook searchObjectComicBookClient(String name, Client client) {
        return client.getReservingComics().stream()
                .filter(ComicBookId -> ComicBookId.getComicBookName().equals(name))
                .findFirst().get();
    }

    /**
     * Методы поиска комиксов в магазине по различным параметрам
     */

    /**
     * Поиск комикса по названию включая сопадения
     */
    public void searchForComicByTitle(String name) {
        PrintHelper.setListResult(listComic.stream()
                .filter(ComicBookName -> ComicBookName.getComicBookName().contains(name))
                .collect(Collectors.toList()));
    }

    /**
     * Поиск комикса по автору включая сопадения
     */

    public void searchForComicByAuthor(String name) {
        PrintHelper.setListResult(listComic.stream()
                .filter(ComicBookName -> ComicBookName.getFullNameAuthor().contains(name))
                .collect(Collectors.toList()));
    }

    /**
     * Поиск комикса по жанру
     */

    public void searchForComicsByGenre(Enum<GenreComics> genreComics) {
        PrintHelper.setListResult(listComic.stream()
                .filter(ComicBookName -> ComicBookName.getGenreComics().equals(genreComics))
                .collect(Collectors.toList()));
    }

    /**
     * Поиск новинок за период (день, неделя, месяц, год)
     */

    private void popularNewComics(LocalDate localDate) {
        PrintHelper.setListResult(listComic.stream()
                .filter(ComicBookName -> ComicBookName.getInputData().isAfter(localDate))
                .collect(Collectors.toList()));
    }

    public void popularNewComicsForTheDay() {
        LocalDate localDate = LocalDate.now().minusDays(2);
        popularNewComics(localDate);
    }

    public void popularNewComicsForTheWeek() {
        LocalDate localDate = LocalDate.now().minusWeeks(1);
        popularNewComics(localDate);
    }

    public void popularNewComicsForTheMonth() {
        LocalDate localDate = LocalDate.now().minusMonths(1);
        popularNewComics(localDate);
    }

    public void popularNewComicsForTheYear() {
        LocalDate localDate = LocalDate.now().minusYears(1);
        popularNewComics(localDate);
    }

    /**
     * Поиск проданных комиксов за период (день, неделя, месяц, год)
     */

    private void popularNewComicsSale(LocalDate localDate) {
        PrintHelper.setListResult(listComicsSold.stream()
                .filter(ComicBookName -> ComicBookName.getInputData().isAfter(localDate))
                .collect(Collectors.toList()));
    }

    public void popularNewComicsSaleForTheDay() {
        LocalDate localDate = LocalDate.now().minusDays(2);
        popularNewComicsSale(localDate);
    }

    public void popularNewComicsSaleForTheWeek() {
        LocalDate localDate = LocalDate.now().minusWeeks(1);
        popularNewComicsSale(localDate);
    }

    public void popularNewComicsSaleForTheMonth() {
        LocalDate localDate = LocalDate.now().minusMonths(1);
        popularNewComicsSale(localDate);
    }

    public void popularNewComicsSaleForTheYear() {
        LocalDate localDate = LocalDate.now().minusYears(1);
        popularNewComicsSale(localDate);
    }

    /**
     * Рейтинг авторов за период (день, неделя, месяц, год + метод поиска новинок за период + метод рейтинга)
     */
    public void popularArtistForTheDay() {
        LocalDate localDate = LocalDate.now().minusDays(2);
        searchListPopularArtists(localDate);
    }

    public void popularArtistForTheWeek() {
        LocalDate localDate = LocalDate.now().minusWeeks(1);
        searchListPopularArtists(localDate);
    }

    public void popularArtistForTheMonth() {
        LocalDate localDate = LocalDate.now().minusMonths(1);
        searchListPopularArtists(localDate);
    }

    public void popularArtistForTheYear() {
        LocalDate localDate = LocalDate.now().minusYears(1);
        searchListPopularArtists(localDate);
    }

    private void searchListPopularArtists(LocalDate localDate) {
        searchPopularArtists(listComic.stream()
                .filter(ComicBookName -> ComicBookName.getInputData().isAfter(localDate))
                .collect(Collectors.toList()));
    }

    private void searchPopularArtists(List<ComicBook> comicBook) {
        Map<String, Integer> list = new HashMap<>();
        int count = 1;
        List<String> value = new ArrayList<>();
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
                .forEach(p -> value.add(place.getAndIncrement() + " Автор - " + p.getKey() +
                        " Количество комиксов " + p.getValue() + '\n'));
        PrintHelper.setListResult(value);
    }

    /**
     * Рейтинг жанров за период (день, неделя, месяц, год + метод поиска новинок за период + метод рейтинга)
     */

    public void popularGenreForTheDay() {
        LocalDate localDate = LocalDate.now().minusDays(2);
        searchListPopularGenre(localDate);
    }

    public void popularGenreForTheWeek() {
        LocalDate localDate = LocalDate.now().minusWeeks(1);
        searchListPopularGenre(localDate);
    }

    public void popularGenreForTheMonth() {
        LocalDate localDate = LocalDate.now().minusMonths(1);
        searchListPopularGenre(localDate);
    }

    public void popularGenreForTheYear() {
        LocalDate localDate = LocalDate.now().minusYears(1);
        searchListPopularGenre(localDate);
    }

    private void searchListPopularGenre(LocalDate localDate) {
        searchPopularGenre(listComic.stream()
                .filter(ComicBookName -> ComicBookName.getInputData().isAfter(localDate))
                .collect(Collectors.toList()));
    }

    private void searchPopularGenre(List<ComicBook> comicBook) {
        Map<String, Integer> list = new HashMap<>();
        int count = 1;
        List<String> value = new ArrayList<>();
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
                .forEach(p -> value.add(place.getAndIncrement() + " Жанр комикса - " + p.getKey() +
                        " Количество комиксов " + p.getValue() + '\n'));
        PrintHelper.setListResult(value);
    }

    /**
     * Объявление скидок на комиксы (по названию, по ID, по серии, по жанру, черная пятница, внутрениие методы обработки
     */
    public void makeDiscountOnComicByName(String name, BigDecimal sale) {
        makeDiscountOnComic(searchObjectComicBook(name), sale);
    }

    public void makeDiscountOnComicByID(int id, BigDecimal sale) {
        makeDiscountOnComic(searchObjectComicBook(id), sale);
    }

    public void makeDiscountOncomicBookSeries(String series, BigDecimal sale) {
        for (ComicBook comicBook : listComic) {
            if (comicBook.getComicBookSeries().equals(series)) {
                makeDiscountOnListComics(comicBook, sale);
            }
        }
    }

    public void makeDiscountOncomicBookGenre(Enum<GenreComics> genre, BigDecimal sale) {
        for (ComicBook comicBook : listComic) {
            if (comicBook.getGenreComics().equals(genre)) {
                makeDiscountOnListComics(comicBook, sale);
            }
        }
    }

    public void makeDiscountOnALLComic(BigDecimal sale) {
        for (ComicBook comicBook : listComic) {
            makeDiscountOnListComics(comicBook, sale);
        }
    }

    private void makeDiscountOnComic(ComicBook comicBook, BigDecimal sale) {
        comicBook.setSalePrice(comicBook.getSalePrice().subtract(comicBook.getSalePrice().multiply(sale.multiply
                (BigDecimal.valueOf(0.01)))));
    }

    private void makeDiscountOnListComics(ComicBook comicBook, BigDecimal sale) {
        comicBook.setSalePrice(comicBook.getSalePrice().subtract(comicBook.getSalePrice().multiply(sale.multiply
                (BigDecimal.valueOf(0.01)))));
    }

    /**
     * Резервирование комиксов по покупателю.
     */

    public void comicBookReserving(ComicBook comicBook, Client client, int numberOfComicBook) throws NullPointerException {
        try {
            int valueWriteOffComicBook = writeOffComicBook(comicBook, numberOfComicBook);
            ComicBook cloneComicBook = (ComicBook) comicBook.clone();
            client.getReservingComics().add(cloneComicBook);
            cloneComicBook.setNumberOfComics(valueWriteOffComicBook);
        } catch (CloneNotSupportedException e) {
            System.out.println("Ошибка при клонировании объекта" + e);
        }
    }

    public int saleComicBookReserving(ComicBook comicBook, Client client, int numberOfComicBook) {
        int result = 0;
        int numberOfComics = 0;
        for (ComicBook comicBookClient : client.getReservingComics()) {
            if (comicBookClient.getIdComic() == comicBook.getIdComic()) {
                result = comicBook.getNumberOfComics() - numberOfComicBook;
                if (result > 0) {
                    comicBook.setNumberOfComics(result);
                    return result;
                } else {
                    client.getReservingComics().remove(comicBookClient);
                    return comicBook.getNumberOfComics();
                }
            }
        }
        return numberOfComics;
    }

    public void returnComicBookReserving(ComicBook comicBook, Client client, int numberOfComicBook) {
        int numberOfComic = saleComicBookReserving(comicBook, client, numberOfComicBook);
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

    /**
     * Работа с клиентом
     */

    public Client searchClientOnId(int id) {
        return clientList.stream()
                .filter(clientId -> clientId.getId() == id)
                .findFirst().get();
    }

    public Client searchClientOnName(String name) {
        return clientList.stream()
                .filter(clientName -> clientName.getFullName().equals(name))
                .findFirst().get();
    }

    public Client searchClientOnPhone(String phone) {
        return clientList.stream()
                .filter(clientPhone -> clientPhone.getPhone().equals(phone))
                .findFirst().get();
    }

    public void addClient(Client client) {
        clientList.add(client);
    }

    public void clientDelete(Client client) throws NoSuchElementException {
        clientList.remove(client);
    }
}


