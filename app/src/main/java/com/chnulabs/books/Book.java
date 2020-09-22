package com.chnulabs.books;

import java.util.ArrayList;
import java.util.Arrays;

public class Book {
    private String name;
    private String author;

    public Book(String name, String author) {
        this.name = name;
        this.author = author;
    }
    public String getName() {
        return name;
    }
    public String getAuthor() {
        return author;
    }

    private final static ArrayList<Book> books = new ArrayList<>(
            Arrays.asList(
                    new Book("Тореадори з Васюківки", "Всеволод Нестайко"),
                    new Book("Кобзар", "Тарас Шевченко"),
                    new Book("Сто лет одиночества", "Габриэль Гарсиа Маркес"),
                    new Book("Американский психопат", "Брет Истон Эллис"),
                    new Book("Приключения Тома Сойера", "Марк Твен")
            )
    );

    public static ArrayList<Book> getBooks(String author){
        return new ArrayList<>(books);
    }
}
