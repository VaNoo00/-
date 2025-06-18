package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    // Объявляем элементы интерфейса
    TextInputLayout emailLayout, passwordLayout;
    TextInputEditText emailEditText, passwordEditText;
    TextView textError, perehodReg;
    Button autorization, btnVk, btnGoogle;
    boolean checkEmail = false;
    boolean checkPassword = false;

    /**
     * Метод инициализации элементов интерфейса
     */
    public void init() {
        emailLayout = findViewById(R.id.textField);
        emailEditText = findViewById(R.id.textField1);
        passwordLayout = findViewById(R.id.textField2);
        passwordEditText = findViewById(R.id.textField22);
        textError = findViewById(R.id.textError);
        autorization = findViewById(R.id.button2);
        perehodReg = findViewById(R.id.perehodReg);
        btnVk = findViewById(R.id.buttonVK);
        btnGoogle = findViewById(R.id.buttonGoogle);
    }



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        btnVk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://vk.com/login";
                Intent intentVK = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intentVK);
            }
        });
        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://accounts.google.com/AccountChooser?hl=ru%0A";
                Intent intentGoogle = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intentGoogle);
            }
        });
        // Переход к экрану регистрации
        perehodReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, registration.class);
                startActivity(intent);
            }
        });

        /**
         * Метод для обработки нажатия на кнопку "Авторизация"
         * Проверяет валидность email и пароля
         */
        // Переход к основному экрану
        autorization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkEmail && checkPassword) {
                    textError.setText("Все корректно");
                    Intent intent = new Intent(MainActivity.this, main_screen.class);
                    String email = emailEditText.getText().toString().trim();
                    intent.putExtra("userName", email); // Передаем email как userName
                    startActivity(intent);
                }
                else{
                    textError.setText("Где-то ошибка");
                    if(!checkEmail){
                        emailLayout.setError("Обязательное поле");
                    }
                    if(!checkPassword){

                        passwordLayout.setError("Обязательное поле");
                    }
                }
            }
        });
        // Проверка на корректность почты
        emailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String email = s.toString().trim();
                if (!email.isEmpty() && !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    emailLayout.setError("Введите корректный email");
                    checkEmail = false;
                } else {
                    emailLayout.setError(null);
                    checkEmail = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > emailLayout.getCounterMaxLength())
                    emailLayout.setError("Электронная почта не может содержать больше "
                            + emailLayout.getCounterMaxLength() + "символов");
            }
        });
        // Проверка на корректность пароля
        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String password = passwordEditText.getText().toString();
                if (password.length() < 8) {
                    passwordLayout.setError("Пароль должен содержать минимум 8 символов");
                    checkPassword = false;
                } else if (password.length() > 20) {
                    passwordLayout.setError("Пароль не должен содержать больше 20 символов");
                    checkPassword = false;
                } else {
                    passwordLayout.setError(null);
                    checkPassword = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }
}

