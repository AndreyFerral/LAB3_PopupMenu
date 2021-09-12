package com.example.lab3_popupmenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Описание тэга для логов debug
    private static final String TAG = "myLogs";

    @Override
    // Метод, который вызывается, когда приложение создает и отображает Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //  вызов метода родительского класса
        setContentView(R.layout.activity_main); // устанавливаем содержимое Activity из layout-файла

        // Определяем необходимые элементы управления
        Button button = (Button) findViewById(R.id.button);
        TextView tw = (TextView) findViewById(R.id.textView);

        // Обработка нажатия на кнопку
        button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View view) {

                Log.d(TAG, "Обработка нажатия на кнопку");

                // Изменение текста объекта управления textView
                // tw.setText("Привет, Мир!");

                // Реализация popup меню с иконками
                MenuBuilder menuBuilder = new MenuBuilder(MainActivity.this);
                MenuInflater inflater = new MenuInflater(MainActivity.this);
                inflater.inflate(R.menu.popup_menu, menuBuilder);
                @SuppressLint("RestrictedApi") MenuPopupHelper popupMenu = new MenuPopupHelper(MainActivity.this, menuBuilder, button);
                popupMenu.setForceShowIcon(true); // включаем показ иконок

                // Установка обработчика щелчка по объекту
                menuBuilder.setCallback(new MenuBuilder.Callback() {
                    @Override
                    // Обработка нажатия на элемент меню
                    public boolean onMenuItemSelected(MenuBuilder menu, MenuItem item) {

                        // Вывод заголовка элемента меню в textView
                        tw.setText("Вы нажали на " + item.getTitle());

                        Log.d(TAG, "Обработка нажатия на элемент меню");
                        return true;
                    }

                    @Override
                    public void onMenuModeChange(MenuBuilder menu) { }
                });

                // Отображение popup меню
                popupMenu.show();

            }
        });
    }
}