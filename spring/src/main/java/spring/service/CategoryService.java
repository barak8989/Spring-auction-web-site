package spring.service;
import java.util.ArrayList;
import java.util.List;

import spring.model.Category;


public class CategoryService  {

    public static List<Category> getAllCategories() {

        List<Category> categories = new ArrayList<>();

        categories.add(new Category(1L, "Electronics"));
        categories.add(new Category(2L, "Fashion"));
        categories.add(new Category(3L, "Health & Beauty"));

        return categories;
    }
}
