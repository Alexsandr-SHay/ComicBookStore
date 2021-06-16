package company.ui;

import company.repository.ReadComicAndClientRepository;
import company.repository.SaveComicBookAndClientRepository;
import company.services.ComicBookSalesman;
import company.services.PrintHelper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    public static ComicBookSalesman comicBookSalesman = new ComicBookSalesman();

    @Override
    public void start(Stage primaryStage) throws Exception {
        ReadComicAndClientRepository readComicAndClientRepository = new ReadComicAndClientRepository();
        readComicAndClientRepository.readAll();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(PrintHelper.ADDRESS_START_MENU)));
        primaryStage.setScene(new Scene(root, 700, 400));
        primaryStage.show();
    }

    @Override
    public void stop() {
        SaveComicBookAndClientRepository saveComicBookAndClientRepository = new SaveComicBookAndClientRepository();
        saveComicBookAndClientRepository.saveAll();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
