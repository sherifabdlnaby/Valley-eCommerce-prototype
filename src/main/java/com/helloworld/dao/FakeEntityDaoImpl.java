package com.helloworld.dao;

import com.helloworld.entity.Entity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
@Qualifier("fakeData")
public class FakeEntityDaoImpl implements EntityDao {

    private static Map<Integer, Entity> entities;

    static {

        entities = new HashMap<Integer, Entity>() {

            {
                put(1, new Entity(1, "Toba", "Desc for first entity"));
                put(2, new Entity(2, "Wara2a", "Desc for second entity"));
                put(3, new Entity(3, "Ma2as", "Desc for third entity"));
            }
        };
    }

    @Override
    public Collection<Entity> getAllEntities() {
        return entities.values();
    }

    @Override
    public Entity getEntityById(int id) {
        return entities.get(id);
    }

    @Override
    public void removeEntityById(int id) {
        entities.remove(id);
    }

    @Override
    public void updateEntity(Entity entity) {
        Entity s = entities.get(entity.getId());
        s.setDesc(entity.getDesc());
        s.setName(entity.getName());
        entities.put(entity.getId(), entity);
    }

    @Override
    public void insertEntityToDb(Entity entity) {
        entities.put(entity.getId(), entity);
    }
}
