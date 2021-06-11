package ru.job4j.todo.store;

import ru.job4j.todo.model.Item;

import java.util.Collection;

public interface Store {
    Collection<Item> findAll();
    Item findById(int id);
    void save(Item model);
}
