/*package com.example.notisaver;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Objects;

public class ChatActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseHelper dbHelper;
    ArrayList<String> id, package_name, textOfNotification, user, post_time;
    ChatAdapter chatAdapter;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        String name = getIntent().getStringExtra("name");

        Objects.requireNonNull(getSupportActionBar()).setTitle(name);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.chat_recyclerview);

        dbHelper = new DatabaseHelper(ChatActivity.this);
        id = new ArrayList<>();
        package_name = new ArrayList<>();
        textOfNotification = new ArrayList<>();
        user = new ArrayList<>();
        post_time = new ArrayList<>();

        storeDataInArrays(name);

        chatAdapter = new ChatAdapter(ChatActivity.this, id, package_name, user, textOfNotification, post_time);
        recyclerView.setAdapter(chatAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ChatActivity.this));
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    void storeDataInArrays(String name) {
        Cursor cursor = dbHelper.readNotifications();

        String name1;
        if (cursor.getCount() > 0)
            while (cursor.moveToNext()) {
                name1 = cursor.getString(3);
                if (name1.equals(name)) {
                    id.add(0, cursor.getString(0));
                    package_name.add(0, cursor.getString(1));
                    user.add(0, name1);
                    textOfNotification.add(0, cursor.getString(4));
                    post_time.add(0, cursor.getString(5));
                }
            }
    }
}

 */

package com.example.notisaver;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;

import java.util.Objects;

public class ChatActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        String name = getIntent().getStringExtra("name");

        Objects.requireNonNull(getSupportActionBar()).setTitle(name);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}