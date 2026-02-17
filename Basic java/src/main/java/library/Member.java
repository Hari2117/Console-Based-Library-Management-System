package library;

import java.util.ArrayList;

public class Member {
    private String memberId;
    private String name;
    private ArrayList<String> borrowedBooks;
    private double fine;

    public Member(String memberId, String name, ArrayList<String> borrowedBooks, double fine) {
        this.memberId = memberId;
        this.name = name;
        this.borrowedBooks = borrowedBooks;
        this.fine = fine;
    }

    public String getMemberId() { return memberId; }
    public String getName() { return name; }
    public ArrayList<String> getBorrowedBooks() { return borrowedBooks; }
    public double getFine() { return fine; }

    public void addBook(String isbn) { borrowedBooks.add(isbn); }
    public void removeBook(String isbn) { borrowedBooks.remove(isbn); }
    public void addFine(double amount) { fine += amount; }

    @Override
    public String toString() {
        return memberId + "," + name + "," +
                String.join("|", borrowedBooks) + "," + fine;
    }
}
