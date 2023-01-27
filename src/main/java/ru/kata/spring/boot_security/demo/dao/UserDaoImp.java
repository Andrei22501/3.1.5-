package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> listUsers() {
    return entityManager.createQuery("select u from User u", User.class)
            .getResultList();
    }

    @Override
    public User show(int id) {
     return entityManager.createQuery("select u from User u where u.id= ?1", User.class)
             .setParameter(1, id)
             .getResultList().stream().findFirst().orElse(null);
    }

    @Override
    public User showName(String name) {
        return entityManager.createQuery("select u from User u join fetch u.roles where u.name = ?1", User.class)
                .setParameter(1, name)
                .getResultList().stream().findFirst().orElse(null);
    }
    @Override
    public User showEmail(String email) {
        return entityManager.createQuery("select u from User u join fetch u.roles where u.email = ?1", User.class)
                .setParameter(1, email)
                .getResultList().stream().findFirst().orElse(null);
    }
    @Override
    public void update(User user) {
       entityManager.merge(user);
    }
    @Override
    public void delete(int id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }
    @Override
    public void save(User user) {
        entityManager.persist(user);
    }
}