package com.piper.valley.models.dao;

import com.piper.valley.models.entity.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
@Qualifier("UserDao")
public class UserDao implements EntityDao<User> {

	private static Map<String, User> entities;

	static {
		entities = new HashMap<String, User>() {
			{
				put("1", new User("1", "Sherif", "sherifabdlnaby", "123456", "sherif@email.com"));
				put("2", new User("2", "Khaled", "wewark", "123456", "wewark@email.com"));
				put("3", new User("3", "Refaie", "refaie        ", "123456", "refaie@email.com"));
			}
		};
	}

	@Override
	public Collection<User> getAllEntities() {
		return entities.values();
	}


	@Override
	public User getEntityById(String id) {
		return entities.get(id);
	}

	@Override
	public boolean removeEntityById(String id) {
		entities.remove(id);
		return true;
	}

	@Override
	public boolean updateEntity(User User) {
		User s = entities.get(User.getId());
		entities.put(User.getId(), User);
		return true;
	}

	@Override
	public boolean insertEntityToDb(User User) {
		entities.put(User.getId(), User);
		return true;
	}

	public User getEntityByUsername(String Username) {
		Collection<User> users = this.getAllEntities();
		for (User user : users) {
			if (user.getUsername().equals(Username))
				return user;
		}
		return null;
	}
}
