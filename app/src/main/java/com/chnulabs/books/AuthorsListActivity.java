package com.chnulabs.books;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.core.view.MenuItemCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
                String author = ((AuthorDetails) adapterView.getItemAtPosition(i))
                        .toString();
                Intent intent = new Intent(AuthorsListActivity.this, AuthorActivity.class);
                intent.putExtra(AuthorActivity.BOOK_AUTHOR, author);
                startActivity(intent);
            }
        };

        ListView listView = (ListView) findViewById(R.id.authors_list);
        listView.setOnItemClickListener(listener);
    }

    @Override
    protected void onStart() {
        super.onStart();
        ListView listView = (ListView) findViewById(R.id.authors_list);
        ArrayAdapter<AuthorDetails> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                AuthorDetails.getAuthors()
        );
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.authors_menu, menu);

        String text = "";
        for (AuthorDetails author : AuthorDetails.getAuthors()) {
            text += author.getName() + "\n";
        }
        MenuItem menuItem = menu.findItem(R.id.action_share);
        ShareActionProvider shareActionProvider =
                (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        shareActionProvider.setShareIntent(intent);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_author:
                startActivity(
                        new Intent(this, AddAuthorActivity.class)
                );
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onAuthorAddClick(View view) {
        startActivity(new Intent(this, AddAuthorActivity.class));
    }
}