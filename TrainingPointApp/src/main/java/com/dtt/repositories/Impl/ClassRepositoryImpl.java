/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.repositories.Impl;

import com.dtt.pojo.Class;
import com.dtt.repositories.ClassRepository;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ThienTu
 */

@Repository
@Transactional
public class ClassRepositoryImpl implements ClassRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Class> getClasses() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Class.findAll");

        return q.getResultList();
    }

    @Override
    public Class getClassById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Class.class, id);
    }

    @Override
    public void addOrUpdate(Class c) {
        Session s = this.factory.getObject().getCurrentSession();
        if (c.getId() != null) {
            s.update(c);
        } else {
            s.save(c);
        }
    }

    @Override
    public void delete(Class c) {
        Session s = this.factory.getObject().getCurrentSession();

        s.delete(c);
    }

    @Override
    public Class findByName(String name) {
        Session s = this.factory.getObject().getCurrentSession();

        Query q = s.createNamedQuery("Class.findByName");
        q.setParameter("name", name);

        try {
            return (Class) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
