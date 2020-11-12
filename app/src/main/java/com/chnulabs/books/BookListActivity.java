package com.chnulabs.books;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BookListActivity extends AppCompatActivity {
    public static final String BOOK_AUTHOR = "author";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booklist);

        Intent intent = getIntent();
        String author = intent.getStringExtra(BOOK_AUTHOR);

        ListView listView = findViewById(R.id.booksList);
        ArrayAdapter<Book> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                Book.getBooks(author)
        );
        listView.setAdapter(adapter);
    }

    public void onBtnSendClick(View view) {
        TextView textView = findViewById(R.id.text);
        Intent intent = new Intent(Intent.ACTION_SEND);

        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TITLE, "Список літератури");
        intent.putExtra(Intent.EXTRA_TEXT, textView.getText());
        startActivity(intent);
    }
}