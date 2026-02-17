package library;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static final String BOOK_PATH = "data/books.txt";
    private static final String MEMBER_PATH = "data/members.txt";

    public static void main(String[] args) {

        ArrayList<Book> books = FileHandler.loadBooks(BOOK_PATH);
        ArrayList<Member> members = FileHandler.loadMembers(MEMBER_PATH);

        Library library = new Library(books, members);
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1.Add Book\n2.Register Member\n3.Borrow\n4.Return\n5.Search\n6.Exit");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("ISBN: ");
                    String isbn = sc.nextLine();
                    System.out.print("Title: ");
                    String title = sc.nextLine();
                    System.out.print("Author: ");
                    String author = sc.nextLine();
                    library.addBook(new Book(isbn, title, author, true, null, null));
                    break;

                case 2:
                    System.out.print("Member ID: ");
                    String id = sc.nextLine();
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    library.registerMember(new Member(id, name, new ArrayList<>(), 0));
                    break;

                case 3:
                    System.out.print("Member ID: ");
                    String mid = sc.nextLine();
                    System.out.print("ISBN: ");
                    String bisbn = sc.nextLine();
                    library.borrowBook(mid, bisbn);
                    break;

                case 4:
                    System.out.print("Member ID: ");
                    String mid2 = sc.nextLine();
                    System.out.print("ISBN: ");
                    String bisbn2 = sc.nextLine();
                    library.returnBook(mid2, bisbn2);
                    break;

                case 5:
                    System.out.print("Enter title keyword: ");
                    library.searchBook(sc.nextLine());
                    break;

                case 6:
                    FileHandler.saveBooks(BOOK_PATH, library.getBooks());
                    FileHandler.saveMembers(MEMBER_PATH, library.getMembers());
                    System.out.println("Data saved. Exiting...");
                    System.exit(0);
            }
        }
    }
}
