package ru.job4j.todo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "item")
public class Item implements Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonProperty("categories")
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private final List<Category> categories = new ArrayList<>();

    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    private boolean done;

    public Item(User user, String description, boolean done) {
        this.user = user;
        this.description = description;
        this.created = new Date(System.currentTimeMillis());
        this.done = done;
    }

    public Item() {
    }

    public void addCategory(Category category) {
        this.categories.add(category);
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return id == item.id
                && done == item.done
                && Objects.equals(user, item.user)
                && Objects.equals(description, item.description)
                && Objects.equals(created, item.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, description, created, done);
    }
}
