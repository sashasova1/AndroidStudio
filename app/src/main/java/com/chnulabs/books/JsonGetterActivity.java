package com.chnulabs.books;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonGetterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_getter);

        Intent intent = getIntent();
        int caseId = intent.getIntExtra(TodoListActivity.CASE_ID, 1);
        String res = new HttpDataGetter(
                "http://jsonplaceholder.typicode.com/todos/"
                        + caseId
        ).getData();

        try {
            JSONObject obj = new JSONObject(res);

            EditText txtId = findViewById(R.id.txtId);
            txtId.setText(obj.getString("id"));
            EditText txtTheme = findViewById(R.id.txtTheme);
            txtTheme.setText(obj.getString("title"));
            CheckBox checkCompleted = findViewById(R.id.isCompleted);
            checkCompleted.setChecked(obj.getBoolean("completed"));


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}