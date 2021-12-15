package com.example.my_test_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.my_test_application.model.Course;
import com.example.my_test_application.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_page);

        ListView orders_list = findViewById(R.id.orders_list);

        //создаем список с информацией для корзины
        List<String> coursesTitle = new ArrayList<>();
        //цикл переберает список курсов, находим каждый курс по id и выводим пользвателю
        for (Course c : MainActivity.fullCourseList) {
            if (Order.itemIds.contains(c.getId())) {
                coursesTitle.add(c.getTitle());
            }
        }

        //обращаемся к списку и указываем стандартный дизайн
        orders_list.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, coursesTitle));
    }

    /*public void removeToCart(View view) {
        int itemId = getIntent().getIntExtra("courseId", 0);
        Order.itemIds.remove(itemId);
        Toast.makeText(this, "Добавлено", Toast.LENGTH_LONG).show();
    }*/

    //открываем контакты
    public void openContacts(View view) {
        Intent intent = new Intent(this, ContactUsPage.class);
        startActivity(intent);
    }

    //открываем о нас
    public void openAbout (View view) {
        Intent intent = new Intent(this, AboutUsPage.class);
        startActivity(intent);
    }

    //открываем главное
    public void openMain (View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}