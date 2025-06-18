package com.example.myapplication;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Arrays;

public class main_screen extends AppCompatActivity {
    // класс для сохранения данных в списке
    public static class Load extends ViewModel {
        public ArrayList<String> items = new ArrayList<>();
    }
    // Определение является ли число числом Цукермана
    public boolean Cukerman(int n){
        String s = String.valueOf(Math.abs(n));
        int cnt = 1;
        for(int i = 0; i < s.length(); i++){
            cnt *= Integer.parseInt(String.valueOf(s.charAt(i)));
        }
        if(cnt != 0 && n % cnt == 0){
            return true;
        }
        else {
            return false;
        }

    }
    // Определение является ли число числом Нивена
    public boolean Niven(int n) {
        String s = String.valueOf(Math.abs(n));
        int cnt = 0;
        for (int i = 0; i < s.length(); i++) {
            cnt += Integer.parseInt(String.valueOf(s.charAt(i)));
        }
        if (cnt != 0 && n % cnt == 0) {
            return true;
        } else {
            return false;
        }

    }
    // Определение можно ли через число придти к константе Капрекара 6174
    public boolean ConstKaprekar(int n){
        String s = String.valueOf(n);
        int steps = 0;
        if (s.length() == 4){
            if(n > 1000){
                if(s.charAt(0) != s.charAt(1) || s.charAt(1) != s.charAt(2) || s.charAt(2) != s.charAt(3)){
                    while (n != 6174 && steps <= 7){
                        String num = String.format("%04d", n);
                        char[] nArray = num.toCharArray();

                        Arrays.sort(nArray);
                        String nMin = new String(nArray);
                        String nMax = new StringBuilder(nMin).reverse().toString();

                        int numMin = Integer.parseInt(nMin);
                        int numMax = Integer.parseInt(nMax);

                        n = numMax - numMin;
                        steps++;
                    }
                }
                else{
                    return false;
                }
            }
            else{
                return false;
            }
        }
        else {
            return false;
        }
        if (steps <= 7 && n == 6174){
            return true;
        }
        else {
            return false;
        }
    }
    // Определение является ли число числом Капрекара
    public boolean FindNumKaprekar(int number){
        if (number == 1){
            return true;
        }
        if (number < 1){
            return false;
        }
        long kvadrat = (long)number*number;
        String kvadStr = String.valueOf(kvadrat);
        for(int i = 1; i < kvadStr.length(); i++){
            String leftStr = kvadStr.substring(0, i);
            String rightStr = kvadStr.substring(i);
            long left = Long.parseLong(leftStr);
            long right = Long.parseLong(rightStr);
            if(right != 0 && left + right == number){
                return true;
            }
        }
        return false;
    }
    // объвление переменных
    Load viewModel;
    TextInputLayout startLayout, endLayout;
    TextInputEditText startEdit, endEdit;
    ListView ListHistory;
    boolean startbool = false;
    boolean endbool = false;
    public ArrayList<String> items;
    public ArrayAdapter<String> adapter;
    public int flag;
    Button historyBtn, btnCukerman, btnNiven, btnConstKaprekar, btnFindkaprekar, btnPoisk, btnCleaning;
    // присваивание значений переменным
    public void init(){
        startLayout = findViewById(R.id.textStart);
        endLayout = findViewById(R.id.textEnd);
        startEdit = findViewById(R.id.textStartEdit);
        endEdit = findViewById(R.id.textEndEdit);
        historyBtn = findViewById(R.id.historyBtn);
        btnCukerman = findViewById(R.id.numCukerman);
        btnNiven = findViewById(R.id.numNiven);
        btnConstKaprekar = findViewById(R.id.numConstKaprekar);
        btnFindkaprekar = findViewById(R.id.numFindNumKaprekar);
        btnPoisk = findViewById(R.id.poisk);
        ListHistory = findViewById(R.id.ListHistory);
        btnCleaning = findViewById(R.id.clear);
    }
    // метод определяет пустые поля или нет, если пустые выводит замечание
    public boolean onClick1(){
        if(startEdit.getText().toString().trim().isEmpty() || endEdit.getText().toString().trim().isEmpty()){
            if(startEdit.getText().toString().trim().isEmpty()) startLayout.setError("Обязательное поле!");
            if (endEdit.getText().toString().trim().isEmpty()) endLayout.setError("Обязательное поле!");
            return false;
        }
        else{
            startLayout.setError(null);
            endLayout.setError(null);
            return true;
        }
    }
    // переменная для передачи имени в history
    String userName = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_screen);

        // передаем имя на другую страницу
        Intent intent = getIntent();
        userName = intent.getStringExtra("userName");

        viewModel = new ViewModelProvider(this).get(Load.class);

        // Затем создаём адаптер
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, viewModel.items);

        // Теперь можно вызывать init()
        init();

        // Устанавливаем адаптер только после инициализации
        ListHistory.setAdapter(adapter);

        // флажок для вызова методов
        flag = 0;
        btnPoisk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // проверка на пустоту полей
                if (onClick1()) {
                    // проверка значени диапазона
                    if (Integer.parseInt(startEdit.getText().toString().trim()) > Integer.parseInt(endEdit.getText().toString().trim())) {
                        Toast.makeText(main_screen.this, "Начало не может быть больше конца диапазона", Toast.LENGTH_SHORT).show();
                    } else {
                        // если никакая задача не выбрана
                        if (flag == 0) {
                            Toast.makeText(main_screen.this, "Выберите задачу", Toast.LENGTH_SHORT).show();
                        } else if (flag == 1) { //если выбрана первая задача то перебираем значения
                            int f = 0;
                            for (int i = Integer.parseInt(startEdit.getText().toString().trim());
                                 i <= Integer.parseInt(endEdit.getText().toString().trim()); i++) {
                                if (Cukerman(i)) {
                                    f = 1;
                                    viewModel.items.add(String.valueOf(i));
                                    adapter.notifyDataSetChanged();
                                }
                            }
                            if (f == 0) {
                                viewModel.items.add("Ничего не найдено");
                                adapter.notifyDataSetChanged();
                            }
                        } else if (flag == 2) { //если выбрана вторая задача то перебираем значения
                            int f = 0;
                            for (int i = Integer.parseInt(startEdit.getText().toString().trim());
                                 i <= Integer.parseInt(endEdit.getText().toString().trim()); i++) {
                                if (Niven(i)) {
                                    f = 1;
                                    viewModel.items.add(String.valueOf(i));
                                    adapter.notifyDataSetChanged();
                                }
                            }
                            if (f == 0) {
                                viewModel.items.add("Ничего не найдено");
                                adapter.notifyDataSetChanged();
                            }
                        } else if (flag == 3) {
                            int f = 0; //если выбрана третья задача то перебираем значения
                            for (int i = Integer.parseInt(startEdit.getText().toString().trim());
                                 i <= Integer.parseInt(endEdit.getText().toString().trim()); i++) {
                                if (ConstKaprekar(i)) {
                                    f = 1;
                                    viewModel.items.add(String.valueOf(i));
                                    adapter.notifyDataSetChanged();
                                }
                            }
                            if (f == 0) {
                                viewModel.items.add("Ничего не найдено");
                                adapter.notifyDataSetChanged();
                            }
                        } else if (flag == 4) { //если выбрана четвертая задача то перебираем значения
                            int f = 0;
                            for (int i = Integer.parseInt(startEdit.getText().toString().trim());
                                 i <= Integer.parseInt(endEdit.getText().toString().trim()); i++) {
                                if (FindNumKaprekar(i)) {
                                    f = 1;
                                    viewModel.items.add(String.valueOf(i));
                                    adapter.notifyDataSetChanged();
                                }
                            }
                            if (f == 0) {
                                viewModel.items.add("Ничего не найдено");
                                adapter.notifyDataSetChanged();
                            }
                        }
                    }
                }
            }
        });
        // кнопки присваивают задачу
        btnCukerman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 1;
                viewModel.items.add("Задача на нахождение чисел Цукермана.");
                adapter.notifyDataSetChanged();
            }
        });
        btnNiven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 2;
                viewModel.items.add("Задача на нахождение чисел Нивена.");
                adapter.notifyDataSetChanged();
            }
        });
        btnConstKaprekar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 3;
                viewModel.items.add("Задача на нахождение постоянной Капрекара 6174.");
                adapter.notifyDataSetChanged();
            }
        });
        btnFindkaprekar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 4;
                viewModel.items.add("Задача на нахождение чисел Капрекара.");
                adapter.notifyDataSetChanged();
            }
        });
        // кнопка очистки
        btnCleaning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.items.clear();
                adapter.notifyDataSetChanged();
                Toast.makeText(main_screen.this, "История очищена", Toast.LENGTH_SHORT).show();
            }
        });
        // переход на другую страницу(перенос имени и значений из списка)
        historyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent historyIntent = new Intent(main_screen.this, history.class);
                historyIntent.putStringArrayListExtra("historyList", viewModel.items);
                historyIntent.putExtra("userName", userName);
                startActivity(historyIntent);
            }
        });
        // валидация полей
        startEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().isEmpty()) {
                    startLayout.setError("Обязательное поле!");
                    startbool = false;
                }else{
                    try {
                        int val = Integer.parseInt(s.toString());
                        if (val < -10_000 || val > 10_000){
                            startLayout.setError("Число должно быть в диапазоне от -10_000 до 10_000");
                            startbool = false;
                        } else{
                            startLayout.setError(null);
                            startbool = true;
                        }
                    } catch (NumberFormatException e){
                        startLayout.setError("Введите корректное число");
                        startbool = false;
                    }
                }

            }
        });

        endEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().isEmpty()){
                    endLayout.setError("Обязательное поле!");
                    endbool = false;
                }else {
                    try{
                        int val = Integer.parseInt(s.toString());
                        if (val < -10_000 || val > 10_000){
                            endLayout.setError("Число должно быть в диапазоне от -10_000 до 10_000");
                            endbool = false;
                        } else{
                            endLayout.setError(null);
                            endbool = true;
                        }
                    }catch (NumberFormatException e){
                        endLayout.setError("Введите корректное число");
                        endbool = false;
                    }

                }
            }
        });
        }
    }
