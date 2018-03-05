package com.helloworld.dao;

import com.helloworld.entity.Entity;
import com.helloworld.entity.User;

import java.util.Collection;

public interface EntityDao {
    Collection<User> getAllEntities();

    User getEntityById(int id);

    void removeEntityById(int id);

    void updateEntity(User entity);

    void insertEntityToDb(User entity);
}
