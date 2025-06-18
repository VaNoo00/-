package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class history extends AppCompatActivity {
    // объявление переменных
    Button backBtn;
    ListView historyMainList;
    TextView userName;
    // метож определения переменных
    public void init(){
        backBtn = findViewById(R.id.backBtn);
        historyMainList = findViewById(R.id.ListMain);
        userName = findViewById(R.id.userName);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_history);
        // вызов метода
        init();
        // получаем значения с других страниц
        Intent intent = getIntent();
        userName.setText(intent.getStringExtra("userName"));
        ArrayList<String> copyList = intent.getStringArrayListExtra("historyList");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, copyList);
        historyMainList.setAdapter(adapter);
        // кнопка вернуться назад
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}