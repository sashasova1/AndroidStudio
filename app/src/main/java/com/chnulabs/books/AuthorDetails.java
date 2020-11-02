package com.chnulabs.books;

import java.util.ArrayList;
import java.util.Arrays;

public class AuthorDetails {
    private String authorName;
    private String birthplace;
    private int litDirection;
    private boolean rusLangFlg;
    private boolean uaLangFlg;

    public AuthorDetails(String authorName, String birthplace, int litDirection, boolean rusLangFlg, boolean uaLangFlg) {
        this.authorName = authorName;
        this.birthplace = birthplace;
        this.litDirection = litDirection;
        this.rusLangFlg = rusLangFlg;
        this.uaLangFlg = uaLangFlg;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public int getLitDirection() {
        return litDirection;
    }

    public boolean isRusLangFlg() {
        return rusLangFlg;
    }

    public boolean isUaLangFlg() {
        return uaLangFlg;
    }

    private final static ArrayList<AuthorDetails> authors = new ArrayList<>(
            Arrays.asList(
                    new AuthorDetails("Тарас Шевченко", "с. Моринці", 1, false, true),
                    new AuthorDetails("Всеволод Нестайко", "м. Бердичів", 1, true, true),
                    new AuthorDetails("Габриэль Гарсиа Маркес", "м. Бердичів", 2, false, false),
                    new AuthorDetails("Брет Истон Эллис", "м. Бердичів", 1, false, false),
                    new AuthorDetails("Марк Твен", "м. Бердичів", 0, false, false)
            )
    );

    public static AuthorDetails getAuthor(String authorName) {
        for (AuthorDetails ad : authors) {
            if (ad.getAuthorName().equals(authorName)) {
                return ad;
            }
        }
        return null;
    }
}
