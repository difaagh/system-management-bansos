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
package com.msb.app.management.system.bansos.service.event;

import com.msb.app.management.system.bansos.helper.HibernateSessionFactory;
import com.msb.app.management.system.bansos.model.EventEntity;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.hibernate.Session;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;

/**
 *
 * @author difaagh
 */
public class EventDaoImpl implements EventDao {

    @Override
    public void create(EventEntity event) throws SQLException {
        Session session = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(event);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void update(EventEntity event) throws SQLException {
        Session session = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(event);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public EventEntity getById(int event_id) throws SQLException {
        Session session = null;
        EventEntity event = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            event = (EventEntity) session.load(EventEntity.class, event_id);
            Hibernate.initialize(event);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return event;
    }

    @Override
    public Collection getAll() throws SQLException {
        Session session = null;
        List listEvent = new ArrayList<EventEntity>();
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            listEvent = session.createCriteria(EventEntity.class).list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return listEvent;
    }

    @Override
    public void delete(EventEntity event) throws SQLException {
        Session session = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(event);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public EventEntity getLatest() throws SQLException {
        Session session = null;
        EventEntity event = null;
        Date now = new Date();
        try {
            List listEvent = new ArrayList<EventEntity>();
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            listEvent = session.createCriteria(EventEntity.class).add(Restrictions.lt("startDate", now)).add(Restrictions.gt("endDate", now)).addOrder(Order.asc("startDate")).list();
            event = listEvent.isEmpty() ? null : (EventEntity) listEvent.get(0);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }

        }
        return event;
    }

}
