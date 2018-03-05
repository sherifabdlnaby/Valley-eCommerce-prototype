package com.piper.valley.dao;

import java.util.Collection;

public interface EntityDao<T> {
	Collection<T> getAllEntities();

	T getEntityById(String id);

	boolean removeEntityById(String id);

	boolean updateEntity(T entity);

	boolean insertEntityToDb(T entity);
}
