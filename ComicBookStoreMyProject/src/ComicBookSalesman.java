import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


public class ComicBookSalesman implements Cloneable {
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

    public void addComic() {                                         // Добавление комикса с помощью ввода с клавиатуры
        ComicBook comicBook = new ComicBook();
        comicBook.setComicBookName(PrintingData.enterComicBookName());                     // - Ввод Названия комикса
        ComicBook finalComicBook = comicBook;
        comicBook = listComic.stream()
                .filter(comicBookName -> comicBookName.getComicBookName().equals(finalComicBook.getComicBookName()))
                .findFirst().orElse(comicBook);
        // - Ввод количества комиксов
        comicBook.setNumberOfComics(comicBook.getNumberOfComics() + PrintingData.enterNumberOfComics());
        comicBook.setInputData(PrintingData.enterDateComics());
        if (comicBook.getFullNameAuthor() == null) {                                      // - Проверка добавления
            comicBook.setFullNameAuthor(PrintingData.enterFullNameAuthor());              // - Ввод ФИО автора
            comicBook.setComicBookPublisher(PrintingData.enterComicBookPublisher());      // - Ввод издательства
            comicBook.setNumberOfPages(PrintingData.enterNumberOfPages());                // - Ввод количества страниц
            comicBook.setGenreComics(PrintingData.enterGenreComics());                    // - Ввод жанра комиксов
            comicBook.setCostPrice(PrintingData.enterCostPrice());                        // - Ввод себестоимости
            comicBook.setSalePrice(PrintingData.enterSalePrice());                        // - Ввод стоимости комикса
            comicBook.setYearPublication(PrintingData.enterYearPublication());            // - Ввод года публикации
            comicBook.setComicBookSeries(PrintingData.enterComicBookSeries());            // - Ввод названия серии
            listComic.add(comicBook);
        }
    }

    public void addComic(ComicBook comicBook) {
        listComic.add(comicBook);
    }

    private ComicBook searchObjectComicBook(String name) {                         // Метод перебора коллекции по имени
        return listComic.stream()
                .filter(ComicBookName -> ComicBookName.getComicBookName().equals(name))
                .findFirst().get();
    }

    private ComicBook searchObjectComicBook(int id) {                              // Метод перебора коллекции по ID
        return listComic.stream()
                .filter(ComicBookId -> ComicBookId.getIdComic() == id)
                .findFirst().get();
    }

    /**
     * Удаление комиксов
     */

    private void deletingComic(ComicBook comicBook) {                       // Метод удаления комикса
        ArrayList<ComicBook> list = new ArrayList<ComicBook>();
        list.addAll(listComic);
        list.remove(comicBook);
        listComic = list;
    }

    public void deletingComicWithDataEntryByName() {                        // Метод удаления по имени комикса с вводом
        deletingComicByName(PrintingData.enterComicBookName());
    }

    public void deletingComicByName(String name) {                          // Метод удаления комикса по имени комикса
        try {
            ComicBook comicBook = searchObjectComicBook(name);
            deletingComic(comicBook);
        } catch (NoSuchElementException e) {
            System.out.println("Комикс с таким названием не найден");
        }
    }

    public void deletingComicWithDataEntryById() {                          // Метод удаления по имени комикса с вводом
        deletingComicById(PrintingData.enterComicId());
    }

    public void deletingComicById(int id) {                                 // Метод удаления по ID комикса
        try {
            ComicBook comicBook = searchObjectComicBook(id);
            deletingComic(comicBook);
        } catch (NoSuchElementException e) {
            System.out.println("Комикс с таким ID не найден");
        }
    }

    /**
     * Списывание комиксов
     */

    private int writeOffComicBook(ComicBook comicBook) {                     // Метод списания комикса
        int valueWriteOffComicBook = PrintingData.enterNumberOfComics();
        int result = comicBook.getNumberOfComics() - valueWriteOffComicBook;
        if (result > 0) {
            comicBook.setNumberOfComics(result);
        } else if (result == 0) {
            deletingComic(comicBook);
        } else {
            PrintingData.writeOffComicBook();
            writeOffComicBook(comicBook);
        }
        return valueWriteOffComicBook;
    }

    public void writeOffComicByNameWithDataEntry() {                         // Метод списания по имени комикса с вводом
        writeOffComicByName(PrintingData.enterComicBookName());
    }

