package sample;


import data.Book;
import data.Datasource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class Controller {


    public TextField getTextField() {
        return this.textField;
    }

    @FXML
    private Button addBook;
    @FXML
    public Button searchButton;

    @FXML
    private TextField textField;

    @FXML
    private ListView<Book> listView;

    @FXML
    private BorderPane mainBorderPane;


    final ContextMenu contextMenu = new ContextMenu();

    MenuItem availability = new MenuItem("Availability");


    @FXML
    public void searchBook(KeyEvent key) {
        if (key.getCode() == KeyCode.ENTER) {
            setData();
        }
    }

    @FXML
    public void searchBookButton() {
        setData();
    }

    private void setData() {
        //Querying the data from database, getting text from @TextField printing  @ListView
        String s = textField.getText();
        String[] ss = s.split(" ");
        try {
            ObservableList<Book> data =
                    FXCollections.observableArrayList(Datasource.getInstance().getBookList(ss[0].toUpperCase()));

            listView.setItems(data);
            textField.clear();

            //Creating availability menu event
            final ContextMenu contextMenu = new ContextMenu();
            MenuItem availability = new MenuItem("Availability");
            contextMenu.getItems().addAll(availability);

            availability.setOnAction((event -> {
                System.out.println("event check");
            }));

            listView.setContextMenu(contextMenu);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void addBook() {
        System.out.println("event");
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("pane.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }


        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        Optional<ButtonType> result = dialog.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {

            DialogController controller = fxmlLoader.getController();
            controller.insertBooks();
        }

    }

}
