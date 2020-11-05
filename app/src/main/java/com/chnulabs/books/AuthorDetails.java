package com.chnulabs.books;

import java.util.ArrayList;
import java.util.Arrays;

public class AuthorDetails {
    private String name;
    private String birthplace;
    private int litDirection;
    private boolean rusLangFlg;
    private boolean uaLangFlg;

    public AuthorDetails(String name, String birthplace, int litDirection, boolean uaLangFlg, boolean rusLangFlg) {
        this.name = name;
        this.birthplace = birthplace;
        this.litDirection = litDirection;
        this.uaLangFlg = uaLangFlg;
        this.rusLangFlg = rusLangFlg;
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

    private static ArrayList<AuthorDetails> authors = new ArrayList<>(
            Arrays.asList(
                    new AuthorDetails("Тарас Шевченко", "с. Моринці", 1, false, true),
                    new AuthorDetails("Всеволод Нестайко", "м. Бердичів", 1, true, true),
                    new AuthorDetails("Габриэль Гарсиа Маркес", "м. Аракатака", 2, false, false),
                    new AuthorDetails("Брет Истон Эллис", "м. Лос-Анджелес", 1, false, false),
                    new AuthorDetails("Марк Твен", "штат Флорида", 0, false, false)
            )
    );

    public static void addAuthor(AuthorDetails author){
        authors.add(author);
    }

    public static AuthorDetails getAuthor(String authorName) {
        for (AuthorDetails ad : authors) {
            if (ad.getName().equals(authorName)) {
                return ad;
            }
        }
        return null;
    }

    public static ArrayList<AuthorDetails> getAuthors() {
        return authors;
    }

    @Override
    public String toString() {
        return name;
    }
}
