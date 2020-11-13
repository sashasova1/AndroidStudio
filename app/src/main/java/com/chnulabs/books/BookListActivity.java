package com.chnulabs.books;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BookListActivity extends AppCompatActivity {

    public static final String ID = "id";

    private Cursor cursor;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booklist);

        Intent intent = getIntent();
        int id = intent.getIntExtra(ID, 0);

        ListView listView = findViewById(R.id.booksList);
        SimpleCursorAdapter adapter = getDataFromDB(id);
        if (adapter != null) {
            listView.setAdapter(adapter);
        }
    }

    private SimpleCursorAdapter getDataFromDB(int id) {
        SimpleCursorAdapter listAdapter = null;

        SQLiteOpenHelper sqLiteHelper = new BooksDatabaseHelper(this);
        try {
            db = sqLiteHelper.getReadableDatabase();
            cursor = db.rawQuery("SELECT b.id _id, bookName, author_id\n" +
                    "FROM Books b INNER JOIN authors a on b.author_id = a.id\n" +
                    "WHERE a.id = ? ", new String[]{Integer.toString(id)});
            listAdapter = new SimpleCursorAdapter(this,
                    android.R.layout.simple_list_item_1,
                    cursor,
                    new String[]{"bookName"},
                    new int[]{android.R.id.text1},
                    0);
        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this,
                    "Database unavailable",
                    Toast.LENGTH_SHORT);
            toast.show();
        }
        return listAdapter;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cursor.close();
        db.close();
    }

    public void onBtnSendClick(View view) {
        TextView textView = findViewById(R.id.text);

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, textView.getText().toString());
        intent.putExtra(Intent.EXTRA_SUBJECT, "Список літератури");
        startActivity(intent);
    }
}