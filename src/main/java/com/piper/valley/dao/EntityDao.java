package com.piper.valley.dao;

import com.piper.valley.entity.Entity;

import java.util.Collection;

public interface EntityDao {
    Collection<Entity> getAllEntities();

    Entity getEntityById(int id);

    void removeEntityById(int id);

    void updateEntity(Entity entity);

    void insertEntityToDb(Entity entity);
}
