package library;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class FileHandler {

    public static ArrayList<Book> loadBooks(String path) {
        ArrayList<Book> books = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                LocalDate dueDate = data[4].equals("null") ? null : LocalDate.parse(data[4]);
                String reservedBy = data[5].equals("null") ? null : data[5];
                books.add(new Book(data[0], data[1], data[2],
                        Boolean.parseBoolean(data[3]), dueDate, reservedBy));
            }
        } catch (IOException e) {
            System.out.println("Books file not found. Creating new file.");
        }
        return books;
    }

    public static void saveBooks(String path, ArrayList<Book> books) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path))) {
            for (Book book : books) {
                pw.println(book.toString());
            }
        } catch (IOException e) {
            System.out.println("Error saving books.");
        }
    }

    public static ArrayList<Member> loadMembers(String path) {
        ArrayList<Member> members = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                ArrayList<String> borrowed = new ArrayList<>();
                if (!data[2].isEmpty())
                    for (String s : data[2].split("\\|")) borrowed.add(s);
                members.add(new Member(data[0], data[1], borrowed,
                        Double.parseDouble(data[3])));
            }
        } catch (IOException e) {
            System.out.println("Members file not found. Creating new file.");
        }
        return members;
    }

    public static void saveMembers(String path, ArrayList<Member> members) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path))) {
            for (Member m : members) {
                pw.println(m.toString());
            }
        } catch (IOException e) {
            System.out.println("Error saving members.");
        }
    }
}
