package com.chnulabs.books;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class BookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        Intent intent = getIntent();
        String author = intent.getStringExtra("author");

        StringBuilder txtBooks = new StringBuilder();
        for (Book s: Book.getBooks(author)){
            txtBooks.append(s.getName()).append("\t - \t").append(s.getAuthor()).append("\n");
        }
        TextView textView = findViewById(R.id.text);
        textView.setText(txtBooks.toString());
    }

    public void onBtnSendClick(View view){
        TextView textView = findViewById(R.id.text);
        Intent intent = new Intent(Intent.ACTION_SEND);

        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TITLE, "Список літератури");
        intent.putExtra(Intent.EXTRA_TEXT, textView.getText());
        startActivity(intent);
    }
}