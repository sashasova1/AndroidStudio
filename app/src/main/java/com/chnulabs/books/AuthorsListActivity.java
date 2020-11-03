package com.chnulabs.books;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AuthorsListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authors_list);
        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String author = ((AuthorDetails) adapterView.getItemAtPosition(i)).toString();
                Intent intent = new Intent(AuthorsListActivity.this, AuthorActivity.class);
                intent.putExtra(AuthorActivity.BOOK_AUTHOR, author);
                startActivity(intent);
            }
        };

        ListView listView = (ListView) findViewById(R.id.authors_list);
        listView.setOnItemClickListener(listener);
        ArrayAdapter<AuthorDetails> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                AuthorDetails.getAuthors()
        );
        listView.setAdapter(adapter);
    }
}