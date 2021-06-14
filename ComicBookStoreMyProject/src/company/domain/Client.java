package company.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Класс содержащий информацию о клиентах:
 * ФИО клиента(fullName), телефон(phone), информацию о зарезервированных комиксах у клиента(reservingComics),
 * id для индификации клиента(id).
 * Основные методы реализованные в данном классе: Сеттеры и гетеры и переопределённый метод toString()
 */
public class Client implements Serializable {
    private static int idClient = 100_000;
    private final int id;
    private String fullName;
    private List<ComicBook> reservingComics;
    private String phone;

    public Client() {
        idClient++;
        id = idClient;
    }

    public Client(String fullName, List<ComicBook> reservingComics, String phone) {
        this.fullName = fullName;
        this.reservingComics = reservingComics;
        this.phone = phone;
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

    public int getId() {
        return id;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void warningSetClientId(int id){
        idClient = id;
    }

    @Override
    public String toString() {
        return "Клиент " +
                "id " + id + ';' +
                ", ФИО клиента " + fullName + ';' +
                ", Номер телефона " + phone + ';' + '\n' +
                ", Список зарезервированных комиксов " + reservingComics + '\n' + '\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id == client.id && Objects.equals(fullName, client.fullName) && Objects.equals(reservingComics,
                client.reservingComics) && Objects.equals(phone, client.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, reservingComics, phone);
    }
}
