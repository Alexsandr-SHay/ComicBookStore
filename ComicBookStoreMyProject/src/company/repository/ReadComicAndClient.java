package company.repository;


import company.domain.Client;
import company.domain.ComicBook;
import company.services.PrintHelper;
import company.ui.Main;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

/**
 * Класс для чтения данных при загрузке программы
 */

public class ReadComicAndClient {

    public void readAll(){
        readListComics();
        readListComicsSold();
        readListClient();
    }

    public void readListComics() {
       readComicBook(Main.comicBookSalesman.getListComic(), PrintHelper.SAVE_AND_READ_LIST_COMIC);
    }

    public void readListComicsSold() {
        readComicBook(Main.comicBookSalesman.getListComicsSold(), PrintHelper.SAVE_AND_READ_LIST_COMIC_SOLD);
    }

    private void readComicBook(List<ComicBook> arrays, String way) {
        try (FileInputStream fis = new FileInputStream(way);  ObjectInputStream ois = new ObjectInputStream(fis) ){
            int count = ois.readInt();
            for (int i = 0; i < count; i++) {
                arrays.add((ComicBook) ois.readObject());
            }

            defineTheComicBookId(arrays.get(count-1).getIdComic());

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void readListClient() {
       readClient(Main.comicBookSalesman.getClientList(), PrintHelper.SAVE_AND_READ_LIST_CLIENT);
    }

    private void readClient(List<Client> arrays, String way) {
        try (FileInputStream fis = new FileInputStream(way); ObjectInputStream ois = new ObjectInputStream(fis)){
            int count = ois.readInt();
            for (int i = 0; i < count; i++) {
                arrays.add((Client) ois.readObject());
            }
            defineTheClientId(arrays.get(count-1).getId());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void defineTheComicBookId(int idComic) {
        ComicBook comicBook = new ComicBook();
        comicBook.warningSetId(idComic);
    }

    private void defineTheClientId(int idClient){
        Client client = new Client();
        client.warningSetClientId(idClient);
    }
}
