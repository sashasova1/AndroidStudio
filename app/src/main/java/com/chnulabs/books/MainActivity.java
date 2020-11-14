package com.chnulabs.books;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBtnShowAuthorsClick(View view) {
        Intent intent = new Intent(this, AuthorsListActivity.class);
        startActivity(intent);
    }

    public void onGetJsonBtnClick(View view) {
        Intent intent = new Intent(this, UserListActivity.class);
        startActivity(intent);
    }
}