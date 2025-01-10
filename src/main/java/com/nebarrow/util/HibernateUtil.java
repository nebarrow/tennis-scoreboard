package com.nebarrow.util;

import lombok.Getter;
import org.flywaydb.core.Flyway;
import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@UtilityClass
public class HibernateUtil {
    @Getter
    private static final SessionFactory sessionFactory;
    @Getter
    private static final Configuration configuration;

    static {
        configuration = new Configuration().configure();
        sessionFactory = configuration.buildSessionFactory();
        runMigrations();
    }

    private void runMigrations() {
        var flyway = Flyway.configure()
                .dataSource(
                        configuration.getProperty("connection.url"),
                        configuration.getProperty("connection.username"),
                        configuration.getProperty("connection.password"))
                .locations("classpath:db/migrations")
                .load();
        flyway.migrate();
    }
}
