import java.util.List;

public class Client {
    private static int idClient = 100_000;
    private final int id;
    private String fullName;
    private List<ComicBook> reservingComics;

    public Client() {
        idClient++;
        id = idClient;
    }

    public Client(String fullName, List<ComicBook> reservingComics) {
        this.fullName = fullName;
        this.reservingComics = reservingComics;
        idClient++;
        id = idClient;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<ComicBook> getReservingComics() {
        return reservingComics;
    }

    public void setReservingComics(List<ComicBook> reservingComics) {
        this.reservingComics = reservingComics;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", ФИО клиента='" + fullName + '\'' +
                ", Список зарезервированных комиксов=" + reservingComics +
                '}' + '\n';
    }
}
