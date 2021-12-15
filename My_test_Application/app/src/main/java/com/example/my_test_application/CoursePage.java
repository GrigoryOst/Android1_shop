package com.example.my_test_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.my_test_application.model.Order;

public class CoursePage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_page);

        /* создаем объект класса ConstraintLayout для изменения заднего фона */
        ConstraintLayout courseBg = findViewById(R.id.coursePageBg);
        ImageView courseImage = findViewById(R.id.coursePageImage);
        TextView courseTitle = findViewById(R.id.coursePageTitle);
        TextView courseDate = findViewById(R.id.coursePageDate);
        TextView courseLevel = findViewById(R.id.coursePageLevel);
        TextView courseText = findViewById(R.id.coursePageText);

        //здесь работаем не с карточкой как было в courseAdapter а работаем с ConstraintLayout
        courseBg.setBackgroundColor(getIntent().getIntExtra("courseBg", 0));
        courseImage.setImageResource(getIntent().getIntExtra("courseImage", 0));
        //когда работаем со строками дефолтное значение не нужно
        courseTitle.setText(getIntent().getStringExtra("courseTitle"));
        courseDate.setText(getIntent().getStringExtra("courseDate"));
        courseLevel.setText(getIntent().getStringExtra("courseLevel"));
        courseText.setText(getIntent().getStringExtra("courseText"));

        /* теперь при нажатии на какой то товар мы будем передавать данные в новую активити,
        а дальше в новой активити будут создавать переменные которые будут ссылаться на объекты из дизайна
        потом в эти объекты будем подставлять различные значения(цвет, изображения, надписи)*/
    }

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

    //при на нажатии добавления в корзину используется этот метод

    public void addToCart(View view) {
        int itemId = getIntent().getIntExtra("courseId", 0);
        Order.itemIds.add(itemId);
        Toast.makeText(this, "Добавлено", Toast.LENGTH_LONG).show();
    }
}
