package com.chnulabs.books;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
                    new Author("Тарас Шевченко", "с. Моринці", 2, false, true),
                    new Author("Всеволод Нестайко", "м. Бердичів", 2, true, true),
                    new Author("Габриэль Гарсиа Маркес", "м. Аракатака", 3, false, false),
                    new Author("Брет Истон Эллис", "м. Лос-Анджелес", 2, false, false),
                    new Author("Марк Твен", "штат Флорида", 1, false, false)
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

    public static ArrayList<Author> httpGetAuthors() {
        ArrayList<Author> arr = new ArrayList<>();
        String res = new HttpDataGetter(
                "http://10.0.2.2/api/?action=get_authors_list"
        ).getData();
        try {
            JSONArray jsonArray = new JSONArray(res);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                arr.add(
                        new Author(
                                obj.getInt("id"),
                                obj.getString("name"),
                                obj.getString("birthplace"),
                                obj.getInt("litDirection"),
                                (obj.getInt("uaLangFlg") != 0),
                                (obj.getInt("rusLangFlg") != 0)
                        )
                );
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arr;
    }

    public static Author httpGetAuthor(int id) {
        Author author = null;
        String res = new HttpDataGetter(
                "http://10.0.2.2/api/?action=get_author&author_id=" + id
        ).getData();
        try {

            JSONObject obj = new JSONObject(res);
            author = new Author(
                    obj.getInt("id"),
                    obj.getString("name"),
                    obj.getString("birthplace"),
                    obj.getInt("litDirection"),
                    (obj.getInt("uaLangFlg") != 0),
                    (obj.getInt("rusLangFlg") != 0)
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return author;
    }
}
