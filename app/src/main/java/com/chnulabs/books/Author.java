package com.chnulabs.books;

import java.util.ArrayList;
import java.util.Arrays;

public class Author {
    private int id;
    private String name;
    private String birthplace;
    private int litDirection;
    private boolean rusLangFlg;
    private boolean uaLangFlg;

    public Author(int id, String name, String birthplace, int litDirection, boolean rusLangFlg, boolean uaLangFlg) {
        this(name, birthplace, litDirection, uaLangFlg, rusLangFlg);
        this.id = id;
    }

    public Author(String name, String birthplace, int litDirection, boolean uaLangFlg, boolean rusLangFlg) {
        this.name = name;
        this.birthplace = birthplace;
        this.litDirection = litDirection;
        this.uaLangFlg = uaLangFlg;
        this.rusLangFlg = rusLangFlg;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public int getLitDirection() {
        return litDirection;
    }

    public boolean isUaLangFlg() {
        return uaLangFlg;
    }

    public boolean isRusLangFlg() {
        return rusLangFlg;
    }

    private static ArrayList<Author> authors = new ArrayList<>(
            Arrays.asList(
                    new Author("Тарас Шевченко", "с. Моринці", 1, false, true),
                    new Author("Всеволод Нестайко", "м. Бердичів", 1, true, true),
                    new Author("Габриэль Гарсиа Маркес", "м. Аракатака", 2, false, false),
                    new Author("Брет Истон Эллис", "м. Лос-Анджелес", 1, false, false),
                    new Author("Марк Твен", "штат Флорида", 0, false, false)
            )
    );

    public static void addAuthor(Author author) {
        authors.add(author);
    }

    public static Author getAuthor(String authorName) {
        for (Author a : authors) {
            if (a.getName().equals(authorName)) {
                return a;
            }
        }
        return null;
    }

    public static ArrayList<Author> getAuthors() {
        return authors;
    }

    @Override
    public String toString() {
        return name;
    }

}
