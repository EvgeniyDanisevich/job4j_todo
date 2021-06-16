package ru.job4j.todo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "category")
public class Category implements Model {
    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonProperty("name")
    private String name;

    public Category(String name) {
        this.name = name;
    }

    public Category() {
    }

    @Override
    public Integer getId() {
        return this.id;
    }
}
