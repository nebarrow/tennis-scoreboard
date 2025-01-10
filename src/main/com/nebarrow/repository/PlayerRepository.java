package main.com.nebarrow.repository;

import main.com.nebarrow.entity.Player;
import org.hibernate.SessionFactory;
import src.main.com.nebarrow.util.HibernateUtil;

import java.util.List;
import java.util.Optional;

public class PlayerRepository implements Repository<Integer, Player> {

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public Player save(Player entity) {
        try (var session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
            return session.get(Player.class, entity.getId());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Player update(Player entity) {
        try (var session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
            return session.get(Player.class, entity.getId());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void delete(Integer id) {
        try (var session = sessionFactory.openSession()) {
            session.beginTransaction();
            var player = session.get(Player.class, id);
            session.detach(player);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Optional<Player> getById(Integer id) {
        try (var session = sessionFactory.openSession()) {
            session.beginTransaction();
            var player = session.get(Player.class, id);
            session.getTransaction().commit();
            if (player == null) {
                return Optional.empty();
            }
            return Optional.of(player);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Player> getAll() {
        try (var session = sessionFactory.openSession()) {
            return session.createQuery("from Player", Player.class).list();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
