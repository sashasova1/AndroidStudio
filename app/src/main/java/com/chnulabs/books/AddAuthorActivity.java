package com.chnulabs.books;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddAuthorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_author);
    }

    public void onAuthorAddClick(View view) {
        EditText author = findViewById(R.id.addAuthorName);
        EditText birthplace = findViewById(R.id.addBirthplace);

        SQLiteOpenHelper sqLiteHelper = new BooksDatabaseHelper(this);
        try {
            SQLiteDatabase db = sqLiteHelper.getReadableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", author.getText().toString());
            contentValues.put("birthplace", birthplace.getText().toString());
            contentValues.put("litDirection", 0);
            contentValues.put("uaLangFlg", 0);
            contentValues.put("rusLangFlg", 0);

            db.insert("Authors", null, contentValues);
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