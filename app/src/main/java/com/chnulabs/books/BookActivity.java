package com.chnulabs.books;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

public class BookActivity extends AppCompatActivity {
    private float textSize;

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
        textSize = textView.getTextSize();
        if (savedInstanceState != null){
            textSize = savedInstanceState.getFloat("textSize");
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);

        }
    }

    public void onBtnSendClick(View view){
        TextView textView = findViewById(R.id.text);
        Intent intent = new Intent(Intent.ACTION_SEND);

        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TITLE, "Список літератури");
        intent.putExtra(Intent.EXTRA_TEXT, textView.getText());
        startActivity(intent);
    }
    public void onBtnPlusClick(View view){
        textSize *= 1.1f;
        TextView textView = findViewById(R.id.text);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putFloat("textSize", textSize);
    }
}