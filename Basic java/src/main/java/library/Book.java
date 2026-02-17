package library;

import java.time.LocalDate;

public class Book {
    private String isbn;
    private String title;
    private String author;
    private boolean available;
    private LocalDate dueDate;
    private String reservedBy;

    public Book(String isbn, String title, String author, boolean available, LocalDate dueDate, String reservedBy) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.available = available;
        this.dueDate = dueDate;
        this.reservedBy = reservedBy;
    }

    public String getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isAvailable() { return available; }
    public LocalDate getDueDate() { return dueDate; }
    public String getReservedBy() { return reservedBy; }

    public void setAvailable(boolean available) { this.available = available; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    public void setReservedBy(String reservedBy) { this.reservedBy = reservedBy; }

    @Override
    public String toString() {
        return isbn + "," + title + "," + author + "," + available + "," +
                (dueDate == null ? "null" : dueDate.toString()) + "," +
                (reservedBy == null ? "null" : reservedBy);
    }
}
