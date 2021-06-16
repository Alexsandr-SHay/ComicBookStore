package company.repository;

import company.domain.Client;
import company.domain.ComicBook;
import company.services.PrintHelper;
import company.ui.Main;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * Класс для сохранения данных о магазине комиксов
 */

public class SaveComicBookAndClientRepository {

    public void saveAll(){
        saveListComics();
        saveListComicsSold();
        saveListClient();
    }

    public void saveListComics() {
        saveComicBook(Main.serviceWorkingWithComics.getListComic(), PrintHelper.SAVE_AND_READ_LIST_COMIC);
    }

    public void saveListComicsSold() {
        saveComicBook(Main.serviceWorkingWithComics.getListComicsSold(), PrintHelper.SAVE_AND_READ_LIST_COMIC_SOLD);
    }

    private void saveComicBook(List<ComicBook> arrays, String way) {
        ComicBook[] comicBooks = new ComicBook[arrays.size()];
        for (int i = 0; i < comicBooks.length; i++) {
            comicBooks[i] = arrays.get(i);
        }
        try (FileOutputStream fos = new FileOutputStream(way);  ObjectOutputStream oos = new ObjectOutputStream(fos)){
            oos.writeInt(comicBooks.length);
            for (ComicBook comicBook : comicBooks) {
                oos.writeObject(comicBook);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveListClient() {
        saveClient(Main.serviceWorkingWithComics.getClientList(), PrintHelper.SAVE_AND_READ_LIST_CLIENT);
    }

    private void saveClient(List<Client> arrays, String way) {
        Client[] clients = new Client[arrays.size()];
        for (int i = 0; i < clients.length; i++) {
            clients[i] = arrays.get(i);
        }
        try (FileOutputStream fos = new FileOutputStream(way); ObjectOutputStream oos = new ObjectOutputStream(fos)){
            oos.writeInt(clients.length);
            for (Client client : clients) {
                oos.writeObject(client);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
