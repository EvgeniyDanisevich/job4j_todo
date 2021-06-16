package ru.job4j.todo.store;

import ru.job4j.todo.model.Category;
import ru.job4j.todo.model.Item;
import ru.job4j.todo.model.Model;
import ru.job4j.todo.model.User;

import java.util.Collection;

public interface Store {
    Collection<Item> findAll();
    User findByEmail(String email);
    void save(Model model);
    Item changeDone(Integer id);
    Collection<Category> allCategory();
    void addNewCategory(Item item, String[] ids);
}
