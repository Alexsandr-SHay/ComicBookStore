import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ComicBookSalesman comicBookSalesman = new ComicBookSalesman();
        comicBookSalesman.addComic();
        comicBookSalesman.addComic();
        System.out.println(comicBookSalesman.listComic);
    }
}
