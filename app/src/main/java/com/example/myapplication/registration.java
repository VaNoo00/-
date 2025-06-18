package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class registration extends AppCompatActivity {
    // Объявляем элементы интерфейса
    TextInputLayout emailLayout, passwordLayout, nameLayout, povtPassLayout;
    TextInputEditText emailEditText, passwordEditText, nameEditText, povtPassEditText;
    TextView proverka, perehodAut;
    Button registr;
    // Флаги проверки полей формы
    boolean checkEmail = false;
    boolean checkPassword = false;
    boolean checkName = false;
    boolean checkPovtPass = false;

    /**
     * Метод инициализации элементов интерфейса
     */
    public void init() {
        emailLayout = findViewById(R.id.textEmail);
        emailEditText = findViewById(R.id.textEmailEdit);
        passwordLayout = findViewById(R.id.textPassword);
        passwordEditText = findViewById(R.id.textPasswordEdit);
        nameLayout = findViewById(R.id.textName);
        nameEditText = findViewById(R.id.textNameEdit);
        povtPassLayout = findViewById(R.id.textPovtPassword);
        povtPassEditText = findViewById(R.id.textPovtPasswordEdit);
        proverka = findViewById(R.id.proverka);
        perehodAut = findViewById(R.id.perehodAut);
        registr = findViewById(R.id.registr);
    }
    /**
     * Метод проверяет корректность заполнения всех полей
     * return true если все данные корректны, иначе false
     */
    public boolean onClick2() {
        if (checkEmail && checkPassword && checkName && checkPovtPass) {
            proverka.setText("Все корректно");
            return true;
        }
        else{
            proverka.setText("Где-то ошибка");
            // Устанавливаем ошибки на поля, которые не прошли проверку
            if(!checkEmail){
                emailLayout.setError("Обязательное поле");
            }
            if(!checkPassword){
                passwordLayout.setError("Обязательное поле");
            }
            if(!checkPovtPass){
                povtPassLayout.setError("Обязательное поле");
            }
            if(!checkName){
                nameLayout.setError("Обязательное поле");
            }
            return false;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registration);
        // Инициализируем элементы интерфейса
        init();
        // Обработчик нажатия на кнопку "Зарегистрироваться"
        registr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Проверяем валидность данных
                // Если всё верно — переходим в главное меню
                if(onClick2()){
                    Intent intent = new Intent(registration.this, main_screen.class);
                    String name = nameEditText.getText().toString();
                    intent.putExtra("userName", name); // Передаем имя как userName
                    startActivity(intent);
                }
            }
        });
        // Переход на экран авторизации
        perehodAut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(registration.this, MainActivity.class);
                startActivity(intent);
            }
        });
        /**
         * Валидация имени:
         * - Минимум 5 символов
         * - Максимум 20 символов
         */
        nameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String name = nameEditText.getText().toString();
                if (name.length() < 5) {
                    nameLayout.setError("Имя должно содержать минимум 5 символов");
                    checkName = false;
                } else if (name.length() > 20) {
                    nameLayout.setError("Имя не должно содержать больше 20 символов");
                    checkName = false;
                } else {
                    nameLayout.setError(null);
                    checkName = true;
                }
            }
        });
        /**
         * Валидация email:
         * - Должен быть пустым или соответствовать формату email
         */
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
        /**
         * Обработка ввода пароля
         * - Проверка длины пароля
         * - Переход к полю повтора пароля
         */
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
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
                        povtPassEditText.requestFocus();
                    }
                    return true;

                }
                return false;
            }
        });
        /**
         * Проверка совпадения паролей
         * - Пароль и его повтор должны совпадать
         */
        povtPassEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String povtpassword = povtPassEditText.getText().toString();
                if (!povtpassword.equals(passwordEditText.getText().toString())) {
                    povtPassLayout.setError("Пароли не совпадают");
                    checkPovtPass = false;
                }  else {
                    povtPassLayout.setError(null);
                    checkPovtPass = true;
                }
            }
        });
    }
}