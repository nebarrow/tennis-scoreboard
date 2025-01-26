package com.nebarrow.repository;

import com.nebarrow.exception.EntityNotFoundException;
import com.nebarrow.exception.RepositoryException;
import com.nebarrow.model.entity.BaseEntity;
import com.nebarrow.util.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

@Slf4j
public class BaseRepository<K extends Integer, E extends BaseEntity> implements Repository<K, E> {

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private final Class<E> entityClass;

    public BaseRepository(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public E save(E entity) {
        try (var session = sessionFactory.openSession()) {
            log.info("Saving entity {}", entity);
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
            log.info("Entity saved");
            return (E) session.get(entityClass, entity.getId());
        } catch (RepositoryException e) {
            log.error("Error while saving entity {}", e.getMessage(), e);
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public E update(E entity) {
        try (var session = sessionFactory.openSession()) {
            log.info("Saving entity: {}", entity);
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
            log.info("Entity saved: {}", entity);
            return (E) session.get(entityClass, entity.getId());
        } catch (RepositoryException e) {
            log.error("Error while saving entity: {}", e.getMessage(), e);
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public void delete(K id) {
        try (var session = sessionFactory.openSession()) {
            log.info("Deleting entity with id: {}", id);
            session.beginTransaction();
            var entity = session.get(entityClass, id);
            if (entity != null) {
                session.remove(entity);
                session.getTransaction().commit();
                log.info("Entity with id {} deleted successfully", id);
            } else {
                log.warn("Entity with id {} not found", id);
                throw new EntityNotFoundException("Entity not found");
            }
        } catch (RepositoryException e) {
            log.error("Error while deleting entity with id {}: {}", id, e.getMessage(), e);
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public Optional<E> getById(K id) {
        try (var session = sessionFactory.openSession()) {
            session.beginTransaction();
            var entity = session.get(entityClass, id);
            session.getTransaction().commit();
            if (entity == null) {
                log.warn("Entity with id {} not found", id);
                return Optional.empty();
            }
            log.info("Entity found: {}", entity);
            return Optional.of(entity);
        } catch (RepositoryException e) {
            log.error("Error while fetching entity with id {}: {}", id, e.getMessage(), e);
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public List<E> getAll() {
        try (var session = sessionFactory.openSession()) {
            log.info("Fetching all entities of type: {}", entityClass.getSimpleName());
            return session.createQuery("from " + entityClass.getName(), entityClass).list();
        } catch (RepositoryException e) {
            log.error("Error while fetching all entities: {}", e.getMessage(), e);
            throw new RepositoryException(e.getMessage());
        }
    }
}
