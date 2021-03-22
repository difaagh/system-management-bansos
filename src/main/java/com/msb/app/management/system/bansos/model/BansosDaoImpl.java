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
package com.msb.app.management.system.bansos.model;

import com.msb.app.management.system.bansos.helper.HibernateSessionFactory;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author difaagh
 */
public class BansosDaoImpl implements BansosDao {

    @Override
    public void createBansos(BansosEntity bansos) throws SQLException {
            Session session = null;
        try {
            System.out.println("disini aja");
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(bansos);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("disini");
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void updateBansos(BansosEntity bansos) throws SQLException {
         Session session = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(bansos);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public BansosEntity getBansosById(int bansos_id) throws SQLException {
        Session session = null;
        BansosEntity bansos = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            bansos = (BansosEntity)session.load(BansosEntity.class, bansos_id);
            Hibernate.initialize(bansos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return bansos;
    }

    @Override
    public Collection getAllBansos() throws SQLException {
        Session session = null;
        List listBansos = new ArrayList<BansosEntity>();
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            listBansos = session.createCriteria(BansosEntity.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return listBansos;
    }

    @Override
    public void deleteBansos(BansosEntity bansos) throws SQLException {
         Session session = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(bansos);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    
}
