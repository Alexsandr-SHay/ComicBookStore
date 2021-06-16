package company.ui;

import company.domain.Client;
import company.services.PrintHelper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;


public class MenuClient {

    @FXML
    private TextField fieldNameClient;

    @FXML
    private TextField fieldNumberPhone;

    @FXML
    private Label labelResult;

    @FXML
    private Button buttonReturnMenu;

    @FXML
    private Button buttonSearchClientName;

    @FXML
    private Button buttonSearchClientPhone;

    @FXML
    private Button buttonAddClient;

    @FXML
    private Button buttonDeleteClient;

    @FXML
    private Button buttonEditClient;

    @FXML
    private Button buttonShowClient;

    @FXML
    private TextArea textArea;

    @FXML
    private Button buttonClear;

    /**
     * Класс предстовляющий собой инфтерфейс пользователя для работы с клиентами.
     */

    @FXML
    void initialize() {

        buttonSearchClientName.setOnAction(actionEvent -> textArea.appendText(PrintHelper.
                editingTheOutputToTheScreenClient(checkDataClientName())));

        buttonSearchClientPhone.setOnAction(actionEvent -> textArea.appendText(PrintHelper.
                editingTheOutputToTheScreenClient(checkDataClientPhone())));

        buttonDeleteClient.setOnAction(actionEvent -> deleteClient());

        buttonShowClient.setOnAction(actionEvent -> showClient());

        buttonAddClient.setOnAction(actionEvent -> addClient());

        buttonEditClient.setOnAction(actionEvent -> editClient());

        buttonClear.setOnAction(actionEvent -> clearField());

        buttonReturnMenu.setOnAction(actionEvent -> returnMenu());
    }

    private void addClient() {
        String textClient = fieldNameClient.getText();
        String textPhone = fieldNumberPhone.getText();
        try {
            labelResult.setText(PrintHelper.CLIENT_EXISTS + Main.comicBookSalesman.searchClientOnName(textClient).getIdClient());
        } catch (NoSuchElementException ignored) {
            if (!textClient.isEmpty() && !textPhone.isEmpty()) {
                Main.comicBookSalesman.addClient(new Client(textClient, new ArrayList<>(),
                        PrintHelper.checkNumberPhone(textPhone)));
                labelResult.setText(PrintHelper.CLIENT_ADDED);
            } else {
                labelResult.setText(PrintHelper.FILL_NAME_CLIENTS_AND_PHONE);
            }
        }
    }

    private void editClient() {
        if (!fieldNameClient.getText().isEmpty()) {
            Client client = checkDataClientName();
            labelResult.setText(PrintHelper.CLIENT_REPLACE_INPUT_DATA);
            buttonEditClient.setOnAction(actionEvent -> {
                String textClient = fieldNameClient.getText();
                String textPhone = fieldNumberPhone.getText();
                if (!textClient.isEmpty() && !textPhone.isEmpty()) {
                    client.setFullName(textClient);
                    client.setPhone(PrintHelper.checkNumberPhone(textPhone));
                    labelResult.setText(PrintHelper.CLIENT_EDITED);
                } else {
                    labelResult.setText(PrintHelper.FILL_NAME_CLIENTS_AND_PHONE);
                }
            });
        } else {
            labelResult.setText(PrintHelper.FILL_NAME_CLIENTS_AND_PHONE);
        }
    }

    private void showClient() {
        textArea.appendText(PrintHelper.editingTheOutputToTheScreen(Main.comicBookSalesman.getClientList()));
    }

    private void deleteClient() {
        try {
            Main.comicBookSalesman.clientDelete(checkDataClientName());
        } catch (NoSuchElementException e) {
            labelResult.setText(PrintHelper.CLIENT_NOT_FOUND);
        }
    }

    private void clearField() {
        textArea.clear();
        labelResult.setText(PrintHelper.SELECT_ONE_OF_THE_MENU_ITEMS);
    }

    private void returnMenu() {
        buttonReturnMenu.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(PrintHelper.ADDRESS_RETURN_MENU));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene((parent)));
        stage.show();
    }

    private Client checkDataClientName() {
        String text = fieldNameClient.getText();
        try {
            int value = Integer.parseInt(text);
            return Main.comicBookSalesman.searchClientOnId(value);
        } catch (NoSuchElementException e) {
            labelResult.setText(PrintHelper.CLIENT_NOT_FOUND);
        } catch (NumberFormatException e) {
            labelResult.setText(PrintHelper.CLIENT_NOT_FOUND);
            try {
                return Main.comicBookSalesman.searchClientOnName(text);
            } catch (NoSuchElementException ignored) {
                labelResult.setText(PrintHelper.CLIENT_NOT_FOUND);
            }
        }
        return new Client();
    }

    private Client checkDataClientPhone() {
        String text = fieldNumberPhone.getText();
        try {
            return Main.comicBookSalesman.searchClientOnPhone(text);
        } catch (NoSuchElementException | NumberFormatException e) {
            labelResult.setText(PrintHelper.CLIENT_NOT_FOUND);
            return new Client();
        }
    }
}