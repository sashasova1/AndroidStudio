package com.chnulabs.books;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class BookActivity extends AppCompatActivity {
    public static final String BOOK_AUTHOR = "author";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        Intent intent = getIntent();
        String authorName = intent.getStringExtra(BOOK_AUTHOR);
        AuthorDetails author = AuthorDetails.getAuthor(authorName);

        TextView txtImgAuthor = (TextView) findViewById(R.id.authorNameImageTxt);
        txtImgAuthor.setText(author.getAuthorName());
        TextView txtImgBirthplace = (TextView) findViewById(R.id.birthplaceImageTxt);
        txtImgBirthplace.setText(author.getBirthplace());

        EditText txtAuthorName = (EditText) findViewById(R.id.authorEdit);
        txtAuthorName.setText(author.getAuthorName());
        EditText txtBirthplace = (EditText) findViewById(R.id.birthplaceEdit);
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

    public void onOkBtnClick(View view) {
        String outString = "Автор " + ((TextView) findViewById(R.id.authorEdit)).getText() + "\n";
        outString += "народився у " + ((TextView) findViewById(R.id.birthplaceEdit)).getText() + ".\n Літературний напрям - ";
        if (((RadioButton) findViewById(R.id.author_direction_baroque)).isChecked()) {
            outString += "бароко\n";
        } else if (((RadioButton) findViewById(R.id.author_direction_realism)).isChecked()) {
            outString += "реалізм\n";
        } else if (((RadioButton) findViewById(R.id.author_direction_sentimentalism)).isChecked()) {
            outString += "сентименталізм\n";
        }

        outString += "Мови творів: \n";
        if (((CheckBox) findViewById(R.id.ua_flg)).isChecked()) {
            outString += "українська \n";
        }
        if (((CheckBox) findViewById(R.id.ru_flg)).isChecked()) {
            outString += "російська \n";
        }
        Toast.makeText(this, outString, Toast.LENGTH_LONG).show();
    }


}