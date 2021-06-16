package ru.job4j.todo.store.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import ru.job4j.todo.model.Category;
import ru.job4j.todo.model.Item;
import ru.job4j.todo.model.Model;
import ru.job4j.todo.model.User;
import ru.job4j.todo.store.Store;

import java.util.function.Function;

import java.util.Collection;

public class HbmStore implements Store, AutoCloseable {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void close() {
        StandardServiceRegistryBuilder.destroy(registry);
    }

    @Override
    public Collection<Item> findAll() {
        return this.tx(session -> session.createQuery(
                "select distinct i from Item i join fetch i.categories order by i.id").list()
        );
    }

    @Override
    public void save(Model model) {
        this.tx(session -> session.save(model));
    }

    @Override
    public Item changeDone(Integer id) {
        return this.tx(session -> {
            Item item = session.get(Item.class, id);
            item.setDone(!item.isDone());
            return item;
        });
    }

    @Override
    public User findByEmail(String email) {
        return (User) this.tx(session -> {
            final Query query = session.createQuery("from User where email=:email");
            query.setParameter("email", email);
            return query.uniqueResult();
        });
    }

    @Override
    public Collection<Category> allCategory() {
        return this.tx(session -> session.createQuery("from Category").list());
    }

    @Override
    public void addNewCategory(Item item, String[] ids) {
        this.tx(session -> {
            for (String id : ids) {
                Category findCategory = session.find(Category.class, Integer.parseInt(id));
                item.addCategory(findCategory);
            }
            session.save(item);
            return item;
        });
    }
}