    public void writeOffComicByName(String name) {                           // Метод списания по имени комикса
        try {
            ComicBook comicBook = searchObjectComicBook(name);
            writeOffComicBook(comicBook);
        } catch (NoSuchElementException e) {
            System.out.println("Комикс с таким названием не найден");
        }
    }

    public void writeOffComicByIdWithDataEntry() {                            // Метод списания по ID  комикса с вводом
        writeOffComicBookById(PrintingData.enterComicId());
    }

    public void writeOffComicBookById(int id) {                               // Метод списания по ID комикса
        try {
            ComicBook comicBook = searchObjectComicBook(id);
            writeOffComicBook(comicBook);
        } catch (NoSuchElementException e) {
            System.out.println("Комикс с таким ID не найден");
        }
    }

    /**
     * Редакция комиксов
     */

    private void editComic(ComicBook comicBook) {
        comicBook.setFullNameAuthor(PrintingData.enterFullNameAuthor());            // - Ввод ФИО автора
        comicBook.setComicBookPublisher(PrintingData.enterComicBookPublisher());    // - Ввод издательства
        comicBook.setNumberOfPages(PrintingData.enterNumberOfPages());              // - Ввод количества страниц
        comicBook.setGenreComics(PrintingData.enterGenreComics());                  // - Ввод жанра комиксов
        comicBook.setCostPrice(PrintingData.enterCostPrice());                      // - Ввод себестоимости
        comicBook.setSalePrice(PrintingData.enterSalePrice());                      // - Ввод стоимости комикса
        comicBook.setYearPublication(PrintingData.enterYearPublication());          // - Ввод года публикации
        comicBook.setComicBookSeries(PrintingData.enterComicBookSeries());          // - Ввод названия серии
        comicBook.setInputData(PrintingData.enterDateComics());                     // - Ввод даты
    }

    public void editComicByNameDataEntry() {                                  // Метод редактирования комикса по имени
        String name = PrintingData.enterComicBookName();
        try {
            ComicBook comicBook = searchObjectComicBook(name);
            String nameComicBook = comicBook.getComicBookName();
            comicBook.setComicBookName("NoN");
            String checkName = PrintingData.enterComicBookName();
            boolean value;
            value = listComic.stream()
                    .anyMatch(ComicBookName -> ComicBookName.getComicBookName().equals(checkName));
            if (!value) {
                comicBook.setComicBookName(checkName);                                      // - Ввод Названия комикса
                editComic(comicBook);
            } else {
                System.out.println("Комикс с таким названием существует");
                comicBook.setComicBookName(nameComicBook);
            }
        } catch (NoSuchElementException e) {
            System.out.println("Комикс с таким названием не найден");
        }
    }

    public void editComicByIdDataEntry() {                           // Метод редактирования комикса по ID
        int id = PrintingData.enterComicId();
        try {
            ComicBook comicBook = searchObjectComicBook(id);
            editComic(comicBook);
        } catch (NoSuchElementException e) {
            System.out.println("Комикс с таким названием не найден");
        }
    }

    /**
     * Методы поиска комиксов в магазине по различным параметрам
     */

    public void searchForComicByTitle(String name) {                           // Метод поиска комикса по названию
        List<ComicBook> comicBook = listComic.stream()
                .filter(ComicBookName -> ComicBookName.getComicBookName().contains(name))
                .collect(Collectors.toList());
        comicBook.forEach(System.out::println);
    }

    public void searchForComicByTitleDataEntry() {                             // Метод поиска комикса по имени с вводом
        searchForComicByTitle(PrintingData.enterComicBookName());
    }

    public void searchForComicByAuthor(String name) {                          // Метод поиска комикса по автору
        List<ComicBook> comicBook = listComic.stream()
                .filter(ComicBookName -> ComicBookName.getFullNameAuthor().contains(name))
                .collect(Collectors.toList());
        comicBook.forEach(System.out::println);
    }

    public void searchForComicByAuthorDataEntry() {                           // Метод поиска комикса по автору с вводом
        searchForComicByAuthor(PrintingData.enterFullNameAuthor());
    }

