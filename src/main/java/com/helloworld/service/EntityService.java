package com.helloworld.service;

import com.helloworld.dao.EntityDao;
import com.helloworld.entity.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class EntityService {

    @Autowired
    @Qualifier("fakeData")
    private EntityDao entityDao;

    public Collection<Entity> getAllEntities() {
        return this.entityDao.getAllEntities();
    }

    public Entity getEntityById(int id) {
        return this.entityDao.getEntityById(id);
    }

    public void removeEntityById(int id) {
        this.entityDao.removeEntityById(id);
    }

    public void updateEntity(Entity entity) {
        this.entityDao.updateEntity(entity);
    }

    public void insertEntity(Entity entity) {
        this.entityDao.insertEntityToDb(entity);
    }
}
