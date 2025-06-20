package com.example.myapplication;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
//                     2
//        ConstraintLayout constraintLayout = new ConstraintLayout( this);
//        TextView textView = new TextView(this);
//        // установка фонового цвета
//        textView. setBackgroundColor(0xffe8eaf6);
//        // установка цвета текста
//        textView. setTextColor(0xff5c6bc0);
//        // делаем все буквы заглавными
//        textView.setAllCaps(true);
//        // устанавливаем вравнивание текста по центру
//        textView.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
//        // устанавливаем текста
//        textView.setText("Hello Android!");
//        // установка шрифта
//        textView.setTypeface(Typeface.create("casual", Typeface.NORMAL));
//        // устанавливаем высоту текста
//        textView.setTextSize(26);
//
//        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams
//                (ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
//        layoutParams.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
//        layoutParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
//        textView.setLayoutParams(layoutParams);
//        constraintLayout.addView(textView);
//        setContentView(constraintLayout);




        //       3
//        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
//        TextView textView = findViewById(R.id.changeTxt);
//        textView.setText("My final message... Goodbye");



        // 4
//        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
//        TextView textView = findViewById(R.id.changeTxt);
//        textView.setText("Привет 100sp");
//        textView.setTextSize(100);


        // 5
//        TextView textView = findViewById(R.id.changeTxt);
//        TextView textView3 = findViewById(R.id.changeTxt3);
//        textView.setTextColor(Color.CYAN);
//        textView.setBackgroundColor(Color.BLACK);
//
//        textView.setTextColor(Color.parseColor("#00FF00"));
//        textView.setBackgroundColor(Color.parseColor("#FF0000"));
//
//        textView.setTextColor(getResources().getColor(R.color.newColorSuperMegaCool));
//        textView.setBackgroundColor(getResources().getColor(R.color.newColorSuperMega));
//
//        textView3.setTextColor(ContextCompat.getColor( this, R.color.newColorSuperMega));
//        textView3.setBackgroundColor(ContextCompat.getColor(this, R.color.newColorSuperMegaCool));


          // 6
//        TextView textView = findViewById(R.id.changeTxt);
//        textView.setTypeface(Typeface.MONOSPACE);


//        // 7
//        TextView textView = findViewById(R.id.changeTxt);
//        textView.setText("Привет 100sp");
//        textView.setTextSize(100);
//        textView.setTypeface(null, Typeface.BOLD_ITALIC);


        // 8
//        TextView textView = findViewById(R.id.changeTxt);
//        textView.setLetterSpacing(2.2F);

        //9
//        TextView textView = findViewById(R.id.changeTxt);
//        textView.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);


        //10
//        TextView textView = findViewById(R.id.changeTxt);
//        textView.setText("1------------- \n" +
//                "2------------- \n" +
//                "3------------- \n" +
//                "4------------- \n");
//        textView.setMaxLines(3);



        //11
//        TextView textView = findViewById(R.id.changeTxt);
//        textView.setTextIsSelectable(true);


        //13
//        String str1 = new String("Hello Android");
//        String str2 = "Hello Android";
//        String a = "hello";  // a: "hello"
//        String b = "hello";  // b: "hello"
//        String c = new String("hello");  // c: "hello"
//
//        if (a == b) {
//            Log.i("a == b", "true");
//        } else {
//            Log.e( "a == b", "false");
//        }
//
//        if (a == c) {
//            Log.i("a == c", "true");
//        } else {
//            Log.e("a == c", "false");
//        }
//        if (a.equals(b)) {
//            Log.i("a.equals(b)", "true");
//        } else {
//            Log.e("a.equals(b)", "false");
//        }
//
//        if (a.equals(c)) {
//            Log.i("a.equals(c)", "true");
//        } else {
//            Log.e("a.equals(c)", "false");
//        }

        //14
//        String result;
//
//        result = "test" + 1 + "|" + 2 + "test";
//        Log.e("Конкатенация-1", result);
//        String firstName = "Nikita";
//        String secondName = "Dombrovskiy";
//        result = firstName + secondName;
//        Log.e("Конкатенация-2", result);
//        result = firstName + " |" + secondName;
//        Log.e("Конкатенация-3", result);
//
//        String form2 = getString(R.string.app_name);
//        Log.e("Form", form2);
//
//        String result2 = String.format("Value: %d", 42);
//        Log.e("Form", result2);
//
//        String str = "Hello Android";
//
//        int length = str.length(); // 13
//
//        char ch = str.charAt(1); // 'e'
//
//        String sub1 = str.substring(6); // "Android"
//        String sub2 = str.substring(0, 5); // "Hello"
//
//        int index = str.indexOf("And"); // 6
//        boolean contains = str.contains("Hello"); // true
//
//        String replaced = str.replace("Hello", "Hi");
//
//        String[] parts = str.split(" "); // ["Hello", "Android"]
//
//        String upper = str.toUpperCase(); // "HELLO ANDROID"
//        String lower = str.toLowerCase(); // "hello android"
//
//        String withSpaces = " text ";
//        String trimmed = withSpaces.trim(); // "text"
//
//        TextView textView = findViewById(R.id.changeTxt);
//
//        String text = textView.getText().toString();
//        textView.setText("Hello");
//        for (int i = 0; i < 5; i++){
//            textView.append(text);
//        }


           //15
//        SpannableString spannable = new SpannableString(
//                "Повседневная практика показывает, " +
//                        "что перспективное планирование позволяет выполнить важные задания по разработке " +
//                        "существующих финансовых и административных условий."
//        );
//
//        // Жирный текст (0-5 символов)
//        spannable.setSpan(
//                new StyleSpan(Typeface.BOLD),
//                0,
//                 5,
//                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
//);
//
//// Курсив (7-14 символы)
//        spannable.setSpan(
//                new StyleSpan(Typeface.ITALIC),
//                 7,
//                 14,
//                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
//);
//
//// Красный цвет (27-37 символы)
//        spannable.setSpan(
//                new ForegroundColorSpan(Color.RED),
//                 27,
//                37,
//                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
//);
//
//// Подчеркивание (39-50 символы)
//        spannable.setSpan(
//                new UnderlineSpan(),
//                 39,
//                50,
//                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
//);
//
//// Зачеркивание (52-64 символы)
//        spannable.setSpan(
//                new StrikethroughSpan(),
//                 52,
//                 64,
//                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
//);
//        TextView textView4 = findViewById(R.id.changeTxt);
//
//        textView4.setText(spannable);

//        // 16
//        TextView textView51 = findViewById(R.id.changeTxt51);
//        TextView textView52 = findViewById(R.id.changeTxt52);
//        String html = "<b>Bold</b> and <i>italic</i>";
//        Spanned text = Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT);
//        textView51.setText(text);
//
//        String htmlWithLink = "Посетите <a href='https://www.oat.ru/'>наш  сайт</a> для подробностей.";
//        Spanned linkText = Html.fromHtml(htmlWithLink, Html.FROM_HTML_MODE_LEGACY);
//
//        textView52.setText(linkText);
//        textView52.setMovementMethod(LinkMovementMethod.getInstance());
    }
}