    public void searchForComicsByGenre(Enum<GenreComics> genreComics) {       // Метод поиска комикса по жанру
        List<ComicBook> comicBook = listComic.stream()
                .filter(ComicBookName -> ComicBookName.getGenreComics().equals(genreComics))
                .collect(Collectors.toList());
        comicBook.forEach(System.out::println);
    }

    public void searchForComicsByGenreDataEntry() {                          // Метод поиска комикса по жанру с вводом
        Enum<GenreComics> genreComics = PrintingData.enterGenreComics();
        searchForComicsByGenre(genreComics);
    }

    private void popularNewComics(LocalDate localDate) {                  //Метод поиска новинок относительно периода
        List<ComicBook> comicBook = listComic.stream()
                .filter(ComicBookName -> ComicBookName.getInputData().isAfter(localDate))
                .collect(Collectors.toList());
        comicBook.forEach(System.out::println);
    }

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

    /**
     * Продажа комиксов
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

    public void comicBookSalesByName(String name) {                            // Метод продажи комикса по имени
        try {
            ComicBook comicBook = searchObjectComicBook(name);
            comicBookSales(comicBook);
        } catch (NoSuchElementException e) {
            System.out.println("Комикс с таким названием не найден");
        }
    }

    public void comicBookSalesByNameDataEntry() {                            // Метод продажи комикса по имени с вводом
        comicBookSalesByName(PrintingData.enterComicBookName());
    }

    public void comicBookSalesById(int id) {                                  // Метод продажи комикса по ID
        try {
            ComicBook comicBook = searchObjectComicBook(id);
            comicBookSales(comicBook);
        } catch (NoSuchElementException e) {
            System.out.println("Комикс с таким названием не найден");
        }
    }

    public void comicBookSalesByIdDataEntry() {                               // Метод продажи комикса по ID с вводом
        comicBookSalesById(PrintingData.enterComicId());
    }

    /**
     * Скидки на комиксы
     */

    private void makeDiscountOnComic(ComicBook comicBook){                  // объявление скидки на комикс
        BigDecimal sale = PrintingData.enterSale();
        comicBook.setSalePrice(comicBook.getSalePrice().subtract(comicBook.getSalePrice().multiply(sale.multiply
                (BigDecimal.valueOf(0.01)))));
    }

    private void makeDiscountOnListComics(ComicBook comicBook, BigDecimal sale){         // объявление скидки на комикс
        comicBook.setSalePrice(comicBook.getSalePrice().subtract(comicBook.getSalePrice().multiply(sale.multiply
                (BigDecimal.valueOf(0.01)))));
    }

    public void makeDiscountOnComicByName(){                              // скидка на комикс поиск по имени
        makeDiscountOnComic(searchObjectComicBook(PrintingData.enterComicBookName()));
    }

    public void makeDiscountOnComicByID(){                                // скидка на комикс поиск по ID
        makeDiscountOnComic(searchObjectComicBook(PrintingData.enterComicId()));
    }

    public void makeDiscountOnALLComic(){                               // скидка на всё комиксы(чёрная пятница)
        BigDecimal sale = PrintingData.enterSale();
        for (ComicBook comicBook: listComic) {
            makeDiscountOnListComics(comicBook, sale);
        }
    }

    public void makeDiscountOnComicBookSeries() {                       // скидка на комиксы по серии
        String series = PrintingData.enterComicBookSeries();
        BigDecimal sale = PrintingData.enterSale();
        for (ComicBook comicBook : listComic) {
            if (comicBook.getComicBookSeries().equals(series)) {
                makeDiscountOnListComics(comicBook, sale);
            }
        }
    }

    public void makeDiscountOnComicBookGenre() {                       // скидка на комиксы по жанру
        Enum<GenreComics> genre = PrintingData.enterGenreComics();
        BigDecimal sale = PrintingData.enterSale();
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
        comicBookReservingById(PrintingData.enterComicId(), PrintingData.enterClientId());
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

    public void saleComicBookReserving(){                        //Продажа комикса из списка зарезервированных
    }

    public void returnComicBookReserving(){                      //Возврат из списка зарезервированных в список комиксов
    }

    /**
     * Работа с клиентом
     */

    private Client searchClient(int id) {                              // Метод перебора коллекции по ID
        return clientList.stream()
                .filter(clientId -> clientId.getId() == id)
                .findFirst().get();
    }
}
