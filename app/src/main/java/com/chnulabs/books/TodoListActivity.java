package com.chnulabs.books;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TodoListActivity extends AppCompatActivity {

    public static final String CASE_ID = "caseId";

    class Case {
        public int id;
        public String title;
        public boolean completed;

        public Case(int id, String name, boolean completed) {
            this.id = id;
            this.title = name;
            this.completed = completed;
        }

        @Override
        public String toString() {
            return title;
        }
    }

    public void getListFunc() {
        ListView listView = findViewById(R.id.todoList);
        ArrayAdapter<Case> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                getListData()
        );
        listView.setAdapter(adapter);

        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Case aCase = (Case) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(TodoListActivity.this,
                        JsonGetterActivity.class);
                intent.putExtra(TodoListActivity.CASE_ID, aCase.id);
                startActivity(intent);
            }
        };
        listView.setOnItemClickListener(listener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);
        CheckBox checkCompleted = findViewById(R.id.checkCompleted);
        getListFunc();

        checkCompleted.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                getListFunc();
            }
        });
    }

    private ArrayList<Case> getListData() {
        Intent intent = getIntent();
        int userId = intent.getIntExtra(UserListActivity.USER_ID, 0);
        CheckBox checkCompleted = findViewById(R.id.checkCompleted);

        ArrayList<Case> arr = new ArrayList<>();
        String res = new HttpDataGetter(
                "http://jsonplaceholder.typicode.com/users/"
                        + userId + "/todos"
        ).getData();

        try {
            JSONArray jsonArray = new JSONArray(res);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                boolean isChecked = obj.getBoolean("completed");
                if (!checkCompleted.isChecked()) {
                    arr.add(
                            new Case(
                                    obj.getInt("id"),
                                    obj.getString("title"),
                                    obj.getBoolean("completed")
                            )
                    );
                } else if (isChecked && checkCompleted.isChecked()) {
                    arr.add(
                            new Case(
                                    obj.getInt("id"),
                                    obj.getString("title"),
                                    obj.getBoolean("completed")
                            )
                    );
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arr;
    }
}