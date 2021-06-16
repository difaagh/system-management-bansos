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

import org.yaml.snakeyaml.Yaml;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
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
            Yaml yaml = new Yaml();
            InputStream inputStream = new FileInputStream(new File("settings.yaml"));
            HashMap env = yaml.load(inputStream);
            String password = env.get("DB_PASSWORD") == null ? "" : (String) env.get("DB_PASSWORD");
            boolean isMysql = "MYSQL".equals(env.get("DB_USE"));
            String dialect = isMysql ? "org.hibernate.dialect.MySQLDialect" : "org.hibernate.dialect.MySQL5InnoDBDialect";
            String dbName = (String) env.get("DB_NAME");
            Integer p = (Integer) env.get("DB_PORT");
            boolean b = (boolean) env.get("DB_LOG");
            String username = (String) env.get("DB_USERNAME");
            String log = ""+b+"";
            String port = p.toString();
            String connection = isMysql ? "jdbc:mysql://localhost:" + port + "/" + dbName : "jdbc:mariadb://localhost:" + port + "/" + dbName;
            Configuration config;
            config = new Configuration().setProperty("hibernate.connection.url", connection)
                    .setProperty("hibernate.connection.username", (String) env.get("DB_USERNAME"))
                    .setProperty("hibernate.connection.password", password)
                    .setProperty("hibernate.dialect", dialect)
                    .setProperty("hibernate.show_sql",log)
                    .addAnnotatedClass(com.msb.app.management.system.bansos.model.EventEntity.class)
                    .addAnnotatedClass(com.msb.app.management.system.bansos.model.CashEntity.class)
                    .addAnnotatedClass(com.msb.app.management.system.bansos.model.PackageEntity.class)
                    .addAnnotatedClass(com.msb.app.management.system.bansos.model.CashReportEntity.class)
                    .addAnnotatedClass(com.msb.app.management.system.bansos.model.UserEntity.class)
                    .addAnnotatedClass(com.msb.app.management.system.bansos.model.ReceiverEntity.class);
            serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(config.getProperties()).build();
            sf = config.buildSessionFactory();
            return sf;
        } catch (Throwable ex) {
            System.err.println("Session factory creation failed." + ex);
            throw new ExceptionInInitializerError("Tidak dapat melakukan koneksi ke database\nTolong periksa file settings.yaml anda kembali!");
        }
    }

    public static SessionFactory getSessionFactory() {
        return sf;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}
