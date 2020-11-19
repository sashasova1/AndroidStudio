package com.chnulabs.books;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

public class AuthorActivity extends AppCompatActivity {

    public static final String ID = "id";

    private Author author;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author2);

        Intent intent = getIntent();
        int id = intent.getIntExtra(AuthorActivity.ID, 0);
        author = Author.httpGetAuthor(id);
       /* SQLiteOpenHelper sqLiteOpenHelper = new BooksDatabaseHelper(this);
        try {
            SQLiteDatabase db = sqLiteOpenHelper.getReadableDatabase();
            Cursor cursor = db.query("Authors",
                    new String[]{"name", "birthplace", "litDirection", "uaLangFlg", "rusLangFlg", "id"},
                    "id=?", new String[]{Integer.toString(id)},
                    null, null, null);
            if (cursor.moveToFirst()) {
                author = new Author(
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
        }*/
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
                case 1:
                    ((RadioButton) findViewById(R.id.author_direction_baroque)).setChecked(true);
                    break;
                case 2:
                    ((RadioButton) findViewById(R.id.author_direction_realism)).setChecked(true);
                    break;
                case 3:
                    ((RadioButton) findViewById(R.id.author_direction_sentimentalism)).setChecked(true);
                    break;
            }

            ((CheckBox) findViewById(R.id.ua_flg)).setChecked(
                    author.isUaLangFlg()
            );

            ((CheckBox) findViewById(R.id.ru_flg)).setChecked(
                    author.isRusLangFlg()
            );

        }
    }

    public void onOkBtnClick(View view) {
        SQLiteOpenHelper sqLiteHelper = new BooksDatabaseHelper(this);

        ContentValues contentValues = new ContentValues();
        contentValues.put("name",
                ((TextView) findViewById(R.id.authorEdit)).getText().toString()
        );
        contentValues.put("birthplace",
                ((TextView) findViewById(R.id.birthplaceEdit)).getText().toString()
        );
        contentValues.put("litDirection",
                ((RadioButton) findViewById(R.id.author_direction_baroque)).isChecked() ? 1 : ((RadioButton) findViewById(R.id.author_direction_realism)).isChecked() ? 2 : 3
        );
        contentValues.put("uaLangFlg",
                ((CheckBox) findViewById(R.id.ua_flg)).isChecked()
        );
        contentValues.put("rusLangFlg",
                ((CheckBox) findViewById(R.id.ru_flg)).isChecked()
        );

        Intent intent = getIntent();
        int id = intent.getIntExtra(ID, 0);
        try {
            SQLiteDatabase db = sqLiteHelper.getReadableDatabase();
            db.update("Authors",
                    contentValues,
                    "id=?",
                    new String[]{Integer.toString(id)}
            );
            db.close();
            NavUtils.navigateUpFromSameTask(this);
        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this,
                    "Database unavailable",
                    Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    public void onBtnBookListClick(View view) {
        Intent newIntent = new Intent(this, BookListActivity.class);
        newIntent.putExtra(BookListActivity.ID, author.getId());
        startActivity(newIntent);
    }

    public void onDelete(View view) {
        SQLiteOpenHelper sqLiteHelper = new BooksDatabaseHelper(this);

        Intent intent = getIntent();
        int id = intent.getIntExtra(ID, 0);
        try {
            SQLiteDatabase db = sqLiteHelper.getReadableDatabase();
            db.delete("Authors",
                    "id=?",
                    new String[]{Integer.toString(id)}
            );
            db.close();
            NavUtils.navigateUpFromSameTask(this);
        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this,
                    "Database unavailable",
                    Toast.LENGTH_SHORT);
            toast.show();
        }

    }
}