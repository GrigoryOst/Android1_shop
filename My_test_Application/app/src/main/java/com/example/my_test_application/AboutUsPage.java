package com.example.my_test_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AboutUsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us_page);


    }

    //открываем контакты
    public void openContacts(View view) {
        Intent intent = new Intent(this, ContactUsPage.class);
        startActivity(intent);
    }

    //открываем главное
    public void openMain (View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}