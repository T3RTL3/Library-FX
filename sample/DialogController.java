package sample;


import data.Datasource;
import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;


import java.sql.SQLException;


public class DialogController {


    @FXML
    private Label firstName;
    @FXML
    private Label lastName;
    @FXML
    private Label title;
    @FXML
    private Label releaseDate;

    @FXML
    private TextField date;
    @FXML
    private TextField titleTF;
    @FXML
    private TextField firstNM;
    @FXML
    private TextField lastNM;


    private static DialogController instance = new DialogController();

    public static DialogController getInstance() {
        return instance;
    }

    public void insertBooks() {
        try {
            String Sdate = date.getText().trim().toUpperCase();
            String STitleTF = titleTF.getText().trim().toUpperCase();
            String SFirstNM = firstNM.getText().trim().toUpperCase();
            String SLastNM = lastNM.getText().trim().toUpperCase();
            Datasource.getInstance().addBook(SFirstNM,SLastNM,STitleTF, Sdate);
        } catch (Exception e){
            e.printStackTrace();
        }
    }


}
