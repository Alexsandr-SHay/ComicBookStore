import java.util.ArrayList;
import java.util.List;


public class ComicBookSalesman {
    public List<ComicBook> listComic;

    public ComicBookSalesman() {
        listComic = new ArrayList<>();
    }

    public void addComic() {                                         // Добавление комикса с помощью ввода с клавиатуры
        ComicBook comicBook = new ComicBook();
        comicBook.setComicBookName(PrintingData.enterComicBookName());                // - Ввод Названия комикса
        comicBook.setNumberOfComics(PrintingData.enterNumberOfComics());              // - Ввод количества комиксов
        int count = checkComicReplay(comicBook);
        if (count >= 0){                                                               // - Проверка добавления
            listComic.get(count).setNumberOfComics(listComic.get(count).getNumberOfComics() +
                    comicBook.getNumberOfComics());
        } else {
            comicBook.setFullNameAuthor(PrintingData.enterFullNameAuthor());              // - Ввод ФИО автора
            comicBook.setComicBookPublisher(PrintingData.enterComicBookPublisher());      // - Ввод издательства
            comicBook.setNumberOfPages(PrintingData.enterNumberOfPages());                // - Ввод количества страниц
            comicBook.setGenreComics(PrintingData.enterGenreComics());                    // - Ввод жанра комиксов
            comicBook.setCostPrice(PrintingData.enterCostPrice());                        // - Ввод себестоимости
            comicBook.setSalePrice(PrintingData.enterSalePrice());                        // - Ввод стоимости комикса
            comicBook.setYearPublication(PrintingData.enterYearPublication());            // - Ввод года публикации
            comicBook.setComicBookSeries(PrintingData.enterСomicBookSeries());            // - Ввод названия серии
            listComic.add(comicBook);
        }
    }

    private int checkComicReplay(ComicBook comicBook) {                            // - Проверка по названию или ID
        for (int i = 0; i < listComic.size(); i++) {
            if (listComic.get(i).getComicBookName().equalsIgnoreCase(comicBook.getComicBookName()) ||
                    listComic.get(i).getIdComic() == comicBook.getIdComic()) {
                return i;
            }
        }
        return -1;
    }

}
