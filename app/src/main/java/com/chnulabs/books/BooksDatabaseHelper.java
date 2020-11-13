package com.chnulabs.books;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BooksDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "authors";
    private static final int DB_VERSION = 1;

    public BooksDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlText = "CREATE TABLE Authors (\n" +
                "id                     INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "name                   TEXT(15) NOT NULL,\n" +
                "birthplace             TEXT(15),\n" +
                "litDirection           INTEGER,\n" +
                "uaLangFlg              BOOLEAN,\n" +
                "rusLangFlg             BOOLEAN\n" +
                ");";
        sqLiteDatabase.execSQL(sqlText);
        updateSchema(sqLiteDatabase, 0);
        populateDB(sqLiteDatabase);
    }

    private void populateDB(SQLiteDatabase db) {
        populateAuthors(db);
        populateBooks(db);
    }

    private void populateAuthors(SQLiteDatabase db) {
        for (Author author : Author.getAuthors()) {
            insertRowToAuthors(db, author);
        }
    }

    private void populateBooks(SQLiteDatabase db) {
        for (Book book : Book.getBooks()) {
            insertRowToBook(db, book);
        }
    }

    private void insertRowToBook(SQLiteDatabase db, Book book) {
        db.execSQL("INSERT INTO Books(bookName, author_id)\n" +
                "SELECT '" + book.getName() + "', id\n" +
                "FROM Authors\n" +
                "WHERE name = '" + book.getAuthor() + "'");
    }

    private void insertRowToAuthors(SQLiteDatabase db, Author author) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", author.getName());
        contentValues.put("birthplace", author.getBirthplace());
        contentValues.put("litDirection", author.getLitDirection());
        contentValues.put("uaLangFlg", author.isUaLangFlg());
        contentValues.put("rusLangFlg", author.isRusLangFlg());
        db.insert("Authors", null, contentValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldV, int newV) {
        updateSchema(sqLiteDatabase, oldV);
    }

    private void updateSchema(SQLiteDatabase db, int oldV) {
        if (oldV < 2) {
            db.execSQL("CREATE TABLE Books(\n" +
                    "id                     INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "bookName                   TEXT(15) NOT NULL,\n" +
                    "author_id              INTEGER REFERENCES Authors(id) ON DELETE RESTRICT\n" +
                    "                                                      ON UPDATE RESTRICT\n" +
                    ");");
            populateBooks(db);
        }
    }
}
