package library;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Library {

    private ArrayList<Book> books;
    private ArrayList<Member> members;

    public Library(ArrayList<Book> books, ArrayList<Member> members) {
        this.books = books;
        this.members = members;
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added successfully.");
    }

    public void registerMember(Member member) {
        members.add(member);
        System.out.println("Member registered successfully.");
    }

    public Book findBook(String isbn) {
        return books.stream().filter(b -> b.getIsbn().equals(isbn)).findFirst().orElse(null);
    }

    public Member findMember(String id) {
        return members.stream().filter(m -> m.getMemberId().equals(id)).findFirst().orElse(null);
    }

    public void borrowBook(String memberId, String isbn) {
        Book book = findBook(isbn);
        Member member = findMember(memberId);

        if (book == null || member == null) {
            System.out.println("Invalid book/member.");
            return;
        }

        if (!book.isAvailable()) {
            System.out.println("Book not available.");
            return;
        }

        book.setAvailable(false);
        book.setDueDate(LocalDate.now().plusDays(14));
        member.addBook(isbn);

        System.out.println("Book borrowed successfully. Due: " + book.getDueDate());
    }

    public void returnBook(String memberId, String isbn) {
        Book book = findBook(isbn);
        Member member = findMember(memberId);

        if (book == null || member == null) return;

        if (book.getDueDate() != null && LocalDate.now().isAfter(book.getDueDate())) {
            long daysLate = ChronoUnit.DAYS.between(book.getDueDate(), LocalDate.now());
            double fine = daysLate * 10;
            member.addFine(fine);
            System.out.println("Overdue! Fine: ₹" + fine);
        }

        book.setAvailable(true);
        book.setDueDate(null);
        member.removeBook(isbn);

        System.out.println("Book returned successfully.");
    }

    public void searchBook(String keyword) {
        books.stream()
                .filter(b -> b.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                .forEach(b -> System.out.println(b.getTitle() + " | " + b.getAuthor()));
    }

    public ArrayList<Book> getBooks() { return books; }
    public ArrayList<Member> getMembers() { return members; }
}
