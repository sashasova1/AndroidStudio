package com.chnulabs.books;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private int seconds = 0;
    private Boolean isRunning = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("timer");
        }
        runTimer();
    }

    public void onBtnClick(View view) {
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        String author = (String) spinner.getSelectedItem();

        Intent intent = new Intent(this, BookListActivity.class);
        intent.putExtra(BookListActivity.BOOK_AUTHOR, author);
        startActivity(intent);
    }

    public void onBookBtnClick(View view) {
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        String author = (String) spinner.getSelectedItem();

        Intent intent = new Intent(this, AuthorActivity.class);
        intent.putExtra(AuthorActivity.BOOK_AUTHOR, author);
        startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("timer", seconds);
    }

    @Override
    protected void onStart() {
        super.onStart();
        isRunning = true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        isRunning = false;
    }

    private void runTimer() {
        final TextView timeView = findViewById(R.id.timer);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                String time = String.format(Locale.getDefault(),
                        "%d:%02d:%02d", hours, minutes, secs);
                timeView.setText(time);
                if (isRunning) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }
}