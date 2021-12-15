package com.example.my_test_application.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_test_application.MainActivity;
import com.example.my_test_application.R;
import com.example.my_test_application.model.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    Context context; // в это поле будем уставаливать ту страницу на которой нам необходимо будет создать список
    List<Category> categories; // будет список со всеми возможными категориями, каждый элемент будет на основе пакета модель/катерогия

    //конструктор
    public CategoryAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //здесь укажем какой конкретный дизайн для всех наших категорий
        /*context страница на которой будем все отображать, inflate первый параметр дизайн который будем использовать,
         * класс родитель parent, false значит не прикрепляем весь дизайн к  родительскому объекту*/
        View categoryItems = LayoutInflater.from(context).inflate(R.layout.category_item, parent, false);
        return new CategoryViewHolder(categoryItems); // каждый элелементы с которым будем работать будет описан через вложенный класс
    }

    /*за счет этого метода мы сможем создать то отображение которое увидит пользователь,
    будем использовать те параметры которые были использованы через вложенный класс*/
    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.CategoryViewHolder holder, int position) {
        //здесь говорится что конкретно в каждое поле мы будем подставлять
        //в get передается индекс позиции, getTitle возвращает название категории
        holder.categoryTitle.setText(categories.get(holder.getLayoutPosition()).getTitle());

        //отслеживаем нажатия на каждую категорию
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //при нажатии на категорию вызывается этот метод(передается id нужной категории)
                MainActivity.showCoursesByCategory(categories.get(holder.getLayoutPosition()).getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return categories.size(); //вместо 0 будет возвращатся настояще количество элементов которые будет выведено в списке
    }

    /*в этом вложенном классе сможем обратиться к определенному дизайну, и из этого дизайно выбрать нужные элементы,
    и в нужные элементы вставим необходимый текст*/
    //final дописываем тк данный класс не будет имень наследников(конечный класс)
    public static final class CategoryViewHolder extends RecyclerView.ViewHolder {

        TextView categoryTitle;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            //itemView каждый элемент который будет выведен в всем общем списке
            //в конктретном элементе ищем с помощью findViewById
            categoryTitle = itemView.findViewById(R.id.categoryTitle);
        }
    }

}
