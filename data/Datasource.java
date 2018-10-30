package data;

import sample.Controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Datasource {

    private Controller controller;

    //DATABASE LOCATION
    private static final String CONNECTION_NAME = ("jdbc:sqlite:books.db");

    //
    private static final String TABLE_NAME = "books";
    private static final String id = "id";
    private static final String lastName = "lastName";
    private static final String FIRSTNAME = "firstName";
    private static final String TITLE = "title";
    private static final String RELEASE_DATE = "releaseDate";


    private static Datasource instance = new Datasource();

    public static Datasource getInstance() {

        return instance;
    }

    private Connection connection;


    public final String searchTitlebyFullName = "SELECT * FROM " + TABLE_NAME;

    private final String insertData =
            "INSERT INTO " + TABLE_NAME + " ("+FIRSTNAME+", "+lastName + ", " + TITLE+ ", "+ RELEASE_DATE+") values " ;



    //QUERY BOOK
    public List<Book> getBookList(String s) throws SQLException {

        List<Book> books = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append(searchTitlebyFullName);
        sb.append(" WHERE " + lastName + " = " + "'" + s + "'");


        try (Statement stm = connection.createStatement();
             ResultSet rs = stm.executeQuery(sb.toString())) {

                while (rs.next()) {
                    Book book = new Book();
                    book.setId(rs.getString(1));
                    book.setLastName(rs.getString(3));
                    book.setFirstName(rs.getString(2));
                    book.setTitle(rs.getString(4));
                    book.setReleaseDate(rs.getString(5));
                    books.add(book);
                }
            }


        return books;
        }


    //Book availability
    public void checkBookAvailability(Book book) {
        System.out.println(book);

    }


    //ADDING BOOK
    public void addBook(String s1, String s2, String s3, String s4){

        StringBuilder sb = new StringBuilder();
        sb.append(insertData);
        sb.append("('"+s1 + "', '" + s2+"', '"+s3+"', '"+s4+"')");
        try{

            Statement stm = connection.createStatement();
            stm.executeUpdate(sb.toString());
        } catch(SQLException e){
            e.getMessage();
        }
    }


    //OPEN DATABASE CONNECTION
    public boolean open() {
        try {
            connection = DriverManager.getConnection(CONNECTION_NAME);

            return true;

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    //CLOSE DATABASE CONNECTION
    public boolean stop() {
        if (connection != null) {
            try {
                connection.close();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return false;

    }


    @Override
    public String toString() {
        return "";
    }
}
