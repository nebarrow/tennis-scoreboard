package com.nebarrow.repository;

import com.nebarrow.entity.BaseEntity;
import com.nebarrow.entity.Player;
import com.nebarrow.util.HibernateUtil;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class BaseRepository<K extends Integer, E extends BaseEntity> implements Repository<K, E> {

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private final Class<E> entityClass;

    public BaseRepository(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public E save(E entity) {
        try (var session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
            return (E) session.get(entityClass, entity.getId());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public E update(E entity) {
        try (var session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
            return (E) session.get(entityClass, entity.getId());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void delete(K id) {
        try (var session = sessionFactory.openSession()) {
            session.beginTransaction();
            var entity = session.get(entityClass, id);
            session.remove(entity);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Optional<E> getById(K id) {
        try (var session = sessionFactory.openSession()) {
            session.beginTransaction();
            var entity = session.get(entityClass, id);
            session.getTransaction().commit();
            if (entity == null) {
                return Optional.empty();
            }
            return Optional.of(entity);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<E> getAll() {
        try (var session = sessionFactory.openSession()) {
            return session.createQuery("from Player", entityClass).list();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
