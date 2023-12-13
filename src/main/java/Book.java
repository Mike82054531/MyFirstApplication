// Book.java
public class Book {
    private int bookCoverResource;
    private String bookTitle;

    public Book(int bookCoverResource, String bookTitle) {
        this.bookCoverResource = bookCoverResource;
        this.bookTitle = bookTitle;
    }

    public int getBookCoverResource() {
        return bookCoverResource;
    }

    public String getBookTitle() {
        return bookTitle;
    }
}
