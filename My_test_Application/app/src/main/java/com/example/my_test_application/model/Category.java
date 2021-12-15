package com.example.my_test_application.model;

public class Category {
    int id;
    String title;

    //через конструктор устанавливаем id  и title
    public Category(int id, String title) {
        this.id = id;
        this.title = title;
        /* this может использоваться внутри любого метода для ссылки на текущий объект.
        То есть this всегда служит ссылкой на объект, для которого был вызван метод.
        Ключевое слово this можно использовать везде*/
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
