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
package com.msb.app.management.system.bansos.service.user;

import com.msb.app.management.system.bansos.helper.HibernateSessionFactory;
import com.msb.app.management.system.bansos.model.UserEntity;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author difaagh
 */
public class UserDaoImpl implements UserDao {

    @Override
    public Collection getAll() throws SQLException {
        Session session = null;
        List userList = new ArrayList<UserEntity>();
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            userList = session.createCriteria(UserEntity.class).list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return userList;
    }

    @Override
    public void update(UserEntity user) throws SQLException {
          Session session = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(user);
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
    public void create(UserEntity user) throws SQLException {
       Session session = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            throw new SQLException(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public String login(String username, String password) throws SQLException {
        Session session = null;
        ArrayList<UserEntity> userList = new ArrayList();
        
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            userList = (ArrayList<UserEntity>) session.createCriteria(UserEntity.class).add(Restrictions.eq("username",username)).add(Restrictions.eq("password", password)).list();
            if(userList.size() == 0){
                String toReturn = "false";
                return toReturn;
            }
            UserEntity user = userList.get(0);
            String toReturn = user.getRole();
            return toReturn;
        } catch (HibernateException e) {
            e.printStackTrace();
            throw new SQLException(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

}
