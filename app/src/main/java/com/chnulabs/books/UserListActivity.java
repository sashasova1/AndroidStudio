package com.chnulabs.books;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class UserListActivity extends AppCompatActivity {

    public static final String USER_ID = "userId";

    class User {
        public int id;
        public String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        ListView listView = findViewById(R.id.userList);
        ArrayAdapter<User> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                getListData()
        );
        listView.setAdapter(adapter);

        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                User user = (User) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(UserListActivity.this,
                        TodoListActivity.class);
                intent.putExtra(UserListActivity.USER_ID, user.id);
                startActivity(intent);
            }
        };
        listView.setOnItemClickListener(listener);
    }

    private ArrayList<User> getListData() {
        ArrayList<User> arr = new ArrayList<>();
        String res = new HttpDataGetter(
                "http://jsonplaceholder.typicode.com/users"
        ).getData();

        try {
            JSONArray jsonArray = new JSONArray(res);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                arr.add(
                        new User(
                                obj.getInt("id"),
                                obj.getString("name")
                        )
                );
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arr;
    }
}