package com.helloworld.dao;

import com.helloworld.entity.Entity;
import com.helloworld.entity.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Qualifier("fakeData")
public class FakeEntityDaoImpl implements EntityDao {

    private static Map<Integer, User> users;
    static {

        users = new HashMap<Integer, User>() {

            {
                put(1, new User("1","Refaie","ref","123","ref@go.com"));
                put(2, new User("2","Hamed","hamed","1234","hamed@go.com"));
                put(3, new User("3","Sherif","sherif","12345","sherif@go.com"));
            }
        };

    }

    /*@Override
    public boolean equals(Object obj)
    {
        User u=(User)obj;
        return users.contains(u);
    }*/
    @Override
    public Collection<User> getAllEntities() {
        return users.values();
    }

    @Override
    public User getEntityById(int id) {
        return users.get(id);
    }

    @Override
    public void removeEntityById(int id) {
        users.remove(id);
    }

    @Override
    public void updateEntity(User user) {
        User s = users.get(user.getId());
        s.setName(user.getName());
        s.setUsername(user.getUsername());
        s.setPassword(user.getPassword());
        s.setEmail(user.getEmail());
        users.put(Integer.getInteger(user.getId()),user);
        users.put(Integer.getInteger(user.getId()),user);
    }

    @Override
    public void insertEntityToDb(User user) {
        users.put(Integer.getInteger(user.getId()),user);
    }
}
