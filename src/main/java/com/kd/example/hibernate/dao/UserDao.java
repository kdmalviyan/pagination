package com.kd.example.hibernate.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import com.kd.example.hibernate.entity.UserInfo;

@Repository
public class UserDao extends AbstractDao {
    @Autowired
    private Environment environment;

    @SuppressWarnings("unchecked")
    public List<UserInfo> getUsers(int pageNum) {
        int start = pageNum * 10 + 1;
        int end = Integer.parseInt(environment.getRequiredProperty("pagination.recordperpage"));
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("From UserInfo");
        query.setFirstResult(start);
        query.setMaxResults(end);
        List<UserInfo> userInfos = query.list();
        session.getTransaction().commit();
        session.close();
        return userInfos;
    }
}
