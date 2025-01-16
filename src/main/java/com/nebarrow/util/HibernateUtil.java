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

    static {
        Configuration configuration = new Configuration().configure();
        sessionFactory = configuration.buildSessionFactory();
        runMigrations(configuration);
    }

    private void runMigrations(Configuration configuration) {
        var flyway = Flyway.configure()
                .dataSource(
                        configuration.getProperty("hibernate.connection.url"),
                        configuration.getProperty("hibernate.connection.username"),
                        configuration.getProperty("hibernate.connection.password"))
                .locations("classpath:db/migration")
                .load();
        flyway.migrate();
    }
}
