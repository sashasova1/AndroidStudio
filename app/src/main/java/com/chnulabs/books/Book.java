package com.chnulabs.books;

import java.util.ArrayList;
import java.util.Arrays;

public class Book {
    private String name;
    private String author;
    private String genre;

    public Book(String name, String author, String genre) {
        this.name = name;
        this.author = author;
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    private final static ArrayList<Book> books = new ArrayList<>(
            Arrays.asList(
                    new Book("Тореадори з Васюківки", "Всеволод Нестайко", "Роман"),
                    new Book("Кобзар", "Тарас Шевченко", "Поезія"),
                    new Book("Сон", "Тарас Шевченко", "Поема"),
                    new Book("Сто лет одиночества", "Габриэль Гарсиа Маркес", "Роман"),
                    new Book("Американский психопат", "Брет Истон Эллис", "Роман"),
                    new Book("Приключения Тома Сойера", "Марк Твен", "Повість")
            )
    );

    public static ArrayList<Book> getBooks(String author) {
        ArrayList<Book> bkList = new ArrayList<>();
        for (Book b : books) {
            if (b.getAuthor().equals(author)) {
                bkList.add(b);
            }
        }
        return bkList;
    }
}
