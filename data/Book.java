package data;



public class Book {

    private String id;
    private String firstName;
    private String lastName;
    private String title;
    private String releaseDate;

    public Book(){

    }

    public Book(String id, String firstName,String lastName, String title, String releaseDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.releaseDate = releaseDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName(){ return  lastName;}

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName){ this.lastName = lastName;}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return id + " " +lastName+" "+ firstName+ " " + title + " " + releaseDate;

    }
}
