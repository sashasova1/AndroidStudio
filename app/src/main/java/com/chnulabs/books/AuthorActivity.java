package com.chnulabs.books;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class AuthorActivity extends AppCompatActivity {
    public static final String BOOK_AUTHOR = "author";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author2);

        Intent intent = getIntent();
        int id = intent.getIntExtra(BOOK_AUTHOR, 0);
        AuthorDetails author = null;
        SQLiteOpenHelper sqLiteOpenHelper = new BooksDatabaseHelper(this);
        try {
            SQLiteDatabase db = sqLiteOpenHelper.getReadableDatabase();
            Cursor cursor = db.query("Authors",
                    new String[]{"name", "birthplace", "litDirection", "uaLangFlg", "rusLangFlg", "id"},
                    "id=?", new String[] {Integer.toString(id)},
                    null, null, null);
            if (cursor.moveToFirst()) {
                author = new AuthorDetails(
                        cursor.getInt(5),
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        (cursor.getInt(3) > 0),
                        (cursor.getInt(4) > 0)
                );
            } else {
                Toast toast = Toast.makeText(this,
                        "Can't find an author with ID " + id,
                        Toast.LENGTH_SHORT);
                toast.show();
            }
            cursor.close();
            db.close();
        } catch (SQLException e) {
            Toast toast = Toast.makeText(this,
                    "Exception! DB unavailable.",
                    Toast.LENGTH_SHORT);
            toast.show();
        }
        if (author != null) {
            TextView txtImgAuthor = findViewById(R.id.authorNameImageTxt);
            txtImgAuthor.setText(author.getName());
            TextView txtImgBirthplace = findViewById(R.id.birthplaceImageTxt);
            txtImgBirthplace.setText(author.getBirthplace());

            EditText txtAuthorName = findViewById(R.id.authorEdit);
            txtAuthorName.setText(author.getName());
            EditText txtBirthplace = findViewById(R.id.birthplaceEdit);
            txtBirthplace.setText(author.getBirthplace());

            switch (author.getLitDirection()) {
                case 0:
                    ((RadioButton) findViewById(R.id.author_direction_baroque)).setChecked(true);
                    break;
                case 1:
                    ((RadioButton) findViewById(R.id.author_direction_realism)).setChecked(true);
                    break;
                case 2:
                    ((RadioButton) findViewById(R.id.author_direction_sentimentalism)).setChecked(true);
                    break;
                default:
                    break;
            }
        }
    }

    public void onOkBtnClick(View view) {
        String outString = "\tАвтор " + ((TextView) findViewById(R.id.authorEdit)).getText() + "\n";
        outString += "народився у " + ((TextView) findViewById(R.id.birthplaceEdit)).getText() + ".\n\tЛітературний напрям - ";
        if (((RadioButton) findViewById(R.id.author_direction_baroque)).isChecked()) {
            outString += "бароко\n";
        } else if (((RadioButton) findViewById(R.id.author_direction_realism)).isChecked()) {
            outString += "реалізм\n";
        } else if (((RadioButton) findViewById(R.id.author_direction_sentimentalism)).isChecked()) {
            outString += "сентименталізм\n";
        }

        outString += "\tМови творів: \n";
        if (((CheckBox) findViewById(R.id.ua_flg)).isChecked()) {
            outString += "українська \n";
        }
        if (((CheckBox) findViewById(R.id.ru_flg)).isChecked()) {
            outString += "російська \n";
        }
        Toast.makeText(this, outString, Toast.LENGTH_LONG).show();
    }

    public void onBtnBookListClick(View view) {
        Intent localIntent = getIntent();
        String author = localIntent.getStringExtra(BOOK_AUTHOR);

        Intent newIntent = new Intent(this, BookListActivity.class);
        newIntent.putExtra(BookListActivity.BOOK_AUTHOR, author);
        startActivity(newIntent);
    }

}