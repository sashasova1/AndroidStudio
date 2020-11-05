package com.chnulabs.books;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddAuthorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_author);
    }

    public void onAuthorAddClick(View view) {
        EditText author = (EditText) findViewById(R.id.addAuthorName);
        EditText birthplace = (EditText) findViewById(R.id.addBirthplace);
        AuthorDetails.addAuthor(
                new AuthorDetails(author.getText().toString(),
                        birthplace.getText().toString(),
                        0, false, true
                )
        );
        NavUtils.navigateUpFromSameTask(this);
    }
}