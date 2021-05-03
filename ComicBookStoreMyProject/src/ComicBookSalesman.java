import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


public class ComicBookSalesman {
    private List<ComicBook> listComic;

    public ComicBookSalesman() {
        listComic = new ArrayList<>();
    }

    public ComicBookSalesman(List<ComicBook> comicBookList) {
        listComic = comicBookList;
    }

    public List<ComicBook> getListComic() {
        return listComic;
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

    public void deletingComicByName(String name) {                        // Метод удаления по имени комикса
        try {
            ComicBook comicBook = searchObjectComicBook(name);
            listComic.remove(comicBook);
        } catch (NoSuchElementException e) {
            System.out.println("Комикс с таким названием не найден");
        }
    }

    public void deletingComicById(int id) {                               // Метод удаления по ID комикса
        try {
            ComicBook comicBook = searchObjectComicBook(id);
            listComic.remove(comicBook);
        } catch (NoSuchElementException e) {
            System.out.println("Комикс с таким ID не найден");
        }
    }

    public void writeOffComicBookByName(String name) {                   // Метод списания по имени комикса
        try {
            ComicBook comicBook = searchObjectComicBook(name);
            int result = comicBook.getNumberOfComics() - PrintingData.enterNumberOfComics();
            if (result > 0 ){
                comicBook.setNumberOfComics(result);
            } else {
                PrintingData.writeOffComicBook();
            }
        } catch (NoSuchElementException e) {
            System.out.println("Комикс с таким названием не найден");
        }
    }

    public void writeOffComicByNameWithDataEntry() {            // Метод списания по имени комикса с вводом с клавиатуры
        writeOffComicBookByName(PrintingData.enterComicBookName());
    }

    public void writeOffComicBookById(int id) {                          // Метод списания по ID комикса
        try {
            ComicBook comicBook = searchObjectComicBook(id);
            int result = comicBook.getNumberOfComics() - PrintingData.enterNumberOfComics();
            if (result > 0 ){
                comicBook.setNumberOfComics(result);
            } else {
                PrintingData.writeOffComicBook();
            }
        } catch (NoSuchElementException e) {
            System.out.println("Комикс с таким названием не найден");
        }
    }

    public void writeOffComicByIdWithDataEntry() {                // Метод списания по ID  комикса с вводом с клавиатуры
        writeOffComicBookById(PrintingData.enterComicId());
    }

    public void editComicByNameDataEntry() {                      // Метод редактирования комикса по имени
        String name = PrintingData.enterComicBookName();
        try {
            ComicBook comicBook = searchObjectComicBook(name);
            String checkName = PrintingData.enterComicBookName();
            boolean value;
            value = listComic.stream()
                    .anyMatch(ComicBookName -> ComicBookName.getComicBookName().equals(checkName));
            if (!value) {
                comicBook.setComicBookName(checkName);                                      // - Ввод Названия комикса
                comicBook.setFullNameAuthor(PrintingData.enterFullNameAuthor());            // - Ввод ФИО автора
                comicBook.setComicBookPublisher(PrintingData.enterComicBookPublisher());    // - Ввод издательства
                comicBook.setNumberOfPages(PrintingData.enterNumberOfPages());              // - Ввод количества страниц
                comicBook.setGenreComics(PrintingData.enterGenreComics());                  // - Ввод жанра комиксов
                comicBook.setCostPrice(PrintingData.enterCostPrice());                      // - Ввод себестоимости
                comicBook.setSalePrice(PrintingData.enterSalePrice());                      // - Ввод стоимости комикса
                comicBook.setYearPublication(PrintingData.enterYearPublication());          // - Ввод года публикации
                comicBook.setComicBookSeries(PrintingData.enterComicBookSeries());          // - Ввод названия серии
            } else {
                System.out.println("Комикс с таким названием существует");
            }
        } catch (NoSuchElementException e) {
            System.out.println("Комикс с таким названием не найден");
        }
    }

    public void editComicByIdDataEntry() {                           // Метод редактирования комикса по ID
        int id = PrintingData.enterComicId();
        try {
            ComicBook comicBook = searchObjectComicBook(id);
            comicBook.setComicBookName(PrintingData.enterComicBookName());              // - Ввод Названия комикса
            comicBook.setFullNameAuthor(PrintingData.enterFullNameAuthor());            // - Ввод ФИО автора
            comicBook.setComicBookPublisher(PrintingData.enterComicBookPublisher());    // - Ввод издательства
            comicBook.setNumberOfPages(PrintingData.enterNumberOfPages());              // - Ввод количества страниц
            comicBook.setGenreComics(PrintingData.enterGenreComics());                  // - Ввод жанра комиксов
            comicBook.setCostPrice(PrintingData.enterCostPrice());                      // - Ввод себестоимости
            comicBook.setSalePrice(PrintingData.enterSalePrice());                      // - Ввод стоимости комикса
            comicBook.setYearPublication(PrintingData.enterYearPublication());          // - Ввод года публикации
            comicBook.setComicBookSeries(PrintingData.enterComicBookSeries());          // - Ввод названия серии
        } catch (NoSuchElementException e) {
            System.out.println("Комикс с таким названием не найден");
        }
    }

    private ComicBook searchObjectComicBook(String name) {                         // Метод перебора коллекции по имени
        return listComic.stream()
                .filter(ComicBookName -> ComicBookName.getComicBookName().equals(name))
                .findFirst().get();
    }
    private ComicBook searchObjectComicBook(int id) {                              // Метод перебора коллекции по ID
        return  listComic.stream()
                .filter(ComicBookId -> ComicBookId.getIdComic() == id)
                .findFirst().get();
    }

    public void searchForComicByTitle(String name) {                               // Метод поиска комикса по названию
        List<ComicBook> comicBook = listComic.stream()
                .filter(ComicBookName -> ComicBookName.getComicBookName().contains(name))
                .collect(Collectors.toList());
        comicBook.forEach(System.out::println);
    }

    public void searchForComicByTitleDataEntry() {       // Метод поиска комикса  по имени комикса с вводом с клавиатуры
        searchForComicByTitle(PrintingData.enterComicBookName());
    }

    public void searchForComicByAuthor(String name) {                              // Метод поиска комикса по автору
        List<ComicBook> comicBook = listComic.stream()
                .filter(ComicBookName -> ComicBookName.getFullNameAuthor().contains(name))
                .collect(Collectors.toList());
        comicBook.forEach(System.out::println);
    }

    public void searchForComicByAuthorDataEntry() {              // Метод поиска комикса по автору с вводом с клавиатуры
        searchForComicByAuthor(PrintingData.enterFullNameAuthor());
    }

    public void searchForComicsByGenre(Enum<GenreComics> genreComics) {           // Метод поиска комикса по жанру
        List<ComicBook> comicBook = listComic.stream()
                .filter(ComicBookName -> ComicBookName.getGenreComics().equals(genreComics))
                .collect(Collectors.toList());
        comicBook.forEach(System.out::println);
    }

    public void searchForComicsByGenreDataEntry() {              // Метод поиска комикса по жанру с вводом с клавиатуры
        Enum<GenreComics> genreComics = PrintingData.enterGenreComics();
        searchForComicsByGenre(genreComics);
    }
}
