package com.example.my_test_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.my_test_application.adapter.CategoryAdapter;
import com.example.my_test_application.adapter.CourseAdapter;
import com.example.my_test_application.model.Category;
import com.example.my_test_application.model.Course;
import com.example.my_test_application.model.Order;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView categoryRecycler, courseRecycler;
    CategoryAdapter categoryAdapter;
    static CourseAdapter courseAdapter;
    static List<Course> courseList = new ArrayList<>();
    static List<Course> fullCourseList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //выделяем память для списка категорий
        List<Category> categoryList = new ArrayList<>();
        //каждый новый элемент на основе нашей модели(категории)
        categoryList.add(new Category(0, "Все"));
        categoryList.add(new Category(1, "Игры"));
        categoryList.add(new Category(2, "Сайты"));
        categoryList.add(new Category(3, "Языки"));
        categoryList.add(new Category(4, "Прочее"));

        //вызовем наш собственный метод
        setCategoryRecycler(categoryList);

        /* создаем новые курсы */
        //каждый новый элемент на основе нашей модели(курсов)
        courseList.add(new Course(1, "java", "Профессия Java\nразработчик", "1 января", "начальный", "#424345", "test", 3));
        courseList.add(new Course(2, "python","Профессия Python\nразработчик", "12 января", "начальный", "#9FA52D", "test",3));
        courseList.add(new Course(3, "unity","Профессия Unity\nразработчик", "13 января", "начальный", "#DC143C", "test", 1));
        courseList.add(new Course(4, "front_end","Профессия Front End\nразработчик", "20 января", "начальный", "#F08080", "test", 2));
        courseList.add(new Course(5, "back_end","Профессия Back End\nразработчик", "25 января", "средний", "#4682B4", "test",2));
        courseList.add(new Course(6, "full_stack","Профессия Full Stack\nразработчик", "30 января", "профи", "#4B0082", "test", 2));

        //добавляем все элементы которые будут храниться в courselist
        fullCourseList.addAll(courseList);

        //вызовем наш собственный метод
        setCourseRecycler(courseList);
    }
    //открываем корзину
    public void openCart(View view) {
        Intent intent = new Intent(this, OrderPage.class);
        startActivity(intent);
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


    private void setCategoryRecycler(List<Category> categoryList) {
        /*указываем настройки для вывода информации, по умолчанию вся информация выводится вертикально,
        а нам нужно горизонтально, указываем что здесь же на этой странице контекст,
        обращаемся к ресаклвью и указываем горизонтальность, и фолс что бы элементы не шли в обратной последовательности*/
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        //устанавливаем ссылку на нужный объект
        categoryRecycler = findViewById(R.id.categoryRecycler);
        categoryRecycler.setLayoutManager(layoutManager);

        categoryAdapter = new CategoryAdapter(this, categoryList);
        categoryRecycler.setAdapter(categoryAdapter);
    }

    private void setCourseRecycler(List<Course> courseList) {
        /*указываем настройки для вывода информации, по умолчанию вся информация выводится вертикально,
        а нам нужно горизонтально, указываем что здесь же на этой странице контекст,
        обращаемся к ресаклвью и указываем горизонтальность, и фолс что бы элементы не шли в обратной последовательности*/
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        //устанавливаем ссылку на нужный объект
        courseRecycler = findViewById(R.id.courseRecycler);
        courseRecycler.setLayoutManager(layoutManager);

        courseAdapter = new CourseAdapter(this, courseList);
        courseRecycler.setAdapter(courseAdapter);

    }

    public static void showCoursesByCategory(int category) {
        //при каждом заходе в этот метод очищаем список
        courseList.clear();
        //добавляем все курсы и дальше фильтруем их
        //при нажатии на 0 показывает все курсы
        if (category == 0) {
            courseList.addAll(fullCourseList);
        } else {
            for (Course course : fullCourseList) {
                if (course.getCategory() == category) {
                    courseList.add(course);
                }
            }
        }
        courseAdapter.notifyDataSetChanged();
    }
}