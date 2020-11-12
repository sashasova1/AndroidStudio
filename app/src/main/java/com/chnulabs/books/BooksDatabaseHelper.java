package com.chnulabs.books;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BooksDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "authors";
    private static final int DB_VERSION = 2;

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
                "uaLangFlg             BOOLEAN,\n" +
                "rusLangFlg              BOOLEAN\n" +
                ");";
        sqLiteDatabase.execSQL(sqlText);

        populateDB(sqLiteDatabase);
    }

    private void populateDB(SQLiteDatabase db) {
        for (AuthorDetails author : AuthorDetails.getAuthors()) {
            insertRow(db, author);
        }
    }

    private void insertRow(SQLiteDatabase db, AuthorDetails author) {
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

    }
}
