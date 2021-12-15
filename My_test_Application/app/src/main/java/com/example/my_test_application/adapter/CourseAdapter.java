package com.example.my_test_application.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_test_application.CoursePage;
import com.example.my_test_application.R;
import com.example.my_test_application.model.Course;

import java.util.List;

//все то же что и в категорииадаптер
//в адаптер передаем вложенный класс через который опишем все характеристики, сможем указать что конкретно будет находиться в recyclerview
public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {
    Context context;
    List<Course> courses;

    public CourseAdapter(Context context, List<Course> courses) {
        this.context = context;
        this.courses = courses;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View courseItems = LayoutInflater.from(context).inflate(R.layout.course_item, parent, false);
        return new CourseViewHolder(courseItems);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.CourseViewHolder holder, int position) {
        /*через холдер обращаемся к нужному полю courseBg
        setBackgroundColor int, обращаемся к списку с курсами,
        через список обращаемся к определенной позиции
        и вытягиваем нужный цвет
        но перед этим все нужн преобразовать в нужный формат
        обращаясь к классу Color, parseColor преобразует цвет в integer*/
        holder.courseBg.setCardBackgroundColor(Color.parseColor(courses.get(holder.getLayoutPosition()).getColor()));

        /*подставляем определенные изображения,обращаемся к  курсам по позиции и по определенной картинке,
        указываем в какой папке(drawable), с помощью getPackageName получаем id*/
        int imageId = context.getResources().getIdentifier("ic_" + courses.get(holder.getLayoutPosition()).getImg(), "drawable", context.getPackageName());
        holder.courseImage.setImageResource(imageId);

        //дальше меняем название курса, дату и уровень
        holder.courseTitle.setText(courses.get(holder.getLayoutPosition()).getTitle());
        holder.courseDate.setText(courses.get(holder.getLayoutPosition()).getDate());
        holder.courseLevel.setText(courses.get(holder.getLayoutPosition()).getLevel());

        //устанавливаем обработчик событий
        /* обрабочик событий должен быть повешен на весь наш товар, обращаемся к itemView, передается в конструктор, */
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CoursePage.class);
                //указываем опции при переходе  между активити
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) context,
                        new Pair<View, String>(holder.courseImage, "courseImage")//указываем что конкретно должно анимироваться
                );

                intent.putExtra("courseBg", Color.parseColor(courses.get(holder.getLayoutPosition()).getColor()));
                intent.putExtra("courseImage", imageId);
                intent.putExtra("courseTitle", courses.get(holder.getLayoutPosition()).getTitle());
                intent.putExtra("courseDate", courses.get(holder.getLayoutPosition()).getDate());
                intent.putExtra("courseLevel", courses.get(holder.getLayoutPosition()).getLevel());
                intent.putExtra("courseText", courses.get(holder.getLayoutPosition()).getText());
                intent.putExtra("courseId", courses.get(holder.getLayoutPosition()).getId());

                context.startActivity(intent, options.toBundle()); // при запуске активити передаем опции
            }
        });
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public static final class CourseViewHolder extends RecyclerView.ViewHolder {

        //прописываем с какими элементами будем работать
        CardView courseBg;
        ImageView courseImage;
        TextView courseTitle, courseDate, courseLevel;


        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);

            courseBg = itemView.findViewById(R.id.courseBg);
            courseImage = itemView.findViewById(R.id.courseImage);
            courseTitle = itemView.findViewById(R.id.courseTitle);
            courseDate = itemView.findViewById(R.id.courseDate);
            courseLevel = itemView.findViewById(R.id.courseLevel);

        }
    }
}
