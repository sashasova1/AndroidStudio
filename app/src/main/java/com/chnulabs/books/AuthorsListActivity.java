package com.chnulabs.books;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.core.view.MenuItemCompat;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class AuthorsListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authors_list);

        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Author author = (Author) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(AuthorsListActivity.this, AuthorActivity.class);
                intent.putExtra(AuthorActivity.ID, author.getId());

                startActivity(intent);
            }
        };

        ListView listView = findViewById(R.id.authors_list);
        listView.setOnItemClickListener(listener);
    }

    @Override
    protected void onStart() {
        super.onStart();

        getDataFromDB();

        ListView listView = findViewById(R.id.authors_list);
        ArrayAdapter<Author> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                //AuthorDetails.getAuthors()
                getDataFromDB()
        );
        listView.setAdapter(adapter);
    }

    private ArrayList<Author> getDataFromDB() {
        ArrayList<Author> authors = new ArrayList<>();
        SQLiteOpenHelper sqLiteOpenHelper = new BooksDatabaseHelper(this);
        try {
            SQLiteDatabase db = sqLiteOpenHelper.getReadableDatabase();
            Cursor cursor = db.query("Authors",
                    new String[]{"name", "birthplace", "litDirection", "uaLangFlg", "rusLangFlg", "id"},
                    null, null, null,
                    null, "name");
            while (cursor.moveToNext()) {
                authors.add(
                        new Author(
                                cursor.getInt(5),
                                cursor.getString(0),
                                cursor.getString(1),
                                cursor.getInt(2),
                                (cursor.getInt(3) > 0),
                                (cursor.getInt(4) > 0)
                        )
                );
            }
            cursor.close();
            db.close();

        } catch (SQLException e) {
            Toast toast = Toast.makeText(this,
                    "Exception! DB unavailable.",
                    Toast.LENGTH_SHORT);
            toast.show();
        }
        return authors;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.authors_menu, menu);

        String text = "";
        for (Author author : Author.getAuthors()) {
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