/*
 * The MIT License
 *
 * Copyright 2021 difaagh.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.msb.app.management.system.bansos.helper;

import io.github.cdimascio.dotenv.Dotenv;
import org.hibernate.SessionFactory;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author difaagh
 */
public class HibernateSessionFactory {

    private static SessionFactory sf = configureSessionFactory();
    private static ServiceRegistry serviceRegistry;

    private static SessionFactory configureSessionFactory() {
        try {
            Dotenv env;
            env = Dotenv.load();
            Configuration config;
            config = new Configuration().setProperty("hibernate.connection.url", env.get("DB_JDBC_CONNECTION"))
                    .setProperty("hibernate.connection.username", env.get("DB_USERNAME"))
                    .setProperty("hibernate.connection.password", env.get("DB_PASSWORD"))
                    .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                    .setProperty("hibernate.show_sql", env.get("DB_LOG"))
                    .addAnnotatedClass(com.msb.app.management.system.bansos.model.EventEntity.class);
            serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(config.getProperties()).build();
            sf = config.buildSessionFactory();
            return sf;
        } catch (Throwable ex) {
            System.err.println("Session factory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sf;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}
